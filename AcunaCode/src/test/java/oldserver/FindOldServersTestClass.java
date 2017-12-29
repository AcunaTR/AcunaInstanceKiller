package oldserver;

import static org.junit.Assert.*;


import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.lambda.demo.stubs.DescribeEC2sRequestFactoryStub;
import com.amazonaws.lambda.demo.stubs.DescribeEC2sRequestStub;
import com.amazonaws.lambda.demo.stubs.EC2EnvStub;
import com.amazonaws.lambda.demo.stubs.EC2Stub;
import com.amazonaws.lambda.demo.stubs.EC2sStub;
import com.amazonaws.lambda.demo.stubs.ReservationStub;
import com.amazonaws.lambda.demo.stubs.ReservationsStub;
import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.lambda.demo.InstanceHandler;
import com.thomsonreuters.lambda.demo.OldServer;
import com.thomsonreuters.lambda.demo.exceptions.EmptyReservationException;
import com.thomsonreuters.lambda.demo.exceptions.InvalidInstancesException;
import com.thomsonreuters.lambda.demo.exceptions.NoInstancesException;
import com.thomsonreuters.lambda.demo.exceptions.NoReservationException;



public class FindOldServersTestClass {

	//int buffer;
	//Date now;
	//IEC2s oldServers; 
	//IEC2s backupServers; 
	
	@Before
	public void setUp() throws Exception {
	//	buffer = 5;
	//	now = new Date();
		
	}

	/*	@Test
	public void testInstanceBornBeforeCutOffDate() {
		Date previously = new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime();
		IEC2 ec2 = new EC2Stub("random.server.name", previously);
		EC2sStub ec2s = new EC2sStub(ec2);
		
		try {
			oldServers = OldServer.identifyOldServers(now, buffer, ec2s);
			Assert.assertEquals(1, oldServers.size());
			Assert.assertEquals(0, backupServers.size());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}



	@Test
	public void testInstanceBornAfterCutOffDate() {
		IEC2 ec2 = new EC2Stub("random.server.name",now);
		EC2sStub ec2s = new EC2sStub(ec2);
		
		try {
			oldServers = OldServer.identifyOldServers(now, buffer, ec2s);
			Assert.assertEquals(0, oldServers.size());
			Assert.assertEquals(1, backupServers.size());			
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
			oldServers = OldServer.identifyOldServers(now, buffer, ec2s);
			Assert.assertEquals(4, oldServers.size());
			Assert.assertEquals(3, backupServers.size());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testNoInstances() {
		EC2sStub ec2s = new EC2sStub(Arrays.asList(null));
		try {
			oldServers = OldServer.identifyOldServers(now, buffer, ec2s);
			//Assert.assertEquals(0, oldServers.size());
			//Assert.assertEquals(0, backupServers.size());
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}*/
	
	@Test
	public void testInvalidInstances() throws InvalidInstancesException, NoInstancesException, EmptyReservationException, NoReservationException {
		Date now = new Date();
		int buffer = 5;
//		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
//		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		Date previously = new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime();
		IEC2 ec2 = new EC2Stub("random.server.name", previously);
		IEC2s ec2s = new EC2sStub(ec2);
	//	IEC2s iec2s = (IEC2s) ec2s;
	//	IEC2s oldServers = new EC2sStub();

//		ReservationStub reservation = new ReservationStub(ec2s);
//		ReservationsStub reservations = new ReservationsStub(reservation);
		
//		EC2EnvStub ec2Env = new EC2EnvStub(reservations);
//		IEC2s ec2Result = InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
		
		try {
			
			List<String> instanceIDs = OldServer.identifyOldServers(now, buffer, ec2s);
			Assert.assertEquals(1, instanceIDs.size());
			//Assert.assertEquals(0, oldServers.size());
		} catch (Exception e) {
			fail("Unexpected exception - ec2s - " + ec2s.toString() +" - " + e.toString());
		}
	}
}
