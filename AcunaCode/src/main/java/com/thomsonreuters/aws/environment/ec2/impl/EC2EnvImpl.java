/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.environment.ec2.impl;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.thomsonreuters.aws.ami.IAmis;
import com.thomsonreuters.aws.ami.impl.AmisImpl;
import com.thomsonreuters.aws.environment.ec2.IEC2Env;
import com.thomsonreuters.aws.environment.ec2.request.ILaunchEC2sRequest;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeAmisRequest;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.aws.environment.ec2.request.ITerminateInstancesRequest;
import com.thomsonreuters.aws.environment.ec2.request.impl.IDescribeAmisRequestRaw;
import com.thomsonreuters.aws.environment.ec2.request.impl.IDescribeEC2sRequestRaw;
import com.thomsonreuters.aws.environment.ec2.request.impl.ILaunchEC2sRequestRaw;
import com.thomsonreuters.aws.environment.ec2.request.impl.ITerminateInstancesRequestRaw;
import com.thomsonreuters.aws.reservation.IReservation;
import com.thomsonreuters.aws.reservation.IReservations;
import com.thomsonreuters.aws.reservation.impl.ReservationImpl;
import com.thomsonreuters.aws.reservation.impl.ReservationsImpl;

/**
 *
 * @author U6067157
 */
public class EC2EnvImpl implements IEC2Env {
	    
    private final AmazonEC2 _env;

    public EC2EnvImpl() {
        _env = AmazonEC2ClientBuilder.defaultClient();
    }

    @Override
    public IAmis describeAmis(IDescribeAmisRequest request) {
        IDescribeAmisRequestRaw raw = (IDescribeAmisRequestRaw)request;
        return new AmisImpl(_env.describeImages(raw.getRaw()).getImages());
    }

    @Override
    public IReservations describeEC2s(IDescribeEC2sRequest request) {
        IDescribeEC2sRequestRaw raw = (IDescribeEC2sRequestRaw)request;
        return new ReservationsImpl(_env.describeInstances(raw.getRaw()).getReservations());
    }

    @Override
    public void terminateInstances(ITerminateInstancesRequest req) {
        ITerminateInstancesRequestRaw raw = (ITerminateInstancesRequestRaw) req;
        _env.terminateInstances(raw.getRaw());
    }

    @Override
    public String toString() {
        return _env.toString();
    }

    @Override
    public IReservation launchEC2s(ILaunchEC2sRequest req) {
        ILaunchEC2sRequestRaw raw = (ILaunchEC2sRequestRaw) req;
        RunInstancesResult res = _env.runInstances(raw.getRaw());
        return new ReservationImpl(res.getReservation());
    }
}
