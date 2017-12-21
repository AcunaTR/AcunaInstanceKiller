package com.thomsonreuters.lambda.demo.factories.impl;

import com.thomsonreuters.aws.environment.ec2.request.DescribeEC2sRequest;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.aws.filter.Filters;
import com.thomsonreuters.aws.filter.IFilter;
import com.thomsonreuters.aws.filter.IFilters;
import com.thomsonreuters.lambda.demo.factories.IDescribeEC2sRequestFactory;

public class DescribeEC2sRequestFactory implements IDescribeEC2sRequestFactory {

	private IFilters _filters;
	
	public DescribeEC2sRequestFactory() {
		_filters = Filters.create();
	}
	
	@Override
	public void addFilter(IFilter filter) {
		_filters.addFilter(filter);

	}

	@Override
	public void addFilters(IFilters filters) {
		_filters.addAllFilters(filters);

	}

	@Override
	public IDescribeEC2sRequest createRequest() {
		IDescribeEC2sRequest req = DescribeEC2sRequest.create();
		req.addFilters(_filters);
		return req;
	}

}
