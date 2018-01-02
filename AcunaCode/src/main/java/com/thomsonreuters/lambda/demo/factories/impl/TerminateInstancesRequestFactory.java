package com.thomsonreuters.lambda.demo.factories.impl;

import java.util.List;

import com.thomsonreuters.aws.environment.ec2.request.ITerminateInstancesRequest;
import com.thomsonreuters.aws.environment.ec2.request.TerminateInstancesRequest;
import com.thomsonreuters.lambda.demo.factories.ITerminateInstancesRequestFactory;

public class TerminateInstancesRequestFactory implements ITerminateInstancesRequestFactory {

	private List<String> _instanceIds;
	
	public TerminateInstancesRequestFactory () {
		
	}
	
	@Override
	public void setInstanceIds(List<String> instanceIds) {
		_instanceIds = instanceIds;
		
	}



	@Override
	public ITerminateInstancesRequest createRequest() {
		ITerminateInstancesRequest req = TerminateInstancesRequest.create();
		req.setInstanceIds(_instanceIds);
		return req;
	}

}
