package com.thomsonreuters.aws.environment.sns;

import com.thomsonreuters.aws.environment.sns.impl.SNSEnvImpl;

public class SNSEnv {

    public static ISNSEnv create() {
        return new SNSEnvImpl();
    }
}
