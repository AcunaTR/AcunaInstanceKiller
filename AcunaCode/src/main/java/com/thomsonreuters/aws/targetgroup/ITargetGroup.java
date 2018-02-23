package com.thomsonreuters.aws.targetgroup;

import java.util.List;

import com.thomsonreuters.aws.environment.elb.IELBEnv;

public interface ITargetGroup {

    String getArn();

    List<String> getTargetIDs(IELBEnv elbEnv);
}
