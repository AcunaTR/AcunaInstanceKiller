package com.thomsonreuters.lambda.demo.oldserver;

import static org.junit.Assert.*;


import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.lambda.demo.InstanceHandler;
import com.thomsonreuters.lambda.demo.OldServer;
import com.thomsonreuters.lambda.demo.exceptions.EmptyReservationException;
import com.thomsonreuters.lambda.demo.exceptions.InvalidInstancesException;
import com.thomsonreuters.lambda.demo.exceptions.NoInstancesException;
import com.thomsonreuters.lambda.demo.exceptions.NoReservationException;
import com.thomsonreuters.lambda.demo.stubs.DescribeEC2sRequestFactoryStub;
import com.thomsonreuters.lambda.demo.stubs.DescribeEC2sRequestStub;
import com.thomsonreuters.lambda.demo.stubs.EC2EnvStub;
import com.thomsonreuters.lambda.demo.stubs.EC2Stub;
import com.thomsonreuters.lambda.demo.stubs.EC2sStub;
import com.thomsonreuters.lambda.demo.stubs.ReservationStub;
import com.thomsonreuters.lambda.demo.stubs.ReservationsStub;



public class FindOldServersTestClass {

	int buffer;
	Date now;
	
	@Before
	public void setUp() throws Exception {
	
		now = new Date();
		buffer = 5;
		
	}

		@Test
	public void testInstanceBornBeforeCutOffDate() {
		Date previously = new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime();
		IEC2 ec2 = new EC2Stub("random.server.name", previously);
		EC2sStub ec2s = new EC2sStub(ec2);
		
		try {
			List<String> oldInstanceIDs =  OldServer.identifyOldServers(now, buffer, ec2s);
	//		List<String> currentInstanceIDs =  OldServer.findBackupInstances(now, buffer, ec2s);
			Assert.assertEquals(1, oldInstanceIDs.size());
		//	Assert.assertEquals(0, currentInstanceIDs.size());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}



	@Test
	public void testInstanceBornAfterCutOffDate() {
		IEC2 ec2 = new EC2Stub("random.server.name",now);
		EC2sStub ec2s = new EC2sStub(ec2);
		
		try {
			List<String> oldInstanceIDs =  OldServer.identifyOldServers(now, buffer, ec2s);
	//		List<String> currentInstanceIDs =  OldServer.findBackupInstances(now, buffer, ec2s);
			Assert.assertEquals(0, oldInstanceIDs.size());
		//	Assert.assertEquals(1, currentInstanceIDs.size());			
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testInstancesBeforeAndAfterCutOffDate() {
		Date previously = new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime();
		IEC2 ec2a = new EC2Stub("random.server.name",now);
		IEC2 ec2b = new EC2Stub("random.server.name",now);
		IEC2 ec2c = new EC2Stub("random.server.name",now);
		IEC2 ec2d = new EC2Stub("random.server.name",previously);
		IEC2 ec2e = new EC2Stub("random.server.name",previously);
		IEC2 ec2f = new EC2Stub("random.server.name",previously);
		IEC2 ec2g = new EC2Stub("random.server.name",previously);
		EC2sStub ec2s = new EC2sStub(Arrays.asList(ec2a, ec2b, ec2c, ec2d, ec2e, ec2f, ec2g));
		try {
			List<String> oldInstanceIDs =  OldServer.identifyOldServers(now, buffer, ec2s);
		//	List<String> currentInstanceIDs =  OldServer.findBackupInstances(now, buffer, ec2s);
			Assert.assertEquals(4, oldInstanceIDs.size());
		//	Assert.assertEquals(3, currentInstanceIDs.size());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testNoInstances() {
		EC2sStub ec2s = new EC2sStub();
		try {
			List<String> oldInstanceIDs = OldServer.identifyOldServers(now, buffer, ec2s);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testInvalidOldInstance()  {
		Date now = new Date();
		int buffer = 5;

		Date previously = new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime();
		IEC2 ec2 = new EC2Stub("random.server.name", null);
		IEC2s ec2s = new EC2sStub(ec2);
	
		try {			
			List<String> oldInstanceIDs = OldServer.identifyOldServers(now, buffer, ec2s);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);	
		} catch (Exception e) {
			fail("Unexpected exception - ec2s - " + ec2s.toString() +" - " + e.toString());
		}
	}
	
}
