package com.thomsonreuters.lambda.demo.stubs;

import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetGroupsRequest;
import com.thomsonreuters.lambda.demo.factories.IDescribeTargetGroupsRequestFactory;

public class DescribeTargetGroupsRequestFactoryStub implements IDescribeTargetGroupsRequestFactory {

	private static int _setNameCounter; 
	private IDescribeTargetGroupsRequest _req;
	private String _name;
	private static int _createRequestCounter;
	
	
	
	public DescribeTargetGroupsRequestFactoryStub(IDescribeTargetGroupsRequest reqStub) {
		_setNameCounter = 0;
		_createRequestCounter = 0;
		_req = reqStub;
	}

	@Override
	public void setName(String name) {
		_setNameCounter ++;
		_name = name;
	}

	@Override
	public IDescribeTargetGroupsRequest createRequest() {
		_createRequestCounter ++;
		return _req;
	}
	
	
	public int getNameCounter() {
		return _setNameCounter;

	}

	public String getName() {
		return _name;
	}
	
	public int getCreateRequestCounter() {
		return _createRequestCounter;
	}

}
