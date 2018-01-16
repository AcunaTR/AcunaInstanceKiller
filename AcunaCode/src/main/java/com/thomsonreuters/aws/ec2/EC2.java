package com.thomsonreuters.aws.ec2;

import com.thomsonreuters.aws.ec2.impl.EC2Impl;

public class EC2 {
    public static IEC2 create() {
        return new EC2Impl();
    }
}
