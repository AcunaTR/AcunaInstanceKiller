package com.thomsonreuters.lambda.demo.factories;

import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetGroupsRequest;

public interface IDescribeTargetGroupsRequestFactory {

	void setName(String name);
	IDescribeTargetGroupsRequest createRequest();
	
}
