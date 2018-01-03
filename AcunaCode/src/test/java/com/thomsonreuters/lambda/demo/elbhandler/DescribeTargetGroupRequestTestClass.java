package com.thomsonreuters.lambda.demo.elbhandler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetGroupsRequest;
import com.thomsonreuters.lambda.demo.stubs.DescribeTargetGroupsRequestFactoryStub;
import com.thomsonreuters.lambda.demo.stubs.DescribeTargetGroupsRequestStub;

public class DescribeTargetGroupRequestTestClass {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateTargetGroupRequestObject() {
		IDescribeTargetGroupsRequest reqStub = new DescribeTargetGroupsRequestStub();
		DescribeTargetGroupsRequestFactoryStub reqFactory = new DescribeTargetGroupsRequestFactoryStub(reqStub);
		
		
		
		
		
		fail("Not yet implemented");
	}

}
