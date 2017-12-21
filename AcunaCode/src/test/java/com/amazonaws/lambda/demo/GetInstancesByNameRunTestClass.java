package com.amazonaws.lambda.demo;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.lambda.demo.stubs.EC2EnvStub;
import com.amazonaws.lambda.demo.stubs.EC2Stub;
import com.amazonaws.lambda.demo.stubs.EC2sStub;
import com.amazonaws.lambda.demo.stubs.ReservationStub;
import com.amazonaws.lambda.demo.stubs.ReservationsStub;
import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.environment.ec2.IEC2Env;
import com.thomsonreuters.aws.reservation.IReservation;
import com.thomsonreuters.aws.reservation.IReservations;
import com.thomsonreuters.lambda.demo.GetInstancesByName;
import com.thomsonreuters.lambda.demo.exceptions.InvalidInstancesException;

public class GetInstancesByNameRunTestClass {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testRunOneValidEC2() {
		IEC2 ec2 = new EC2Stub("random.server.name");
		IEC2s ec2s = new EC2sStub(ec2);
		
		IReservation reservation = new ReservationStub(ec2s);
		IReservations reservations = new ReservationsStub(reservation);
		
		IEC2Env ec2Env = new EC2EnvStub(reservations);
		
		IEC2s result;
		try {
			result = GetInstancesByName.run(ec2Env, "random.server.name");
			Assert.assertEquals(1, result.size());
			Assert.assertEquals("random.server.name", result.get(0).getTags().get(0).getValue());
			
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}

	}
	
	@Test
	public void testRunMultipleValidEC2s() {
		IEC2 ec2a = new EC2Stub("random.server.name");
		IEC2 ec2b = new EC2Stub("random.server.name");
		IEC2 ec2c = new EC2Stub("random.server.name");
		IEC2s ec2s = new EC2sStub(Arrays.asList(ec2a, ec2b, ec2c));
		
		IReservation reservation = new ReservationStub(ec2s);
		IReservations reservations = new ReservationsStub(reservation);
		
		IEC2Env ec2Env = new EC2EnvStub(reservations);
		
		IEC2s result;
		try {
			result = GetInstancesByName.run(ec2Env, "random.server.name");
			Assert.assertEquals(3, result.size());
			
			for(int i=0; i<result.size(); i++) {
				Assert.assertEquals("random.server.name", result.get(i).getTags().get(0).getValue());
			}
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}

	}
	
	@Test
	public void testRunNoEC2s() {
		//IEC2s ec2s = new EC2sStub(new ArrayList<>());
		//IReservation reservation = new ReservationStub(ec2s);
		IReservations reservations = new ReservationsStub(new ArrayList<>());
		IEC2Env ec2Env = new EC2EnvStub(reservations);
		try {
			IEC2s result = GetInstancesByName.run(ec2Env, "random.server.name");
			fail("Expected 'No Instances' exception not thrown " );
		}
		catch (NoInstancesException e) {
			Assert.assertTrue(true);			
		}
		catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
	}

	@Test
	public void testRunMultipleInvalidEC2s() {
		IEC2 ec2a = new EC2Stub("other.name");
		IEC2 ec2b = new EC2Stub("Random.Server.Name");
		IEC2 ec2c = new EC2Stub("21");
		IEC2s ec2s = new EC2sStub(Arrays.asList(ec2a, ec2b, ec2c));
		
		IReservation reservation = new ReservationStub(ec2s);
		IReservations reservations = new ReservationsStub(reservation);
		
		IEC2Env ec2Env = new EC2EnvStub(reservations);
		try {
			IEC2s result = GetInstancesByName.run(ec2Env, "random.server.name");
			fail("Expected 'No Instances' exception not thrown " );
		}
		catch (InvalidInstancesException e) {
			Assert.assertTrue(true);			
		}
		catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testRunMixedValidityEC2s() {
		IEC2 ec2a = new EC2Stub("Random.Server.Name");
		IEC2 ec2b = new EC2Stub("random.server.name");
		IEC2 ec2c = new EC2Stub("Jim");
		IEC2 ec2d = new EC2Stub("random.server.name ");
		IEC2s ec2s = new EC2sStub(Arrays.asList(ec2a, ec2b, ec2c));
		
		IReservation reservation = new ReservationStub(ec2s);
		IReservations reservations = new ReservationsStub(reservation);
		
		IEC2Env ec2Env = new EC2EnvStub(reservations);
		
		IEC2s result;
		try {
			result = GetInstancesByName.run(ec2Env, "random.server.name");
			Assert.assertEquals(1, result.size());
			
			for(int i=0; i<result.size(); i++) {
				Assert.assertEquals("random.server.name", result.get(i).getTags().get(0).getValue());
			}
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
}
