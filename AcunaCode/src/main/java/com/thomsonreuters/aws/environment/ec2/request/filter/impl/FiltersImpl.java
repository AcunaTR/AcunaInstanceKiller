/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.environment.ec2.request.filter.impl;

import com.amazonaws.services.ec2.model.Filter;
import java.util.ArrayList;
import java.util.List;

import com.thomsonreuters.aws.environment.ec2.request.filter.IFilter;
import com.thomsonreuters.aws.environment.ec2.request.filter.IFilters;
import com.thomsonreuters.aws.environment.ec2.request.filter.raw.IFilterRaw;
import com.thomsonreuters.aws.environment.ec2.request.filter.raw.IFiltersRaw;

/**
 *
 * @author U6067157
 */
public class FiltersImpl implements IFilters, IFiltersRaw {

    private final List<Filter> _filters;
    
    public FiltersImpl() {
        _filters = new ArrayList<>();
    }
    
    @Override
    public void addFilter(IFilter filter) {
        IFilterRaw raw = (IFilterRaw)filter;
        _filters.add(raw.getRaw());
    }

    @Override
    public List<Filter> getRaw() {
        return _filters;
    }
    
}
