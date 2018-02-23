package com.thomsonreuters.lambda.demo.stubs;

import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetHealthResult;
import com.thomsonreuters.aws.environment.elb.IELBEnv;
import com.thomsonreuters.aws.environment.elb.request.IDeregisterTargetsRequest;
import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetGroupsRequest;
import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetHealthRequest;
import com.thomsonreuters.aws.targetgroup.ITargetGroups;

public class ELBEnvStub implements IELBEnv {

	
	private int _describeTargetGroupsCounter;
	private ITargetGroups _itgs;
	private IDescribeTargetGroupsRequest _req;
	
	//private IDescribeTargetGroupsRequest _req;
	//private List<ITargetGroup> _targetGroups;
	
	public ELBEnvStub() {
		_describeTargetGroupsCounter = 0;
	//	ITargetGroup itg = new TargetGroupStub(name);
	//	ITargetGroups targetGroups =  new TargetGroupsStub(itg);
	//	_itgs = targetGroups;
	}
	

	
	@Override
	public ITargetGroups describeTargetGroups(IDescribeTargetGroupsRequest req) {
		_describeTargetGroupsCounter++;	
	//	_request = req;
		return  _itgs;
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

	public int getDescribeTargetGroupsCounter() {
		return 	_describeTargetGroupsCounter;
	}
	

//	public void addTargetGroups(ITargetGroup targetGroup) {
//	 _itgs.add(targetGroup);
//	}
	
//	public int targetGroupArraySize() {
//		return _targetGroups.size();		
//	}
}
