package com.thomsonreuters.aws.environment.ec2.request.impl;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.Filter;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.aws.filter.IFilter;
import com.thomsonreuters.aws.filter.IFilters;
import com.thomsonreuters.aws.filter.impl.IFilterRaw;
import com.thomsonreuters.aws.filter.impl.IFiltersRaw;

public class DescribeEC2sRequestImpl implements IDescribeEC2sRequest, IDescribeEC2sRequestRaw{

    private final DescribeInstancesRequest _req;
    private List<Filter> _filters;
	
    public DescribeEC2sRequestImpl() {
        _req = new DescribeInstancesRequest();
        _filters = new ArrayList<>();
    }

    @Override
    public DescribeInstancesRequest getRaw() {
        _req.setFilters(_filters);
        return _req;
    }

    @Override
    public void addFilter(IFilter filter) {
        IFilterRaw raw = (IFilterRaw) filter;
        _filters.add(raw.getRaw());
    }

    @Override
    public void addFilters(IFilters filters) {
        IFiltersRaw raw = (IFiltersRaw) filters;
        _filters.addAll(raw.getRaw());
    }
    
    @Override
	public String toString() {
		return _req.toString() + _filters.toString();
	}
}
