package com.thomsonreuters.aws.environment.elb.targets.impl;

import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetGroupsRequest;
import com.thomsonreuters.aws.environment.elb.targets.IDescribeTargetGroupsRequest;

public class DescribeTargetGroupsRequestImpl implements IDescribeTargetGroupsRequest, IDescribeTargetGroupsRequestRaw {

	private final DescribeTargetGroupsRequest _req;
	
	public DescribeTargetGroupsRequestImpl() {
		_req = new DescribeTargetGroupsRequest();
	}
	
	@Override
	public DescribeTargetGroupsRequest getRaw() {
		return _req;
	}
}
