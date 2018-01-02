package com.thomsonreuters.aws.environment.ec2.request.impl;

import java.util.List;

import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.thomsonreuters.aws.environment.ec2.request.ITerminateInstancesRequest;

public class TerminateInstancesRequestImpl implements ITerminateInstancesRequest, ITerminateInstancesRequestRaw {

	TerminateInstancesRequest _req;
	
	public TerminateInstancesRequestImpl() {
		_req = new TerminateInstancesRequest();
	}
	
	
	@Override
	public TerminateInstancesRequest getRaw() {
		
		return _req;
	}

	@Override
	public void setInstanceIds(List<String> instanceIds) {
		_req.setInstanceIds(instanceIds);
	}

}
