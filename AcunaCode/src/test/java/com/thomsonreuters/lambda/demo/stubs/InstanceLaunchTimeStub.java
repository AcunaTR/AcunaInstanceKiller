package com.thomsonreuters.lambda.demo.stubs;

import java.util.Calendar;
import java.util.Date;

public class InstanceLaunchTimeStub {
	
	Calendar cal = Calendar.getInstance();
	private Date _launchTime;
	
	public InstanceLaunchTimeStub() {
			_launchTime = cal.getTime();
	}
	
	public Date whatTimeIsNow() {
		return _launchTime;
	}
	
	

}
