package com.thomsonreuters.lambda.demo.instancehandler;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.aws.reservation.IReservation;
import com.thomsonreuters.aws.reservation.IReservations;
import com.thomsonreuters.lambda.demo.InstanceHandler;
import com.thomsonreuters.lambda.demo.stubs.DescribeEC2sRequestFactoryStub;
import com.thomsonreuters.lambda.demo.stubs.DescribeEC2sRequestStub;
import com.thomsonreuters.lambda.demo.stubs.EC2EnvStub;
import com.thomsonreuters.lambda.demo.stubs.EC2Stub;
import com.thomsonreuters.lambda.demo.stubs.EC2sStub;
import com.thomsonreuters.lambda.demo.stubs.ReservationStub;
import com.thomsonreuters.lambda.demo.stubs.ReservationsStub;


public class CreateRequestTestClass {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateRequestObject() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		
		IEC2 ec2 = new EC2Stub("random.server.name",null);
		EC2sStub ec2s = new EC2sStub(ec2);
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);
		
		try {
			InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
			Assert.assertEquals(1, reqFactory.getCreateRequestCallCount());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}

	@Test
	public void testAddFiltersCalled() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		
		IEC2 ec2 = new EC2Stub("random.server.name",null);
		EC2sStub ec2s = new EC2sStub(ec2);
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);
		
		try {
			InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
			Assert.assertEquals(1, reqFactory.getSetFilterorFiltersCount());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testAddCorrectFilterValue() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		
		IEC2 ec2 = new EC2Stub("random.server.name",null);
		EC2sStub ec2s = new EC2sStub(ec2);
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);
		
		try {
			InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
			Assert.assertEquals(1, reqFactory.getFilters().size());
			Assert.assertEquals("random.server.name", reqFactory.getFilters().get(0).getValues().get(0));
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testAddCorrectFilterName() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		
		IEC2 ec2 = new EC2Stub("random.server.name",null);
		EC2sStub ec2s = new EC2sStub(ec2);
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		EC2EnvStub ec2Env = new EC2EnvStub(reservations);
		try {
			InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
			Assert.assertEquals(1, reqFactory.getFilters().size());
			Assert.assertEquals("tag:Name", reqFactory.getFilters().get(0).getName());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
}
