package com.amazonaws.lambda.demo.getinstancesbyname;

import static org.junit.Assert.*;

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
import com.thomsonreuters.aws.reservation.IReservation;
import com.thomsonreuters.aws.reservation.IReservations;
import com.thomsonreuters.lambda.demo.GetInstancesByName;


public class CreateRequestTestClass {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateRequestObject() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		
		IEC2 ec2 = new EC2Stub("random.server.name");
		IEC2s ec2s = new EC2sStub(ec2);
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);
		
		try {
			GetInstancesByName.run(ec2Env, null, reqFactory);
			Assert.assertEquals(1, reqFactory.getCreateRequestCallCount());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}

	@Test
	public void testAddFiltersCalled() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		
		IEC2 ec2 = new EC2Stub("random.server.name");
		IEC2s ec2s = new EC2sStub(ec2);
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);
		
		try {
			GetInstancesByName.run(ec2Env, null, reqFactory);
			Assert.assertEquals(1, reqFactory.getSetFilterorFiltersCount());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testAddCorrectFilterValue() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		
		IEC2 ec2 = new EC2Stub("random.server.name");
		IEC2s ec2s = new EC2sStub(ec2);
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);
		
		try {
			GetInstancesByName.run(ec2Env, "Tuesday", reqFactory);
			Assert.assertEquals(1, reqFactory.getFilters().size());
			Assert.assertEquals("Tuesday", reqFactory.getFilters().get(0).getValues().get(0));
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testAddCorrectFilterName() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		
		IEC2 ec2 = new EC2Stub("random.server.name");
		IEC2s ec2s = new EC2sStub(ec2);
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);
		try {
			GetInstancesByName.run(ec2Env, "Tuesday", reqFactory);
			Assert.assertEquals(1, reqFactory.getFilters().size());
			Assert.assertEquals("tag:Name", reqFactory.getFilters().get(0).getName());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
}
