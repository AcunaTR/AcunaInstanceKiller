/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.ami.wrappers;

import com.thomsonreuters.aws.ami.wrappers.impl.RequestFilterImpl;
import com.thomsonreuters.aws.ami.wrappers.impl.DescribeRequestFiltersImpl;

/**
 *
 * @author U6067157
 */
public class RequestFilters {
    public static IRequestFilter createFilter(String name) {
        return new RequestFilterImpl(name);
    }
    
    public static IRequestFilters createFilterCollection() {
        return new DescribeRequestFiltersImpl();
    }
}
