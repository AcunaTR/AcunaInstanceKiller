package com.thomsonreuters.aws.environment.elb.request.targetgroup.impl;

import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetGroupsRequest;
import com.thomsonreuters.aws.environment.elb.request.targetgroup.IDescribeTargetGroupsRequest;
import com.thomsonreuters.aws.environment.elb.request.targetgroup.raw.IDescribeTargetGroupsRequestRaw;

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
