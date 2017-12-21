package com.thomsonreuters.aws.ec2.impl;

import java.util.Date;

import com.amazonaws.services.ec2.model.Instance;
import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.iam.instanceprofile.IInstanceProfile;
import com.thomsonreuters.aws.iam.instanceprofile.impl.InstanceProfileImpl;
import com.thomsonreuters.aws.tag.ITags;
import com.thomsonreuters.aws.tag.impl.TagsImpl;

public class EC2Impl implements IEC2{

	private final Instance _ec2;
	
	public EC2Impl(Instance instance) {
		_ec2 = instance;
	}

	@Override
	public String getInstanceID() {
		return _ec2.getInstanceId();
	}

	@Override
	public ITags getTags() {
		return new TagsImpl(_ec2.getTags());
	}

	@Override
	public Date getLaunchTime() {
		return _ec2.getLaunchTime();
	}

	@Override
	public String getImageID() {
		return _ec2.getImageId();
	}

	@Override
	public String getInstanceType() {
		return _ec2.getInstanceType();
	}

	@Override
	public IInstanceProfile getIamInstanceProfile() {
		return new InstanceProfileImpl(_ec2.getIamInstanceProfile());
	}
	

}
