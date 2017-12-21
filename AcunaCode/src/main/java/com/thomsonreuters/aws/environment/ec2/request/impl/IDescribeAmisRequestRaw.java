/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.environment.ec2.request.impl;

import com.amazonaws.services.ec2.model.DescribeImagesRequest;

/**
 *
 * @author U6067157
 */
public interface IDescribeAmisRequestRaw {
    DescribeImagesRequest getRaw();
}
