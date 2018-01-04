package com.thomsonreuters.lambda.demo.stubs;

import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetGroupsRequest;

public class DescribeTargetGroupsRequestStub implements IDescribeTargetGroupsRequest {

	private String _name;
	
	@Override
	public void setName(String name) {
		_name = name;

	}
	
	//@Override
	private String getName(IDescribeTargetGroupsRequest req) {
		return _name;
		
	}
		

	

}
