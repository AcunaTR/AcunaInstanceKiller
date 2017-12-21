package com.thomsonreuters.aws.environment.ec2.request;

import com.thomsonreuters.aws.filter.IFilter;
import com.thomsonreuters.aws.filter.IFilters;

public interface IDescribeEC2sRequest {

	void addFilter(IFilter filter);
	void addFilters(IFilters filters);
	
}
