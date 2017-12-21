package com.amazonaws.lambda.demo.stubs;

import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.aws.filter.IFilter;
import com.thomsonreuters.aws.filter.IFilters;

public class DescribeEC2sRequestStub implements IDescribeEC2sRequest {
	
	public IFilters getFilters() {
		return null;
	}

	@Override
	public void addFilter(IFilter filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFilters(IFilters filters) {
		// TODO Auto-generated method stub
		
	}

}
