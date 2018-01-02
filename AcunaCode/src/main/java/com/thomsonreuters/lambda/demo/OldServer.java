package com.thomsonreuters.lambda.demo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.thomsonreuters.aws.ec2.EC2s;
import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.ec2.IEC2s;

public class OldServer {
	
	static List<String> currentServerIDs = new ArrayList<>();
	static List<String> instanceIDs = new ArrayList<>();
	
	public static List<String> identifyOldServers(Date todaysDate, int bufferDays, IEC2s ec2s) {
		
		Date cutOffDate = getCutOffDate(todaysDate, bufferDays);
		
		//IEC2s duplicate = ec2s.clone();

	//	oldServers = ec2s.
		List<String> oldServers = findOldInstanceIDs(ec2s, cutOffDate);
		List<String> currentServers = findBackupInstances(ec2s, cutOffDate);
		
		
		
		
		
		//return cutOffDate;
		
		
		return oldServers;	
	}
	
	private static List<String> findOldInstanceIDs(IEC2s instances, Date cutOffDate) {
    	//List<Instance> oldInstances = new ArrayList<>();
		//IEC2s oldie = EC2s.create();
		//IEC2s oldie = instances.clone();
		instanceIDs.clear();
		for (int i = 0; i < instances.size(); i++) {
		//	Date born = instances.get(i).getLaunchTime();
			 Date born = instances.get(i).getLaunchTime();
		//	Date born = flump.getLaunchTime();
	
    		if (born.before(cutOffDate)) {
    			//TODO - if this is straying, need to add in checks for the correct instance here, as all info other than ID is going to be lost beyond this
    			
    			instanceIDs.add(instances.get(i).getInstanceID());
    			//oldInstances.add(i);
    			//oldServers.add(instances.get(i));   
    			
    //	if (instances.size()>0) {
    		
    	//	oldServers.add(instances.get(0));
    			//addAll(instances.get(i));			
			}
    //		else {
    //			backupServers.add(instances.get(i);
    //		}
		}
		
		//IEC2s instances2 = new IEC2s()
		return instanceIDs;
	}

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

	
	
	
	// TODO I changed this from private to public, is that ok?
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

}
