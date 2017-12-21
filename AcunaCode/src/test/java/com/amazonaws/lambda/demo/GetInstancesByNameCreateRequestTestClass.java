package com.amazonaws.lambda.demo;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.lambda.demo.stubs.DescribeEC2sRequestFactoryStub;
import com.amazonaws.lambda.demo.stubs.DescribeEC2sRequestStub;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.lambda.demo.GetInstancesByName;


public class GetInstancesByNameCreateRequestTestClass {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateRequestObject() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		
		try {
			GetInstancesByName.run(null, null, reqFactory);
			Assert.assertEquals(1, reqFactory.getCreateRequestCallCount());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}

	@Test
	public void testAddFiltersCalled() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		try {
			GetInstancesByName.run(null, null, reqFactory);
			Assert.assertEquals(1, reqFactory.getSetFilterorFiltersCount());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
	
	@Test
	public void testAddCorrectFilter() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		try {
			GetInstancesByName.run(null, "Tuesday", reqFactory);
			Assert.assertEquals("Tuesday", reqFactory.  );
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
}
