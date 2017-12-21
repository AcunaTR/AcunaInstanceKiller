package com.thomsonreuters.aws.environment.elb.targets.impl;

import com.amazonaws.services.elasticloadbalancingv2.model.DeregisterTargetsRequest;

public interface IDeregisterTargetsRequestRaw {

	DeregisterTargetsRequest getRaw();
	
}
