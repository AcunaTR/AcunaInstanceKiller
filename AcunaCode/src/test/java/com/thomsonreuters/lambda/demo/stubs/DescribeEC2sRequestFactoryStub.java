package com.thomsonreuters.lambda.demo.stubs;


import java.util.ArrayList;
import java.util.List;

import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.aws.filter.IFilter;
import com.thomsonreuters.aws.filter.IFilters;
import com.thomsonreuters.lambda.demo.factories.IDescribeEC2sRequestFactory;

public class DescribeEC2sRequestFactoryStub implements IDescribeEC2sRequestFactory {

	private IDescribeEC2sRequest _req;
	private int _createRequestCallCount;
	private int _setFilterCount;
	private List<IFilter> _filters;
	
	public DescribeEC2sRequestFactoryStub(IDescribeEC2sRequest req) {
		_req = req;
		_createRequestCallCount = 0;
		_setFilterCount = 0;
		_filters = new ArrayList<>();
	}
	
	@Override
	public void addFilter(IFilter filter) {
		_setFilterCount++;
		_filters.add(filter);
		_req.addFilter(filter);
	}

	@Override
	public void addFilters(IFilters filters) {
		
	}

	@Override
	public IDescribeEC2sRequest createRequest() {
		_createRequestCallCount++;
		return _req;
	}

	public int getCreateRequestCallCount() {
		return _createRequestCallCount;
	}
	
	public int getSetFilterorFiltersCount() {
		return _setFilterCount;
	}
	
	public List<IFilter> getFilters(){
		return _filters;
	}
}
