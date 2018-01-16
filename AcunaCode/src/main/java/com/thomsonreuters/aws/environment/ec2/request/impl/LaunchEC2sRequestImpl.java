package com.thomsonreuters.aws.environment.ec2.request.impl;

import com.amazonaws.services.ec2.model.LaunchTemplateSpecification;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.thomsonreuters.aws.environment.ec2.request.ILaunchEC2sRequest;

public class LaunchEC2sRequestImpl implements ILaunchEC2sRequest, ILaunchEC2sRequestRaw {

    private final RunInstancesRequest _req;

    public LaunchEC2sRequestImpl() {
        _req = new RunInstancesRequest();
    }

    @Override
    public void setAmi(String amiId) {
        _req.setImageId(amiId);
    }

    @Override
    public void setLaunchTemplateARN(String launchTemplateARN) {
        LaunchTemplateSpecification spec = new LaunchTemplateSpecification();
        spec.setLaunchTemplateId(launchTemplateARN);
        _req.setLaunchTemplate(spec);
    }

    @Override
    public RunInstancesRequest getRaw() {
        return _req;
    }
}
