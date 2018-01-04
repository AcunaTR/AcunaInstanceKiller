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
		//reqFactory.setName("acuna-jenkins-load-ballancer");
		
		
		try {
			ELBHandler.getTargetGroup(elbEnv, reqFactory);
			Assert.assertEquals(1,reqFactory.getNameCounter());
			//has name run, is name right
		} catch (Exception e) {
			fail("Unexpected exception - " + reqFactory.getNameCounter() +" - " + e.getMessage());
		}
	}

}
