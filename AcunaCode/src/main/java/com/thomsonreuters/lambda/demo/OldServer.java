package com.thomsonreuters.lambda.demo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.thomsonreuters.aws.ec2.EC2s;
import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.environment.elb.IELBEnv;
import com.thomsonreuters.aws.environment.sns.ISNSEnv;
import com.thomsonreuters.aws.environment.sns.SNSEnv;
import com.thomsonreuters.aws.targetgroup.ITargetGroup;
import com.thomsonreuters.lambda.demo.exceptions.InvalidInstancesException;
import com.thomsonreuters.lambda.demo.exceptions.InvalidTargetGroupsException;
import com.thomsonreuters.lambda.demo.exceptions.NoTargetGroupException;
import com.thomsonreuters.lambda.demo.exceptions.NotEnoughServersException;
import com.thomsonreuters.lambda.demo.factories.IDescribeTargetGroupsRequestFactory;

public class OldServer {
	
	public static IEC2s identifyOldServers(IELBEnv elbEnv, 
											int numberOfBackups, 
											IEC2s ec2s, 
											IDescribeTargetGroupsRequestFactory reqFactory,
											String ERROR_TOPIC_ARN, ISNSEnv snsEnv) 
							throws InvalidInstancesException, 
							NoTargetGroupException, 
							InvalidTargetGroupsException, 
							NotEnoughServersException {
		
		if(ec2s.size() <= numberOfBackups) {
			throw new NotEnoughServersException("Expected " + numberOfBackups + " but only had " + ec2s.size() + " servers ");
		}
		
		Date today = new Date();
		Date cutOffDate = getCutOffDate(today, numberOfBackups);
		
		IEC2s oldServers = findOldInstances(ec2s, cutOffDate);
		IEC2s youngServers = findRecentInstances(ec2s, cutOffDate);
		
		IEC2s toTerminate = findDoomedInstances(oldServers, youngServers, numberOfBackups);
		
		 

		 
		 if(youngServers.size() > numberOfBackups) {
				String errorMessage = ("More backup servers running than anticipated - " + youngServers.size() + " servers");
				snsEnv.publish(ERROR_TOPIC_ARN, errorMessage);
		 }
		 
		
		if(!connectedToELB(elbEnv, toTerminate, reqFactory)) {
			return toTerminate;
		}
		throw new InvalidInstancesException("InvalidInstancesException - Servers still connected to ELB - number of servers = " + oldServers.size() + " - oldServers.tostring = " +oldServers.toString());

	}
	
	public static IEC2s findDoomedInstances(IEC2s oldServers, IEC2s youngServers, int numberOfBackups) throws NotEnoughServersException {
		while (youngServers.size() < numberOfBackups) {
			 if (oldServers.isEmpty()) {
				throw new NotEnoughServersException("Not enough servers running");
			 }
			 int mostRecentIdx = identifyMostRecent(oldServers);
			 IEC2 temp = oldServers.remove(mostRecentIdx);
			 youngServers.add(temp);
		 }
		return oldServers;
	}

	private static int identifyMostRecent(IEC2s oldServers) throws NotEnoughServersException {
		if (oldServers.isEmpty()) {
			throw new NotEnoughServersException("No old servers found");
		}
	
		int index = 0;
		Date time  = oldServers.get(0).getLaunchTime();
		for (int i=0; i< oldServers.size(); i++) {
			Date newTime  = oldServers.get(i).getLaunchTime();
			if (newTime.after(time)) {
				time = newTime;
				index = i;
			}
		}
		return index;
	}

	private static boolean connectedToELB(IELBEnv elbEnv, IEC2s oldServers, IDescribeTargetGroupsRequestFactory reqFactory) throws NoTargetGroupException, InvalidTargetGroupsException {
		ITargetGroup targetGroup = ELBHandler.getTargetGroup(elbEnv, reqFactory);
		List<String> targetIDs = targetGroup.getTargetIDs(elbEnv);
		for(int i=0; i<oldServers.size(); i++) {
			String id = oldServers.get(i).getInstanceID();
			if(targetIDs.contains(id)) {
				return true;
			}
		}
		
		return false;
	}

	public static IEC2s findOldInstances(IEC2s instances, Date cutOffDate) {

		IEC2s oldies = EC2s.create();
		
		for (int i = 0; i < instances.size(); i++) {
			
			Date born = instances.get(i).getLaunchTime();
    		if (born.before(cutOffDate)) {
    			oldies.add(instances.get(i));
    		}
		}
		
		return oldies;
	}
	
	
	public static IEC2s findRecentInstances(IEC2s instances, Date cutOffDate) {

		IEC2s recentServers = EC2s.create();
		
		for (int i = 0; i < instances.size(); i++) {
			
			Date born = instances.get(i).getLaunchTime();
    		if (born.after(cutOffDate)) {
    			recentServers.add(instances.get(i));
    		}
		}
		
		return recentServers;
	}
	
	
	public static Date getCutOffDate(Date date, int buffer) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
    	c.add(Calendar.DATE, (-1*buffer));
    	return c.getTime();
	}
}
