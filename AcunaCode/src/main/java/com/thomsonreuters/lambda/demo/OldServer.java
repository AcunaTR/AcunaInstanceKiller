package com.thomsonreuters.lambda.demo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.thomsonreuters.aws.ec2.EC2s;

public class OldServer {
	
	ArrayList <EC2s> oldServers = new ArrayList<>();
	
	public static Date identifyOldServers(Date date, int buffer) {
		//List<EC2s> when finished!
		Date cutOffDate = getCutOffDate(date, buffer);
		return cutOffDate;
		//return oldServers;
	
	}
	
	private static Date getCutOffDate(Date date, int buffer) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
    	c.add(Calendar.DATE, (-1*buffer));
    	return c.getTime();
		}
	
	
	/* given buffer, list of instance
	find cut off date
	work out 5 days ago
	find instances born before date
	
	
	Possible algorithm for making sure exactly 4 old servers
	
	If >5 - sort by date, kill all but 5 youngest
	  - risk - if 5 made today, could kill all old backups in error
	 
	  
	ALTERNATIVELY - kill the old ones (>than buffer) and flag up that there were more servers than 5, and to be killed manually 
	
	
	*/	

}
