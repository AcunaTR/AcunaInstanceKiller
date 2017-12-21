package com.amazonaws.lambda.demo.stubs;

import java.util.Date;

import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.iam.instanceprofile.IInstanceProfile;
import com.thomsonreuters.aws.tag.ITags;

public class EC2Stub implements IEC2 {

	private ITags _name;
	
	public EC2Stub(String name) {
		_name = new TagsStub(name);
	}

	@Override
	public String getInstanceID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITags getTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getLaunchTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getImageID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInstanceType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IInstanceProfile getIamInstanceProfile() {
		// TODO Auto-generated method stub
		return null;
	}

}
