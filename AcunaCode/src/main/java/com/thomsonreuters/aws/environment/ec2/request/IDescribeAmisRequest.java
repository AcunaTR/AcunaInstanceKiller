/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.environment.ec2.request;

import com.thomsonreuters.aws.filter.IFilters;

/**
 *
 * @author U6067157
 */
public interface IDescribeAmisRequest {
    void setFilters(IFilters filters);
}
