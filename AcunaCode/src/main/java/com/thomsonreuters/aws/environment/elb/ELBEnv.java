package com.thomsonreuters.aws.environment.elb;

import com.thomsonreuters.aws.environment.elb.impl.ELBEnvImpl;

public class ELBEnv {

    public static IELBEnv create() {
        return new ELBEnvImpl();
    }
}
