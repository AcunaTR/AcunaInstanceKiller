package com.thomsonreuters.aws.iam.instanceprofile.impl;

import com.amazonaws.services.ec2.model.IamInstanceProfile;
import com.thomsonreuters.aws.iam.instanceprofile.IInstanceProfile;

public class InstanceProfileImpl implements IInstanceProfile {

	private final IamInstanceProfile _instProf;
	
	public InstanceProfileImpl(IamInstanceProfile iamInstanceProfile) {
		_instProf = iamInstanceProfile;
	}

	@Override
	public String getID() {
		return _instProf.getId();
	}

	@Override
	public String getArn() {
		return _instProf.getArn();
	}

}
