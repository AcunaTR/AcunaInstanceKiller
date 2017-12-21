package com.thomsonreuters.lambda.demo.factories;


import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.aws.filter.IFilter;
import com.thomsonreuters.aws.filter.IFilters;

public interface IDescribeEC2sRequestFactory{

	void addFilter(IFilter filter);
	void addFilters(IFilters filters);
	IDescribeEC2sRequest createRequest();
	
}
