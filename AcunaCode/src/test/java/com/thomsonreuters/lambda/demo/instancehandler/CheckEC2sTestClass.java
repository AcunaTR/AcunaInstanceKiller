package com.thomsonreuters.lambda.demo.instancehandler;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.lambda.demo.InstanceHandler;
import com.thomsonreuters.lambda.demo.exceptions.EmptyReservationException;
import com.thomsonreuters.lambda.demo.exceptions.InvalidInstancesException;
import com.thomsonreuters.lambda.demo.stubs.DescribeEC2sRequestFactoryStub;
import com.thomsonreuters.lambda.demo.stubs.DescribeEC2sRequestStub;
import com.thomsonreuters.lambda.demo.stubs.EC2EnvStub;
import com.thomsonreuters.lambda.demo.stubs.EC2Stub;
import com.thomsonreuters.lambda.demo.stubs.EC2sStub;
import com.thomsonreuters.lambda.demo.stubs.ReservationStub;
import com.thomsonreuters.lambda.demo.stubs.ReservationsStub;

public class CheckEC2sTestClass {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNoEC2sNull() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		ReservationStub reservation = new ReservationStub(null);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);
			
		try {
			InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
			fail("Expected 'Empty Reservation' exception not thrown " );
		} catch (EmptyReservationException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testNoEC2sNullStub() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		IEC2 ec2 = null;
		EC2sStub ec2s = new EC2sStub(ec2);
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);

		try {
			InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
			fail("Expected 'Empty Reservation' exception not thrown " );
		} catch (EmptyReservationException e) {
			fail("Unexpected Empty Reservation Exception thrown ");
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		
	}	

	@Test
	public void testNoEC2sEmptyList() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		//IEC2 ec2 = new EC2Stub("random.server.name");
		EC2sStub ec2s = new EC2sStub(new ArrayList<>());
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);

		try {
			InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
			fail("Expected 'Empty Reservation' exception not thrown " );
		} catch (EmptyReservationException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
	}
	
	@Test
	public void testNoEC2sListEmptied() { 
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		//IEC2 ec2 = new EC2Stub("random.server.name");
		EC2sStub ec2s = new EC2sStub(new ArrayList<>());
		ec2s.clear();
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);

		try {
			InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
			fail("Expected 'Empty Reservation' exception not thrown " );
		} catch (EmptyReservationException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
	}
	
	
	@Test
	public void testCheckOneEC2() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		IEC2 ec2 = new EC2Stub("random.server.name",null);
		EC2sStub ec2s = new EC2sStub(ec2);
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);

		try {
			IEC2s ec2Result = InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
			Assert.assertEquals(1, ec2Result.size());
			Assert.assertEquals(ec2, ec2Result.get(0));
		}catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
	}
	
	@Test
	public void testCheckOneEC2DeletedEC2() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		IEC2 ec2 = new EC2Stub("random.server.name",null);
		EC2sStub ec2s = new EC2sStub(ec2);
		ec2s.clear();
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);

		try {
			InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
			fail("Expected 'Empty Reservation' exception not thrown " );
		} catch (EmptyReservationException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
	}
	
	
	
	@Test
	public void testCheckOneInvalidEC2() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		IEC2 ec2 = new EC2Stub("otherrandom.server.name",null);
		EC2sStub ec2s = new EC2sStub(ec2);
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);

		try {
			InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
			fail("expected exception - InvalidInstancesException");
		}catch (InvalidInstancesException e) {
			Assert.assertTrue(true);
		}catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
	}
	
	@Test //TODO test works if 'checkEc2s' is made public 
	public void testCheckOneEmptyListIEC2s() {
		/*IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		IEC2 ec2 = new EC2Stub("otherrandom.server.name",null);*/
		EC2sStub ec2s = new EC2sStub();
		//IEC2s ec2Result = ec2s.empty();
		//ReservationStub reservation = new ReservationStub(ec2Result);
		//ReservationsStub reservations = new ReservationsStub(reservation);
		
		//EC2EnvStub ec2Env = new EC2EnvStub(reservations);

		try {
			InstanceHandler.checkEc2s(ec2s, "random.server.name");
			fail("expected exception - Empty Reservation Exception not thrown");
		}catch (EmptyReservationException e) {
			Assert.assertTrue(true);
		}catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
	}
	
/*	@Test //test works if 'checkEc2s' is made public 
	public void testCheckOneNullListIEC2s() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		IEC2 ec2 = new EC2Stub("random.server.name");
		EC2sStub ec2s = new EC2sStub(ec2);
		ec2s.nullify();
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);

		try {
			InstanceHandler.checkEc2s(ec2s, "random.server.name");
			fail("expected exception - Empty Reservation Exception not thrown");
		}catch (EmptyReservationException e) {
			Assert.assertTrue(true);
		}catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
	} */
	
	
	@Test
	public void testCheckSeveralValidEC2s() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		IEC2 ec2a = new EC2Stub("random.server.name",null);
		IEC2 ec2b = new EC2Stub("random.server.name",null);
		IEC2 ec2c = new EC2Stub("random.server.name",null);
		List<IEC2> collectionEc2s = Arrays.asList(ec2a,ec2b,ec2c);
		EC2sStub ec2s = new EC2sStub(collectionEc2s);
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);

		try {
			IEC2s ec2Result = InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
			Assert.assertEquals(3, ec2Result.size());
			for (int i=0; i<ec2Result.size(); i++) {			
				Assert.assertTrue(ec2Result.contains(collectionEc2s.get(i)));
			}
		}catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
	}
}
