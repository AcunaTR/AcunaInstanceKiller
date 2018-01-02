package com.thomsonreuters.lambda.demo.instancehandler;

import static org.junit.Assert.fail;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.environment.ec2.IEC2Env;
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

public class RunTestClass {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testRunOneValidEC2() {
		IEC2 ec2 = new EC2Stub("random.server.name",null);
		EC2sStub ec2s = new EC2sStub(ec2);
		
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		IEC2Env ec2Env = new EC2EnvStub(reservations);
		
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		try {
			IEC2s result = InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
			Assert.assertEquals(1, result.size());
			Assert.assertEquals("random.server.name", result.get(0).getTags().getValue("tag:Name"));
			
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}

	}
	
	@Test
	public void testRunMultipleValidEC2s() {
		IEC2 ec2a = new EC2Stub("random.server.name",null);
		IEC2 ec2b = new EC2Stub("random.server.name",null);
		IEC2 ec2c = new EC2Stub("random.server.name",null);
		EC2sStub ec2s = new EC2sStub(Arrays.asList(ec2a, ec2b, ec2c));
		
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		IEC2Env ec2Env = new EC2EnvStub(reservations);
		
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		try {
			IEC2s result = InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
			Assert.assertEquals(3, result.size());
			
			for(int i=0; i<result.size(); i++) {
				Assert.assertEquals("random.server.name", result.get(i).getTags().getValue("tag:Name"));
			}
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}

	}
	
	@Test
	public void testRunNoEC2s() {
		//IEC2s ec2s = new EC2sStub(new ArrayList<>());
		//IReservation reservation = new ReservationStub(ec2s);
		ReservationStub reservation = new ReservationStub(null);
		ReservationsStub reservations = new ReservationsStub(reservation);
		IEC2Env ec2Env = new EC2EnvStub(reservations);
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		try {
			InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
			fail("Expected 'No Instances' exception not thrown " );
		}
		catch (EmptyReservationException e) {
			Assert.assertTrue(true);			
		}
		catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
		
	}

	@Test
	public void testRunMultipleInvalidEC2s() {
		IEC2 ec2a = new EC2Stub("other.name",null);
		IEC2 ec2b = new EC2Stub("Random.Server.Name",null);
		IEC2 ec2c = new EC2Stub("21",null);
		EC2sStub ec2s = new EC2sStub(Arrays.asList(ec2a, ec2b, ec2c));
		
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		IEC2Env ec2Env = new EC2EnvStub(reservations);
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		try {
			InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
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
		IEC2 ec2a = new EC2Stub("Random.Server.Name",null);
		IEC2 ec2b = new EC2Stub("random.server.name",null);
		IEC2 ec2c = new EC2Stub("Jim",null);
		IEC2 ec2d = new EC2Stub("random.server.name ",null);
		EC2sStub ec2s = new EC2sStub(Arrays.asList(ec2a, ec2b, ec2c, ec2d));
		
		ReservationStub reservation = new ReservationStub(ec2s);
		ReservationsStub reservations = new ReservationsStub(reservation);
		
		IEC2Env ec2Env = new EC2EnvStub(reservations);
		
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		try {
			InstanceHandler.getInstanceByTagname(ec2Env, "random.server.name", reqFactory);
		}catch(InvalidInstancesException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
}
