package com.thomsonreuters.aws.environment.ec2.request.ec2.impl;

import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.thomsonreuters.aws.environment.ec2.request.ec2.IDescribeEC2sRequest;
import com.thomsonreuters.aws.environment.ec2.request.ec2.IDescribeEC2sRequestRaw;

public class DescribeEC2sRequestImpl implements IDescribeEC2sRequest, IDescribeEC2sRequestRaw{

	private final DescribeInstancesRequest _req;
	
	public DescribeEC2sRequestImpl() {
		_req = new DescribeInstancesRequest();
	}

	@Override
	public DescribeInstancesRequest getRaw() {
		return _req;
	}
	
	
	
	
}
