package com.thomsonreuters.lambda.demo.oldserver;

import java.util.Date;
import org.junit.Before;



public class FindOldServersTestClass {

	int buffer;
	Date now;
	
	@Before
	public void setUp() throws Exception {
	
		now = new Date();
		buffer = 5;
		
	}
/*
		@Test
	public void testInstanceBornBeforeCutOffDate() {
		Date previously = new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime();
		IEC2 ec2 = new EC2Stub("random.server.name", previously);
		EC2sStub ec2s = new EC2sStub(ec2);
		
		try {
			IEC2s oldInstanceIDs =  OldServer.identifyOldServers(buffer, ec2s);
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
			IEC2s oldInstanceIDs =  OldServer.identifyOldServers(buffer, ec2s);
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
			IEC2s oldInstanceIDs =  OldServer.identifyOldServers(buffer, ec2s);
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
			OldServer.identifyOldServers(buffer, ec2s);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testInvalidOldInstance()  {
		int buffer = 5;

		//Date previously = new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime();
		IEC2 ec2 = new EC2Stub("random.server.name", null);
		IEC2s ec2s = new EC2sStub(ec2);
	
		try {			
			OldServer.identifyOldServers(buffer, ec2s);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);	
		} catch (Exception e) {
			fail("Unexpected exception - ec2s - " + ec2s.toString() +" - " + e.toString());
		}
	}
	*/
}
