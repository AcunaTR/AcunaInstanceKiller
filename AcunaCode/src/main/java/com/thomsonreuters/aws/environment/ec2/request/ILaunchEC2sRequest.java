package com.thomsonreuters.aws.environment.ec2.request;

public interface ILaunchEC2sRequest {

    void setAmi(String amiId);

    void setLaunchTemplateARN(String launchTemplateARN);	
}
