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
	public void testAddCorrectFilterValue() {
		IDescribeEC2sRequest reqStub = new DescribeEC2sRequestStub();
		DescribeEC2sRequestFactoryStub reqFactory = new DescribeEC2sRequestFactoryStub(reqStub);
		try {
			GetInstancesByName.run(null, "Tuesday", reqFactory);
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
		try {
			GetInstancesByName.run(null, "Tuesday", reqFactory);
			Assert.assertEquals(1, reqFactory.getFilters().size());
			Assert.assertEquals("tag:Name", reqFactory.getFilters().get(0).getName());
		} catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}
}
