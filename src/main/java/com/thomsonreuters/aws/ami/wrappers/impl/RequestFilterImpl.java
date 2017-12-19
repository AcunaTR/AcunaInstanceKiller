/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.ami.wrappers.impl;

import com.amazonaws.services.ec2.model.Filter;
import java.util.ArrayList;
import java.util.List;
import com.thomsonreuters.aws.ami.wrappers.IRequestFilter;

/**
 *
 * @author U6067157
 */
public class RequestFilterImpl implements IRequestFilter, IDescribeRequestFilterRaw {

    private final String _name;
    private final List<String> _values;
    
    public RequestFilterImpl(String name)
    {
        _name = name;
        _values = new ArrayList<>();
    }
    
    @Override
    public String getName() {
        return _name;
    }
    
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
