package com.thomsonreuters.aws.environment.ec2.request;

import java.util.List;

public interface ITerminateInstancesRequest {
	
	void setInstanceIds(List<String> instanceIds);
	
	

}
