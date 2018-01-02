package com.thomsonreuters.lambda.demo.stubs;

import java.util.Date;

import com.amazonaws.services.ec2.model.Instance;
import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.ec2.impl.IEC2Raw;
import com.thomsonreuters.aws.iam.instanceprofile.IInstanceProfile;
import com.thomsonreuters.aws.tag.ITags;

public class EC2Stub implements IEC2, IEC2Raw {

	private ITags _name;
	private Date _launchTime;
	
	public EC2Stub(String name, Date launchtime) {
		_name = new TagsStub(name);	
		_launchTime = launchtime;
	}

	@Override
	public String getInstanceID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITags getTags() {
		return _name;
	}
	
	@Override
	public Date getLaunchTime() {
		return _launchTime;
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

	@Override
	public Instance getRaw() {
		return null;
	}

}
