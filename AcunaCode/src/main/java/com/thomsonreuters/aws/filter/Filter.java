package com.thomsonreuters.aws.filter;

import java.util.List;

import com.thomsonreuters.aws.filter.impl.FilterImpl;

public class Filter {
	public static IFilter create(String name, String value) {
        return new FilterImpl(name, value);
    }
	
	public static IFilter create(String name, List<String> values) {
        return new FilterImpl(name, values);
    }
}
