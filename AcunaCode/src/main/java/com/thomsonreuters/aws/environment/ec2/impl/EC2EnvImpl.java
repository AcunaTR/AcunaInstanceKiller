/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.environment.ec2.impl;

import com.thomsonreuters.aws.environment.ec2.request.impl.IDescribeEC2sRequestRaw;
import com.thomsonreuters.aws.environment.ec2.request.impl.ITerminateInstancesRequestRaw;
import com.thomsonreuters.aws.environment.ec2.request.impl.IDescribeAmisRequestRaw;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.thomsonreuters.aws.ami.IAmis;
import com.thomsonreuters.aws.ami.impl.AmisImpl;
import com.thomsonreuters.aws.environment.ec2.IEC2Env;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeAmisRequest;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.aws.environment.ec2.request.ITerminateInstancesRequest;
import com.thomsonreuters.aws.reservation.IReservations;
import com.thomsonreuters.aws.reservation.impl.ReservationsImpl;

/**
 *
 * @author U6067157
 */
public class EC2EnvImpl implements IEC2Env {
	    
    private final AmazonEC2 _ec2;

    public EC2EnvImpl() {
        _ec2 = AmazonEC2ClientBuilder.defaultClient();
    }

    @Override
    public IAmis describeAmis(IDescribeAmisRequest request) {
        IDescribeAmisRequestRaw raw = (IDescribeAmisRequestRaw)request;
        return new AmisImpl(_ec2.describeImages(raw.getRaw()).getImages());
    }

    @Override
    public IReservations describeEC2s(IDescribeEC2sRequest request) {
        IDescribeEC2sRequestRaw raw = (IDescribeEC2sRequestRaw)request;
        return new ReservationsImpl(_ec2.describeInstances(raw.getRaw()).getReservations());
    }

	@Override
	public void terminateInstances(ITerminateInstancesRequest req) {
		ITerminateInstancesRequestRaw raw = (ITerminateInstancesRequestRaw) req;
		_ec2.terminateInstances(raw.getRaw());
		
	}
}
