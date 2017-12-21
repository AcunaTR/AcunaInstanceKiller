package com.thomsonreuters.aws.environment.elb;

import com.thomsonreuters.aws.environment.elb.request.targetgroup.IDeregisterTargetsRequest;
import com.thomsonreuters.aws.environment.elb.request.targetgroup.IDescribeTargetGroupsRequest;
import com.thomsonreuters.aws.targetgroup.ITargetGroups;

public interface IELBEnv {

	ITargetGroups describeTargetGroups(IDescribeTargetGroupsRequest req);
	
	void deregisterTargets(IDeregisterTargetsRequest req);
	
}
