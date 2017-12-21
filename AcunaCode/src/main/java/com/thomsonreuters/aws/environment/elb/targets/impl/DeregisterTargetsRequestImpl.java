package com.thomsonreuters.aws.environment.elb.targets.impl;

import com.amazonaws.services.elasticloadbalancingv2.model.DeregisterTargetsRequest;
import com.thomsonreuters.aws.environment.elb.targets.IDeregisterTargetsRequest;

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
