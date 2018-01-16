package com.thomsonreuters.aws.environment.elb.request.impl;

import java.util.Arrays;

import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetGroupsRequest;

import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetGroupsRequest;

public class DescribeTargetGroupsRequestImpl implements IDescribeTargetGroupsRequest, IDescribeTargetGroupsRequestRaw {

    private final DescribeTargetGroupsRequest _req;

    public DescribeTargetGroupsRequestImpl() {
        _req = new DescribeTargetGroupsRequest();
    }

    @Override
    public DescribeTargetGroupsRequest getRaw() {
        return _req;
    }

    @Override
    public void setName(String name) {
        _req.setNames(Arrays.asList(name));
    }

    @Override
    public String toString() {
        return _req.toString();
    }
}
