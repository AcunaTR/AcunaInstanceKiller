package com.thomsonreuters.lambda.demo.oldserver;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.environment.sns.ISNSEnv;
import com.thomsonreuters.aws.environment.sns.SNSEnv;
import com.thomsonreuters.lambda.demo.OldServer;
import com.thomsonreuters.lambda.demo.exceptions.NotEnoughServersException;
import com.thomsonreuters.lambda.demo.stubs.EC2Stub;
import com.thomsonreuters.lambda.demo.stubs.EC2sStub;
import com.thomsonreuters.lambda.demo.stubs.SNSEnvStub;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;



public class FindOldServersTestClass {

	int buffer;
	Date now;
	SNSEnvStub snsEnv;
	
	@Before
	public void setUp() throws Exception {
	
		now = new Date();
		buffer = 5;
		snsEnv = new SNSEnvStub();
	}
	
		@Test
		public void testInstanceBornBeforeCutOffDate() {
		Date previously = new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime();
		IEC2 ec2 = new EC2Stub("random.server.name", previously);
		EC2sStub ec2s = new EC2sStub(ec2);

		
		try {
			IEC2s oldInstanceIDs =  OldServer.findOldInstances(ec2s, OldServer.getCutOffDate(now, buffer));
			IEC2s recentInstanceIDs =  OldServer.findRecentInstances(ec2s, OldServer.getCutOffDate(now, buffer));
			Assert.assertEquals(1, oldInstanceIDs.size());
			Assert.assertEquals(0, recentInstanceIDs.size());
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
			IEC2s recentInstanceIDs =  OldServer.findRecentInstances(ec2s, OldServer.getCutOffDate(now, buffer));
			Assert.assertEquals(0, oldInstanceIDs.size());
			Assert.assertEquals(1, recentInstanceIDs.size());
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
			IEC2s recentInstanceIDs =  OldServer.findRecentInstances(ec2s, OldServer.getCutOffDate(now, buffer));
			Assert.assertEquals(4, oldInstanceIDs.size());
			Assert.assertEquals(3, recentInstanceIDs.size());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testNoInstances() {
		EC2sStub ec2s = new EC2sStub();
		try {
			OldServer.identifyOldServers(null, buffer, ec2s, null, null, null);
			fail("Expected NotEnoughServersException not thrown");
		} catch (NotEnoughServersException e){
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}	
	}
	
	@Test
	public void testInvalidOldInstance()  {
		int buffer = 5;

		IEC2 ec2a = new EC2Stub("random.server.name",null);
		IEC2 ec2b = new EC2Stub("random.server.name",null);
		IEC2 ec2c = new EC2Stub("random.server.name",null);
		IEC2 ec2d = new EC2Stub("random.server.name",null);
		IEC2 ec2e = new EC2Stub("random.server.name",null);
		IEC2 ec2f = new EC2Stub("random.server.name",null);
		IEC2 ec2g = new EC2Stub("random.server.name",null);
		EC2sStub ec2s = new EC2sStub(Arrays.asList(ec2a, ec2b, ec2c, ec2d, ec2e, ec2f, ec2g));
		
		try {			
			OldServer.identifyOldServers(null, buffer, ec2s, null, null, null);
			fail("Expected NullPointerException not thrown");
		} catch (NullPointerException e) {
			Assert.assertTrue(true);	
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	
	@Test
	public  void testtooFewEC2s() {
		IEC2 ec2 = new EC2Stub("random.server.name", null);
		IEC2s ec2s = new EC2sStub(ec2);
		try {
			OldServer.identifyOldServers(null, buffer, ec2s, null, null, null);
			fail("Expected NotEnoughServersException not thrown");
		} catch (NotEnoughServersException e){
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}	
	}
	
	@Test 
	public void testExactlyFiveInstances() {
		IEC2 ec2b = new EC2Stub("random.server.name",now);
		IEC2 ec2c = new EC2Stub("random.server.name",now);
		IEC2 ec2d = new EC2Stub("random.server.name",now);
		IEC2 ec2e = new EC2Stub("random.server.name",now);
		IEC2 ec2f = new EC2Stub("random.server.name",now);
		EC2sStub ec2s = new EC2sStub(Arrays.asList(ec2b, ec2c, ec2d, ec2e, ec2f));
		
		try {			
			OldServer.identifyOldServers(null, buffer, ec2s, null, null, null);
			fail("Expected NullPointerException not thrown");
		} catch (NotEnoughServersException e) {
			Assert.assertTrue(true);	
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
	}
	
	@Test 
	public void testKeepNewestInstances() {
		now = new Date();
		IEC2 ec2b = new EC2Stub("ec2b",now);
		now = new Date();
		IEC2 ec2c = new EC2Stub("ec2c",now);
		List<IEC2> newServers = new LinkedList<>();
		newServers.add(ec2b);
		newServers.add(ec2c);
		IEC2s ec2Keep = new EC2sStub(newServers);
		
		Date previously = new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime();
		IEC2 ec2d = new EC2Stub("ec2d",previously);
		previously = new GregorianCalendar(2017, Calendar.DECEMBER, 2).getTime();
		IEC2 ec2e = new EC2Stub("ec3e",previously);
		previously = new GregorianCalendar(2017, Calendar.DECEMBER, 3).getTime();
		IEC2 ec2f = new EC2Stub("ec2f",previously);
		previously = new GregorianCalendar(2017, Calendar.DECEMBER, 4).getTime();
		IEC2 ec2g = new EC2Stub("ec2g",previously);
		List<IEC2> oldServers = new LinkedList<>();
		oldServers.add(ec2d);
		oldServers.add(ec2e);
		oldServers.add(ec2f);
		oldServers.add(ec2g);
		
		EC2sStub ec2Old = new EC2sStub(oldServers);
		
		try {			
			IEC2s oldEc2s = OldServer.findDoomedInstances(ec2Old, ec2Keep, buffer);
			Assert.assertEquals(1, oldEc2s.size());
			Assert.assertTrue("ec2d".equals(oldEc2s.get(0).getTags().getValue("name")));
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
	}
	
	@Test
	public  void testTooManyYoungEC2s() {
		IEC2 ec2a = new EC2Stub("random.server.name",now);
		IEC2 ec2b = new EC2Stub("random.server.name",now);
		IEC2 ec2c = new EC2Stub("random.server.name",now);
		IEC2 ec2d = new EC2Stub("random.server.name",now);
		IEC2 ec2e = new EC2Stub("random.server.name",now);
		IEC2 ec2f = new EC2Stub("random.server.name",now);
		IEC2 ec2g = new EC2Stub("random.server.name",now);
		EC2sStub ec2s = new EC2sStub(Arrays.asList(ec2a, ec2b, ec2c, ec2d, ec2e, ec2f, ec2g));
		
		String ERROR_TOPIC_ARN = ("24a");
		
		try {
			OldServer.identifyOldServers(null, buffer, ec2s, null, ERROR_TOPIC_ARN, snsEnv);
		} catch (Exception e) {
			Assert.assertTrue(ERROR_TOPIC_ARN.equals(snsEnv.getTopicArn()));
		}	
	}
}
