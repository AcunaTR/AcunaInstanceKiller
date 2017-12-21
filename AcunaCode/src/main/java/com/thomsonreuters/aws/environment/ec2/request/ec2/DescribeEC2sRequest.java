package com.thomsonreuters.aws.environment.ec2.request.ec2;

import com.thomsonreuters.aws.environment.ec2.request.ec2.impl.DescribeEC2sRequestImpl;

public class DescribeEC2sRequest {
	public static IDescribeEC2sRequest create() {
        return new DescribeEC2sRequestImpl();
    }
}
