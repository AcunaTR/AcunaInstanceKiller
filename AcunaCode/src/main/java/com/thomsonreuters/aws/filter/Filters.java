package com.thomsonreuters.aws.filter;

import java.util.List;

import com.thomsonreuters.aws.filter.impl.FiltersImpl;

public class Filters {
	public static IFilters create(List<IFilter> filters) {
        return new FiltersImpl(filters);
    }
	
	public static IFilters create() {
        return new FiltersImpl();
    }
}
