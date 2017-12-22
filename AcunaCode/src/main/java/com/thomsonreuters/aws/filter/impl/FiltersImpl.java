/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.filter.impl;

import com.amazonaws.services.ec2.model.Filter;
import java.util.ArrayList;
import java.util.List;

import com.thomsonreuters.aws.filter.IFilter;
import com.thomsonreuters.aws.filter.IFilters;

/**
 *
 * @author U6067157
 */
public class FiltersImpl implements IFilters, IFiltersRaw {

    private final List<Filter> _filters;
    
    public FiltersImpl() {
        _filters = new ArrayList<>();
    }
    
    public FiltersImpl(List<IFilter> filters) {
    	_filters = new ArrayList<>();
    	filters.forEach(filter -> {addFilter(filter);});
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

    @Override
    public IFilter getFilter(int idx) {
        return new FilterImpl(_filters.get(idx));
    }

    @Override
    public void addAllFilters(IFilters filters) {
        IFiltersRaw raw = (IFiltersRaw) filters;
        raw.getRaw().forEach(filter -> {_filters.add(filter);});
    }

    @Override
    public List<IFilter> getFilters() {
        List<IFilter> filters = new ArrayList<>();
        _filters.forEach(filter -> filters.add(new FilterImpl(filter)));
        return filters;
    }
}
