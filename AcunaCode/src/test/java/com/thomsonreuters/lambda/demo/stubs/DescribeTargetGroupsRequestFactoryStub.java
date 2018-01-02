package com.thomsonreuters.lambda.demo.stubs;

import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetGroupsRequest;
import com.thomsonreuters.lambda.demo.factories.IDescribeTargetGroupsRequestFactory;

public class DescribeTargetGroupsRequestFactoryStub implements IDescribeTargetGroupsRequestFactory {

	public DescribeTargetGroupsRequestFactoryStub(ELBEnvStub elbEnv) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public IDescribeTargetGroupsRequest createRequest() {
		// TODO Auto-generated method stub
		return null;
	}

}
