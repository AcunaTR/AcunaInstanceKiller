package com.thomsonreuters.aws.environment.elb.request;

import com.thomsonreuters.aws.environment.elb.request.impl.DescribeTargetGroupsRequestImpl;

public class DescribeTargetGroupsRequest {

    public static IDescribeTargetGroupsRequest create() {
        return new DescribeTargetGroupsRequestImpl();
    }
}
