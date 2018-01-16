package com.thomsonreuters.aws.ec2.impl;

import com.amazonaws.services.ec2.model.Instance;

public interface IEC2Raw {
    Instance getRaw();
}
