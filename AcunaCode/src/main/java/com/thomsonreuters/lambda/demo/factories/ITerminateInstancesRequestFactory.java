package com.thomsonreuters.lambda.demo.factories;

import java.util.List;

import com.thomsonreuters.aws.environment.ec2.request.ITerminateInstancesRequest;

public interface ITerminateInstancesRequestFactory {

	void setInstanceIds(List<String> instanceIds);
	ITerminateInstancesRequest createRequest();
}
