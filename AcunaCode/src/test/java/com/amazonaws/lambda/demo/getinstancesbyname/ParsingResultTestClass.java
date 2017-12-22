package com.amazonaws.lambda.demo.getinstancesbyname;

import static org.junit.Assert.*;

import java.util.Arrays;
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
import com.thomsonreuters.lambda.demo.GetInstancesByName;
import com.thomsonreuters.lambda.demo.exceptions.EmptyReservationException;
import com.thomsonreuters.lambda.demo.exceptions.NoReservationException;

public class ParsingResultTestClass {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testOneReservationNoInstances() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		ReservationStub reservation = new ReservationStub(null);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);
			
		try {
			GetInstancesByName.run(ec2Env, "random.server.name", reqFactory);
			fail("Expected 'Empty Reservation' exception not thrown " );
		} catch (EmptyReservationException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testOneReservationOneInstance() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		IEC2 ec2 = new EC2Stub("random.server.name");
		EC2sStub ec2s = new EC2sStub(ec2);
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);

		try {
			GetInstancesByName.run(ec2Env, "random.server.name", reqFactory);
			
			ReservationsStub resList = (ReservationsStub) ec2Env.getReservations();
			Assert.assertEquals(1, resList.get_getReservationCounter());
			
			ReservationStub res = resList.getReservations().get(0);
			Assert.assertEquals(1, res.get_getInstancesCounter());
			
		}catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
		
	
	}
	
	@Test
	public void testOneReservationMultipleInstances() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		IEC2 ec2a = new EC2Stub("random.server.name");
		IEC2 ec2b = new EC2Stub("random.server.name");
		IEC2 ec2c = new EC2Stub("random.server.name");
		IEC2 ec2d = new EC2Stub("random.server.name");
		EC2sStub ec2s = new EC2sStub(Arrays.asList(ec2a,ec2b,ec2c,ec2d));
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);

		try {
			GetInstancesByName.run(ec2Env, "random.server.name", reqFactory);
			
			ReservationsStub resList = (ReservationsStub) ec2Env.getReservations();
			Assert.assertEquals(1, resList.get_getReservationCounter());
			
				
			ReservationStub res = resList.getReservations().get(0);
			Assert.assertEquals(1, res.get_getInstancesCounter());
			
				
			
		}catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
	}
	
	@Test
	public void testNoReservations() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		EC2EnvStub ec2Env = new EC2EnvStub(null);
			
		try {
			GetInstancesByName.run(ec2Env, "random.server.name", reqFactory);
			fail("Expected 'No Reservation' exception not thrown " );
		} catch (NoReservationException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
	}
	
	@Test
	public void testMultipleReservationsOneInstanceEach() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		IEC2 ec2 = new EC2Stub("random.server.name");
		IEC2s ec2s = new EC2sStub(ec2);
		ReservationStub reservationA = new ReservationStub(ec2s);
		ReservationStub reservationB = new ReservationStub(ec2s);
		ReservationStub reservationC = new ReservationStub(ec2s);
		ReservationStub reservationD = new ReservationStub(ec2s);
		ReservationStub reservationE = new ReservationStub(ec2s);
		
		ReservationsStub reservations = new ReservationsStub(Arrays.asList(reservationA,reservationB,reservationC,reservationD,reservationE));
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);

		try {
			GetInstancesByName.run(ec2Env, "random.server.name", reqFactory);
			
			ReservationsStub resList = (ReservationsStub) ec2Env.getReservations();
			Assert.assertEquals(5, resList.get_getReservationCounter());
			
			for(ReservationStub res : resList.getReservations()) {
				Assert.assertEquals(1, res.get_getInstancesCounter());
			}		
		}catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testMultipleReservationsSomeEmpty() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		IEC2 ec2a = new EC2Stub("random.server.name");
		IEC2 ec2b = new EC2Stub("random.server.name");
		IEC2 ec2c = new EC2Stub("random.server.name");
		IEC2 ec2d = new EC2Stub("random.server.name");
		IEC2s ec2s = new EC2sStub(Arrays.asList(ec2a,ec2b,ec2c,ec2d));
		ReservationStub reservationA = new ReservationStub(ec2s);
		ReservationStub reservationB = new ReservationStub(ec2s);
		ReservationStub reservationC = new ReservationStub(null);
		ReservationStub reservationD = new ReservationStub(ec2s);
		ReservationStub reservationE = new ReservationStub(ec2s);
		
		ReservationsStub reservations = new ReservationsStub(Arrays.asList(reservationA,reservationB,reservationC,reservationD,reservationE));
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);

		try {
			GetInstancesByName.run(ec2Env, "random.server.name", reqFactory);
			fail("Expected 'Empty Reservation' exception not thrown " );
		} catch (EmptyReservationException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
	}
	
	@Test
	public void testMultipleReservationsMultipleInstances() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		IEC2 ec2a = new EC2Stub("random.server.name");
		IEC2 ec2b = new EC2Stub("random.server.name");
		IEC2 ec2c = new EC2Stub("random.server.name");
		IEC2 ec2d = new EC2Stub("random.server.name");
		IEC2s ec2s = new EC2sStub(Arrays.asList(ec2a,ec2b,ec2c,ec2d));
		ReservationStub reservationA = new ReservationStub(ec2s);
		ReservationStub reservationB = new ReservationStub(ec2s);
		ReservationStub reservationC = new ReservationStub(ec2s);
		ReservationStub reservationD = new ReservationStub(ec2s);
		ReservationStub reservationE = new ReservationStub(ec2s);
		
		ReservationsStub reservations = new ReservationsStub(Arrays.asList(reservationA,reservationB,reservationC,reservationD,reservationE));
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);

		try {
			GetInstancesByName.run(ec2Env, "random.server.name", reqFactory);
			
			ReservationsStub resList = (ReservationsStub) ec2Env.getReservations();
			Assert.assertEquals(5, resList.get_getReservationCounter());
			
			for(ReservationStub res : resList.getReservations()) {
				Assert.assertEquals(1, res.get_getInstancesCounter());
			}		
		}catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
}



