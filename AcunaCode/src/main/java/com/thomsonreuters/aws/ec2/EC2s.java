package com.thomsonreuters.aws.ec2;

import com.thomsonreuters.aws.ec2.impl.EC2sImpl;

public class EC2s {
	
    public static IEC2s create() {
        return new EC2sImpl();
    }
}
