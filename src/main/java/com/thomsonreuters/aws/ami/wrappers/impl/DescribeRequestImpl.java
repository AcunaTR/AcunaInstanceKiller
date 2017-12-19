/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.ami.wrappers.impl;

import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.thomsonreuters.aws.ami.wrappers.IDescribeRequest;
import com.thomsonreuters.aws.ami.wrappers.IRequestFilters;

/**
 *
 * @author U6067157
 */
public class DescribeRequestImpl implements IDescribeRequest, IDescribeRequestRaw {
    private final DescribeImagesRequest _req;
    
    public DescribeRequestImpl() {
        _req = new DescribeImagesRequest();
    }
    
    @Override
    public void setFilters(IRequestFilters filters) {
        IDescribeRequestFiltersRaw raw = (IDescribeRequestFiltersRaw) filters;
        
        _req.setFilters(raw.getRaw());
    }

    @Override
    public DescribeImagesRequest getRaw() {
        return _req;
    }
}
