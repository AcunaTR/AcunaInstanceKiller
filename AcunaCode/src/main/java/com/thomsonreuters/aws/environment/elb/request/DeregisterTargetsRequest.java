package com.thomsonreuters.aws.environment.elb.request;

import com.thomsonreuters.aws.environment.elb.request.impl.DeregisterTargetsRequestImpl;

public class DeregisterTargetsRequest {

    public static IDeregisterTargetsRequest create() {
        return new DeregisterTargetsRequestImpl();
    }
}
