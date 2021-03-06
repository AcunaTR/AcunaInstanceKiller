package com.thomsonreuters.aws.environment.elb.impl;

import com.amazonaws.services.elasticloadbalancingv2.AmazonElasticLoadBalancing;
import com.amazonaws.services.elasticloadbalancingv2.AmazonElasticLoadBalancingClientBuilder;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetHealthResult;

import com.thomsonreuters.aws.environment.elb.IELBEnv;
import com.thomsonreuters.aws.environment.elb.request.*;
import com.thomsonreuters.aws.environment.elb.request.impl.IDeregisterTargetsRequestRaw;
import com.thomsonreuters.aws.environment.elb.request.impl.IDescribeTargetGroupsRequestRaw;
import com.thomsonreuters.aws.environment.elb.request.impl.IDescribeTargetHealthRequestRaw;
import com.thomsonreuters.aws.targetgroup.ITargetGroups;
import com.thomsonreuters.aws.targetgroup.impl.TargetGroupsImpl;

public class ELBEnvImpl implements IELBEnv {

    private final AmazonElasticLoadBalancing _env;

    public ELBEnvImpl() {
        _env = AmazonElasticLoadBalancingClientBuilder.defaultClient();
    }

    @Override
    public ITargetGroups describeTargetGroups(IDescribeTargetGroupsRequest req) {
        IDescribeTargetGroupsRequestRaw raw = (IDescribeTargetGroupsRequestRaw) req;
        return new TargetGroupsImpl(_env.describeTargetGroups(raw.getRaw()).getTargetGroups());
    }

    @Override
    public void deregisterTargets(IDeregisterTargetsRequest req) {
        IDeregisterTargetsRequestRaw raw = (IDeregisterTargetsRequestRaw) req;
        _env.deregisterTargets(raw.getRaw());
    }

    @Override
    public DescribeTargetHealthResult describeTargetHealth(IDescribeTargetHealthRequest request) {
        IDescribeTargetHealthRequestRaw raw = (IDescribeTargetHealthRequestRaw) request;
        return _env.describeTargetHealth(raw.getRaw());
    }

    @Override
    public String toString() {
        return _env.toString();
    }
}
