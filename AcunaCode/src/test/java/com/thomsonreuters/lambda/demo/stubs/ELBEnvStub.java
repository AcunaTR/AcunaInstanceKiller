package com.thomsonreuters.lambda.demo.stubs;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetHealthResult;
import com.thomsonreuters.aws.environment.elb.IELBEnv;
import com.thomsonreuters.aws.environment.elb.request.IDeregisterTargetsRequest;
import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetGroupsRequest;
import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetHealthRequest;
import com.thomsonreuters.aws.targetgroup.ITargetGroup;
import com.thomsonreuters.aws.targetgroup.ITargetGroups;

public class ELBEnvStub implements IELBEnv {

	
	private int _describeTargetGroupsCounter;
	
	//private IDescribeTargetGroupsRequest _req;
	//private List<ITargetGroup> _targetGroups;
	
	public ELBEnvStub() {
		_describeTargetGroupsCounter = 0;
	}
	

	
	@Override
	public ITargetGroups describeTargetGroups(IDescribeTargetGroupsRequest req) {
		_describeTargetGroupsCounter++;
		ITargetGroups targetGroup =  null;

		//= new ITargetGroups(targetGroup);
		
		 
		/* ITargetGroups targetGroups = (ITargetGroups) _targetGroup;
		_req=req;
	//	return targetGroups; 
		
		return (ITargetGroups) req; */
		return  targetGroup;
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
	
	
//	public void addTargetGroup(ITargetGroup targetGroup) {
//		 _targetGroups.add(targetGroup);
//	}
	
//	public int targetGroupArraySize() {
//		return _targetGroups.size();		
//	}
}
