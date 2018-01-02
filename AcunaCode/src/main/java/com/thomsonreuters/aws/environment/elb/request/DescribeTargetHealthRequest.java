package com.thomsonreuters.aws.environment.elb.request;

import com.thomsonreuters.aws.environment.elb.request.impl.DescribeTargetHealthRequestImpl;

public class DescribeTargetHealthRequest {

	public static IDescribeTargetHealthRequest create() {
        return new DescribeTargetHealthRequestImpl();
    }
}
