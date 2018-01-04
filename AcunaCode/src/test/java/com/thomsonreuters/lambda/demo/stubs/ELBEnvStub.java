package com.thomsonreuters.lambda.demo.stubs;

import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetHealthResult;
import com.thomsonreuters.aws.environment.elb.IELBEnv;
import com.thomsonreuters.aws.environment.elb.request.IDeregisterTargetsRequest;
import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetGroupsRequest;
import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetHealthRequest;
import com.thomsonreuters.aws.targetgroup.ITargetGroups;

public class ELBEnvStub implements IELBEnv {

	private IDescribeTargetGroupsRequest _req;
	
	@Override
	public ITargetGroups describeTargetGroups(IDescribeTargetGroupsRequest req) {
		_req = req;
		
		return null;
	}

	@Override
	public void deregisterTargets(IDeregisterTargetsRequest req) {
		// TODO Auto-generated method stub

	}

	@Override
	public DescribeTargetHealthResult describeTargetHealth(IDescribeTargetHealthRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
