package com.thomsonreuters.aws.environment.elb.request.impl;

import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetHealthRequest;
import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetHealthRequest;

public class DescribeTargetHealthRequestImpl implements IDescribeTargetHealthRequest, IDescribeTargetHealthRequestRaw {

	private DescribeTargetHealthRequest _req;
	
	public DescribeTargetHealthRequestImpl(){
		_req = new DescribeTargetHealthRequest();
	}
	
	@Override
	public DescribeTargetHealthRequest getRaw() {
		return _req;
	}

	@Override
	public void setArn(String targetGroupArn) {
		_req.setTargetGroupArn(targetGroupArn);
	}

}
