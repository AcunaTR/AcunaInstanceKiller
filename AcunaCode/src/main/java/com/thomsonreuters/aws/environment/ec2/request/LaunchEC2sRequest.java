package com.thomsonreuters.aws.environment.ec2.request;

import com.thomsonreuters.aws.environment.ec2.request.impl.LaunchEC2sRequestImpl;

public class LaunchEC2sRequest {
    
    public static ILaunchEC2sRequest create() {
        return new LaunchEC2sRequestImpl();
    }
}
