package com.thomsonreuters.lambda.demo.factories.impl;

import com.thomsonreuters.aws.environment.elb.request.DescribeTargetGroupsRequest;
import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetGroupsRequest;
import com.thomsonreuters.lambda.demo.factories.IDescribeTargetGroupsRequestFactory;

public class DescribeTargetGroupsRequestFactory implements IDescribeTargetGroupsRequestFactory {

	private String _name;
	
	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public IDescribeTargetGroupsRequest createRequest() {
		IDescribeTargetGroupsRequest req = DescribeTargetGroupsRequest.create();
		req.setName(_name);
		return req;
	}

}
