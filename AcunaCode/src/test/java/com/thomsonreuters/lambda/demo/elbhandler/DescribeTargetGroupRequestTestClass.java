package com.thomsonreuters.lambda.demo.elbhandler;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetGroupsRequest;
import com.thomsonreuters.lambda.demo.ELBHandler;
import com.thomsonreuters.lambda.demo.stubs.DescribeTargetGroupsRequestFactoryStub;
import com.thomsonreuters.lambda.demo.stubs.DescribeTargetGroupsRequestStub;
import com.thomsonreuters.lambda.demo.stubs.ELBEnvStub;




public class DescribeTargetGroupRequestTestClass {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateTargetGroupRequestObject() {
		IDescribeTargetGroupsRequest reqStub = new DescribeTargetGroupsRequestStub();
		DescribeTargetGroupsRequestFactoryStub reqFactory = new DescribeTargetGroupsRequestFactoryStub(reqStub);
		ELBEnvStub elbEnv = new ELBEnvStub();
		
		try {
			ELBHandler.getTargetGroup(elbEnv, reqFactory);
			fail("Expected NullPointerException not thrown");
		} catch (NullPointerException e) {
			Assert.assertEquals(1,reqFactory.getNameCounter());
		} catch (Exception e) {
			fail("Unexpected exception - " + reqFactory.getNameCounter() +" - " + e.getMessage());
		}
	}
	
	@Test
	public void testCorrectNameSet() {
		IDescribeTargetGroupsRequest reqStub = new DescribeTargetGroupsRequestStub();
		DescribeTargetGroupsRequestFactoryStub reqFactory = new DescribeTargetGroupsRequestFactoryStub(reqStub);
		ELBEnvStub elbEnv = new ELBEnvStub();
		
		try {
			ELBHandler.getTargetGroup(elbEnv, reqFactory);
			fail("Expected NullPointerException not thrown");
		} catch (NullPointerException e) {
			Assert.assertEquals("acuna-jenkins-load-ballancer",reqFactory.getName());
		} catch (Exception e) {
			fail("Unexpected exception - " + reqFactory.getName() +" - " + e.getMessage());
		}
	}

	@Test
	public void testCreateRequestRan() {
		IDescribeTargetGroupsRequest reqStub = new DescribeTargetGroupsRequestStub();
		DescribeTargetGroupsRequestFactoryStub reqFactory = new DescribeTargetGroupsRequestFactoryStub(reqStub);
		ELBEnvStub elbEnv = new ELBEnvStub();
		
		try {
			ELBHandler.getTargetGroup(elbEnv, reqFactory);
			fail("Expected NullPointerException not thrown");
		} catch (NullPointerException e) {
			Assert.assertEquals(1,reqFactory.getCreateRequestCounter());
		} catch (Exception e) {
			fail("Unexpected exception - " + reqFactory.getCreateRequestCounter() +" - " + e.getMessage());
		}
	}
}
