package com.amazonaws.lambda.demo.stubs;


import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.aws.filter.IFilter;
import com.thomsonreuters.aws.filter.IFilters;
import com.thomsonreuters.lambda.demo.factories.IDescribeEC2sRequestFactory;

public class DescribeEC2sRequestFactoryStub implements IDescribeEC2sRequestFactory {

	private IDescribeEC2sRequest _req;
	private int createRequestCallCount;
	private int setFilterCount;
	
	public DescribeEC2sRequestFactoryStub(IDescribeEC2sRequest req) {
		_req = req;
		createRequestCallCount = 0;
		setFilterCount = 0;
	}
	
	@Override
	public void addFilter(IFilter filter) {
		setFilterCount++;
		_req.addFilter(filter);
	}

	@Override
	public void addFilters(IFilters filters) {
		
	}

	@Override
	public IDescribeEC2sRequest createRequest() {
		createRequestCallCount++;
		return _req;
	}

	public int getCreateRequestCallCount() {
		return createRequestCallCount;
	}
	
	public int getSetFilterorFiltersCount() {
		return setFilterCount;
	}
}
