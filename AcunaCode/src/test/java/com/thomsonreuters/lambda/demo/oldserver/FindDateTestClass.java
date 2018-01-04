package com.thomsonreuters.lambda.demo.oldserver;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.lambda.demo.OldServer;
import com.thomsonreuters.lambda.demo.stubs.EC2Stub;
import com.thomsonreuters.lambda.demo.stubs.EC2sStub;
import com.thomsonreuters.lambda.demo.stubs.InstanceLaunchTimeStub;





public class FindDateTestClass {

	int buffer;
	Date now;
	IEC2 ec2; 
	EC2sStub ec2s;
	
	@Before
	public void setUp() throws Exception {
		buffer = 5;
		now = new Date();
		ec2 = new EC2Stub("random.server.name",null);
		ec2s  = new EC2sStub(ec2);
	}

	
	@Test // check the calculated date values are in UTC
	public void testTodaysDateInUTC() { 
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));

		InstanceLaunchTimeStub currentTime = new InstanceLaunchTimeStub();
		
		Assert.assertEquals(sdf.format(now), sdf.format((currentTime.whatTimeIsNow())));		
	}
	
	
/*	@Test
	public void testBufferDistanceBetweenCutoffAndToday() {
		int bufferHours = -24*buffer;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, bufferHours);
		Date cutOffDate = OldServer.getCutOffDate(now, buffer);
		Assert.assertTrue(cal.getTime().equals(cutOffDate));	
	} */
	
	@Test //?same as above
	public void testCutoffTimeIsBeforeToday() {
		Date cutOffDate = OldServer.getCutOffDate(now, buffer);
		DateTime cutOffDateTime = new DateTime(cutOffDate.getTime());
		DateTime nowDateTime = new DateTime(now.getTime());
		//Duration bufferDuration = Duration.ofHours(buffer);
		try {
			Assert.assertTrue(cutOffDateTime.isBefore(nowDateTime));
		}catch (IllegalArgumentException e) {
			fail("Unexpected Illegal Agrument Exception - end date before start date - " + e.getMessage());
		}catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testTimeDifferenceNotSilly() {
		Date cutOffDate = OldServer.getCutOffDate(now, buffer);
		DateTime cutOffDateTime = new DateTime(cutOffDate.getTime());
		DateTime nowDateTime = new DateTime(now.getTime());
		int hourDifference = cutOffDateTime.plusDays(buffer).compareTo(nowDateTime); 
		try {		
			Assert.assertTrue(hourDifference <= 5);
		}catch (IllegalArgumentException e) {
			fail("Unexpected Illegal Agrument Exception - end date before start date - " + e.getMessage());
		}catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
/*	@Test
	public void testBornOnCutoffTime() {
	//	fail("Not yet implemented");
	}
	
	@Test
	public void testTimeDifferenceNotNegative() {
	//	fail("Not yet implemented"); ? same test as testCutoffTimeIsBeforeToday
	}

	@Test
	public void testServerDateInUTC() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCutOffDateInputInUTC() {
	//	fail("Not yet implemented");
	} */
}
