package com.thomsonreuters.lambda.demo.stubs;

import java.util.List;

import com.thomsonreuters.aws.environment.elb.IELBEnv;
import com.thomsonreuters.aws.targetgroup.ITargetGroup;

public class TargetGroupStub implements ITargetGroup {
	
	private String _name;
	
	public TargetGroupStub(String name) {
		_name = name;
	}

	@Override
	public String getArn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getTargetIDs(IELBEnv elbEnv) {
		// TODO Auto-generated method stub
		return null;
	}

}
