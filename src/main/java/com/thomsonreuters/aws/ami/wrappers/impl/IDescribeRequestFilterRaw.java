/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.ami.wrappers.impl;

import com.amazonaws.services.ec2.model.Filter;

/**
 *
 * @author U6067157
 */
public interface IDescribeRequestFilterRaw {
    Filter getRaw();
}
