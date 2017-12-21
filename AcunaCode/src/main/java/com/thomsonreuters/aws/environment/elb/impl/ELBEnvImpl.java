package com.thomsonreuters.aws.environment.elb.impl;

import com.amazonaws.services.elasticloadbalancingv2.AmazonElasticLoadBalancing;
import com.amazonaws.services.elasticloadbalancingv2.AmazonElasticLoadBalancingClientBuilder;
import com.thomsonreuters.aws.environment.elb.IELBEnv;
import com.thomsonreuters.aws.environment.elb.request.targetgroup.IDeregisterTargetsRequest;
import com.thomsonreuters.aws.environment.elb.request.targetgroup.IDescribeTargetGroupsRequest;
import com.thomsonreuters.aws.environment.elb.request.targetgroup.raw.IDeregisterTargetsRequestRaw;
import com.thomsonreuters.aws.environment.elb.request.targetgroup.raw.IDescribeTargetGroupsRequestRaw;
import com.thomsonreuters.aws.targetgroup.ITargetGroups;
import com.thomsonreuters.aws.targetgroup.impl.TargetGroupsImpl;

public class ELBEnvImpl implements IELBEnv {

	private AmazonElasticLoadBalancing env;

	public ELBEnvImpl() {
		env = AmazonElasticLoadBalancingClientBuilder.defaultClient();
	}
	
	@Override
	public ITargetGroups describeTargetGroups(IDescribeTargetGroupsRequest req) {
		IDescribeTargetGroupsRequestRaw raw = (IDescribeTargetGroupsRequestRaw) req;
		return new TargetGroupsImpl(env.describeTargetGroups(raw.getRaw()).getTargetGroups());
	}

	@Override
	public void deregisterTargets(IDeregisterTargetsRequest req) {
		IDeregisterTargetsRequestRaw raw = (IDeregisterTargetsRequestRaw) req;
		env.deregisterTargets(raw.getRaw());
	}

}
