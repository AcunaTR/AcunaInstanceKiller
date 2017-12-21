package com.thomsonreuters.aws.environment.elb.request.targetgroup.raw;

import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetGroupsRequest;

public interface IDescribeTargetGroupsRequestRaw {

	DescribeTargetGroupsRequest getRaw();
	
}
