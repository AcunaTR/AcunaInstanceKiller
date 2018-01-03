package com.thomsonreuters.aws.environment.elb.request.impl;

import com.amazonaws.services.elasticloadbalancingv2.model.DeregisterTargetsRequest;
import com.thomsonreuters.aws.environment.elb.request.IDeregisterTargetsRequest;

public class DeregisterTargetsRequestImpl implements IDeregisterTargetsRequest, IDeregisterTargetsRequestRaw {
	
    private final DeregisterTargetsRequest _req;

    public DeregisterTargetsRequestImpl() {
        _req = new DeregisterTargetsRequest();
    }

    @Override
    public DeregisterTargetsRequest getRaw() {
        return _req;
    }
    
    @Override
	public String toString() {
		return _req.toString();
	}
}
