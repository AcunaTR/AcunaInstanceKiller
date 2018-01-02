package com.thomsonreuters.lambda.demo.oldserver;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.lambda.demo.OldServer;
import com.thomsonreuters.lambda.demo.stubs.EC2Stub;
import com.thomsonreuters.lambda.demo.stubs.EC2sStub;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;



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
			IEC2s oldInstanceIDs =  OldServer.findOldInstances(ec2s, OldServer.getCutOffDate(now, buffer));
			Assert.assertEquals(1, oldInstanceIDs.size());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}



	@Test
	public void testInstanceBornAfterCutOffDate() {
		IEC2 ec2 = new EC2Stub("random.server.name",now);
		EC2sStub ec2s = new EC2sStub(ec2);
		
		try {
			IEC2s oldInstanceIDs =  OldServer.findOldInstances(ec2s, OldServer.getCutOffDate(now, buffer));
			Assert.assertEquals(0, oldInstanceIDs.size());
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
			IEC2s oldInstanceIDs =  OldServer.findOldInstances(ec2s, OldServer.getCutOffDate(now, buffer));
			Assert.assertEquals(4, oldInstanceIDs.size());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testNoInstances() {
		EC2sStub ec2s = new EC2sStub();
		try {
			OldServer.identifyOldServers(null, buffer, ec2s, null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testInvalidOldInstance()  {
		int buffer = 5;

		IEC2 ec2 = new EC2Stub("random.server.name", null);
		IEC2s ec2s = new EC2sStub(ec2);
	
		try {			
			OldServer.identifyOldServers(null, buffer, ec2s, null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);	
		} catch (Exception e) {
			fail("Unexpected exception - ec2s - " + ec2s.toString() +" - " + e.toString());
		}
	}
	
}
