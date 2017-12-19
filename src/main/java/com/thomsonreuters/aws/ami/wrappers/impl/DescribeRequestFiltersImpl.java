/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.ami.wrappers.impl;

import com.amazonaws.services.ec2.model.Filter;
import java.util.ArrayList;
import java.util.List;
import com.thomsonreuters.aws.ami.wrappers.IRequestFilter;
import com.thomsonreuters.aws.ami.wrappers.IRequestFilters;

/**
 *
 * @author U6067157
 */
public class DescribeRequestFiltersImpl implements IRequestFilters, IDescribeRequestFiltersRaw {

    private final List<Filter> _filters;
    
    public DescribeRequestFiltersImpl() {
        _filters = new ArrayList<>();
    }
    
    @Override
    public void addFilter(IRequestFilter filter) {
        IDescribeRequestFilterRaw raw = (IDescribeRequestFilterRaw)filter;
        _filters.add(raw.getRaw());
    }

    @Override
    public List<Filter> getRaw() {
        return _filters;
    }
    
}
