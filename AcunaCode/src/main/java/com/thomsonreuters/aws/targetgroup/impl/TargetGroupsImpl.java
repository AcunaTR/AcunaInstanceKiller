package com.thomsonreuters.aws.targetgroup.impl;

import java.util.List;

import com.amazonaws.services.elasticloadbalancingv2.model.TargetGroup;
import com.thomsonreuters.aws.targetgroup.ITargetGroup;
import com.thomsonreuters.aws.targetgroup.ITargetGroups;

public class TargetGroupsImpl implements ITargetGroups {

	private final List<TargetGroup> _targetGroups;
	
	public TargetGroupsImpl(List<TargetGroup> list) {
		_targetGroups = list;
	}

	@Override
	public int size() {
		return _targetGroups.size();
	}

	@Override
	public boolean isEmpty() {
		return _targetGroups.isEmpty();
	}

	@Override
	public ITargetGroup get(int idx) {
		return new TargetGroupImpl(_targetGroups.get(idx));
	}

}
