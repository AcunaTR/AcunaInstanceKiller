package com.thomsonreuters.lambda.demo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.thomsonreuters.aws.ec2.EC2s;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.environment.elb.IELBEnv;
import com.thomsonreuters.aws.targetgroup.ITargetGroup;
import com.thomsonreuters.lambda.demo.exceptions.InvalidInstancesException;
import com.thomsonreuters.lambda.demo.exceptions.InvalidTargetGroupsException;
import com.thomsonreuters.lambda.demo.exceptions.NoTargetGroupException;
import com.thomsonreuters.lambda.demo.factories.IDescribeTargetGroupsRequestFactory;

public class OldServer {
	
	//static List<String> currentServerIDs = new ArrayList<>();
	//static List<String> instanceIDs = new ArrayList<>();
	
	public static IEC2s identifyOldServers(IELBEnv elbEnv, int bufferDays, IEC2s ec2s, IDescribeTargetGroupsRequestFactory reqFactory) throws InvalidInstancesException, NoTargetGroupException, InvalidTargetGroupsException {
		Date today = new Date();
		Date cutOffDate = getCutOffDate(today, bufferDays);
		IEC2s oldServers = findOldInstances(ec2s, cutOffDate);
		if(!connectedToELB(elbEnv, oldServers, reqFactory)) {
			return oldServers;
		}
		throw new InvalidInstancesException("InvalidInstancesException - Servers still connected to ELB - number of servers = " + oldServers.size() + " - oldServers.tostring = " +oldServers.toString());

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
	
	
	public static Date getCutOffDate(Date date, int buffer) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
    	c.add(Calendar.DATE, (-1*buffer));
    	return c.getTime();
	}
	
	
	/* given buffer, list of instance
	find cut off date // done
	work out 5 days ago // done
	find instances born before date // done
	
	
	Possible algorithm for making sure exactly 4 old servers
	
	If >5 - sort by date, kill all but 5 youngest
	  - risk - if 5 made today, could kill all old backups in error
	 
	  
	ALTERNATIVELY - kill the old ones (>than buffer) and flag up that there were more servers than 5, and to be killed manually 
	
	
	If killing more than 2 or 3 instances, put in some sort of check - Are you sure? flag
	
	*/	
	
	/*
	private static  List<String> findBackupInstances(IEC2s instances, Date cutOffDate) {

		currentServerIDs.clear();
		for (int i = 0; i < instances.size(); i++) {
			Date born = instances.get(i).getLaunchTime();
    		if (born.after(cutOffDate)) {
    			//IEC2 ec2 = instances.get(i);
    			currentServerIDs.add(instances.get(i).getInstanceID());
    			//currentServers.add(ec2);
    		//backupServers.addAll(ec2);
    		}
		}
		return currentServerIDs;
	}

	
	*/

}
