package com.thomsonreuters.aws.targetgroup.impl;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetHealthResult;
import com.amazonaws.services.elasticloadbalancingv2.model.TargetGroup;
import com.amazonaws.services.elasticloadbalancingv2.model.TargetHealthDescription;
import com.thomsonreuters.aws.environment.elb.IELBEnv;
import com.thomsonreuters.aws.environment.elb.request.DescribeTargetHealthRequest;
import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetHealthRequest;
import com.thomsonreuters.aws.targetgroup.ITargetGroup;

public class TargetGroupImpl implements ITargetGroup {

    private final TargetGroup _targetGroup;

    public TargetGroupImpl(TargetGroup targetGroup) {
        _targetGroup = targetGroup;
    }

	@Override
	public String getArn() {
		return _targetGroup.getTargetGroupArn();
	}

	@Override
	public List<String> getTargetIDs(IELBEnv elbEnv) {
		IDescribeTargetHealthRequest request = DescribeTargetHealthRequest.create();
		request.setArn(_targetGroup.getTargetGroupArn());
		
		DescribeTargetHealthResult res = elbEnv.describeTargetHealth(request);
		
        List <TargetHealthDescription> targetHealth = res.getTargetHealthDescriptions();
        List<String> ids = new ArrayList<>();
        
        for (TargetHealthDescription targetH : targetHealth) {
            ids.add(targetH.getTarget().getId());
        }
		return ids;
	}

	@Override
	public String toString() {
		return _targetGroup.toString();
	}
}
