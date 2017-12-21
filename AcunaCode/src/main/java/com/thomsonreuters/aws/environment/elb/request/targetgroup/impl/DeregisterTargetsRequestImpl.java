package com.thomsonreuters.aws.environment.elb.request.targetgroup.impl;

import com.amazonaws.services.elasticloadbalancingv2.model.DeregisterTargetsRequest;
import com.thomsonreuters.aws.environment.elb.request.targetgroup.IDeregisterTargetsRequest;
import com.thomsonreuters.aws.environment.elb.request.targetgroup.raw.IDeregisterTargetsRequestRaw;

public class DeregisterTargetsRequestImpl implements IDeregisterTargetsRequest, IDeregisterTargetsRequestRaw {
	
	private final DeregisterTargetsRequest _req;
	
	public DeregisterTargetsRequestImpl() {
		_req = new DeregisterTargetsRequest();
	}
	
	@Override
	public DeregisterTargetsRequest getRaw() {
		return _req;
	}

}
