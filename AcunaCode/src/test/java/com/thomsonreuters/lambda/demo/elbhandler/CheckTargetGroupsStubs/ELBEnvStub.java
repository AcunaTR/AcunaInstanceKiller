package com.thomsonreuters.lambda.demo.elbhandler.CheckTargetGroupsStubs;

import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetHealthResult;
import com.thomsonreuters.aws.environment.elb.IELBEnv;
import com.thomsonreuters.aws.environment.elb.request.IDeregisterTargetsRequest;
import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetGroupsRequest;
import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetHealthRequest;
import com.thomsonreuters.aws.targetgroup.ITargetGroup;
import com.thomsonreuters.aws.targetgroup.ITargetGroups;
import com.thomsonreuters.lambda.demo.stubs.TargetGroupStub;
import com.thomsonreuters.lambda.demo.stubs.TargetGroupsStub;

public class ELBEnvStub implements IELBEnv {
	
	private int _describeTargetGroupsCounter;
	private ITargetGroups _itgs;
	private IDescribeTargetGroupsRequest _req;
	
	public ELBEnvStub() {
		_describeTargetGroupsCounter = 0;
		ITargetGroup itg = new TargetGroupStub("acuna-jenkins-load-ballancer");
		ITargetGroups targetGroups =  new TargetGroupsStub(itg);
		_itgs = targetGroups;
	}
	

	@Override
	public ITargetGroups describeTargetGroups(IDescribeTargetGroupsRequest req) {
		// TODO Auto-generated method stub
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
	
//	public String getTargetGroupName() {
//		_itgs.get(0).
//	}

}
