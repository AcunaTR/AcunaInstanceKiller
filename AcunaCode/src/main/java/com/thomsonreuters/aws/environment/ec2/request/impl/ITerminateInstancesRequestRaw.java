package com.thomsonreuters.aws.environment.ec2.request.impl;

import com.amazonaws.services.ec2.model.TerminateInstancesRequest;

public interface ITerminateInstancesRequestRaw {
	
    TerminateInstancesRequest getRaw();

}
