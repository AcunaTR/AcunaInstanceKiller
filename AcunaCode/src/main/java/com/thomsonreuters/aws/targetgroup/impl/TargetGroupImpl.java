package com.thomsonreuters.aws.targetgroup.impl;

import com.amazonaws.services.elasticloadbalancingv2.model.TargetGroup;
import com.thomsonreuters.aws.targetgroup.ITargetGroup;

public class TargetGroupImpl implements ITargetGroup {

    private final TargetGroup _targetGroup;

    public TargetGroupImpl(TargetGroup targetGroup) {
        _targetGroup = targetGroup;
    }

}
