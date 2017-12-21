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

/**
 *
 * @author U6067157
 */
public class FilterImpl implements IFilter, IFilterRaw {

    private final String _name;
    private final List<String> _values;
    
    public FilterImpl(String name, String value)
    {
        _name = name;
        _values = new ArrayList<>();
        _values.add(value);
    }
    
    public FilterImpl(String name, List<String> values)
    {
        _name = name;
        _values = values;
    }

	public FilterImpl(Filter filter) {
		_name = filter.getName();
		_values = filter.getValues();
	}

	@Override
    public String getName() {
        return _name;
    }
    
    @Override
    public void addValue(String value) {
        _values.add(value);
    }

    @Override
    public List<String> getValues() {
        return _values;
    }

    @Override
    public Filter getRaw() {
        return new Filter(_name, _values);
    }
    
}
