package com.thomsonreuters.aws.ec2;

import java.util.Date;

import com.thomsonreuters.aws.iam.instanceprofile.IInstanceProfile;
import com.thomsonreuters.aws.tag.ITags;

public interface IEC2 {

	public String getInstanceID();
	
	public ITags getTags();
	
	public Date getLaunchTime();
	
	public String getImageID();
	
	public String getInstanceType();
	
	public IInstanceProfile getIamInstanceProfile();
}
