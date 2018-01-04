package com.thomsonreuters.lambda.demo.stubs;

import java.util.ArrayList;
import java.util.List;

import com.thomsonreuters.aws.targetgroup.ITargetGroup;
import com.thomsonreuters.aws.targetgroup.ITargetGroups;

public class TargetGroupsStub implements ITargetGroups {
	
	private List<ITargetGroup> _itgs;
	
	public TargetGroupsStub(ITargetGroup itg) {
		_itgs = new ArrayList<>();
		_itgs.add(itg);
	}
	
	public void addTargetGroup(ITargetGroup itg) {
		_itgs.add(itg);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ITargetGroup get(int idx) {
		return _itgs.get(idx);
	}


	public void add(ITargetGroup targetGroup) {
		// TODO Auto-generated method stub
		
	}

}
