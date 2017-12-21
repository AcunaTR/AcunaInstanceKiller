package com.thomsonreuters.aws.environment.elb.targets.impl;

import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetGroupsRequest;

public interface IDescribeTargetGroupsRequestRaw {

	DescribeTargetGroupsRequest getRaw();
	
}
