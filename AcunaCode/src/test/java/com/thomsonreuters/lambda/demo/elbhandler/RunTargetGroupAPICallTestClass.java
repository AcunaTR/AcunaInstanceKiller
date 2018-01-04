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

public class RunTargetGroupAPICallTestClass {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTargetGroupAPICallRan() {
		IDescribeTargetGroupsRequest reqStub = new DescribeTargetGroupsRequestStub();
		DescribeTargetGroupsRequestFactoryStub reqFactory = new DescribeTargetGroupsRequestFactoryStub(reqStub);
		ELBEnvStub elbEnv = new ELBEnvStub();
		
		
		try {
			ELBHandler.getTargetGroup(elbEnv, reqFactory);
		} catch (NullPointerException e) {
			Assert.assertEquals(1,elbEnv.getDescribeTargetGroupsCounter());			
		} catch (Exception e) {
		fail("Unexpected exception - " + e.getMessage());
		}
	}

}
