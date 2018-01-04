package com.thomsonreuters.lambda.demo.stubs;

import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetGroupsRequest;
import com.thomsonreuters.lambda.demo.factories.IDescribeTargetGroupsRequestFactory;

public class DescribeTargetGroupsRequestFactoryStub implements IDescribeTargetGroupsRequestFactory {

	private static int _setNameCounter; 
	private IDescribeTargetGroupsRequest _req;
	
	
	public DescribeTargetGroupsRequestFactoryStub(IDescribeTargetGroupsRequest reqStub) {
		_setNameCounter = 0;
		_req = reqStub;
	}

	@Override
	public void setName(String name) {
		_setNameCounter ++;

	}

	@Override
	public IDescribeTargetGroupsRequest createRequest() {
		return _req;
	}
	
	
	public int getNameCounter() {
		return _setNameCounter;

	}

}
