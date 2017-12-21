package com.amazonaws.lambda.demo.stubs;

import com.thomsonreuters.aws.ami.IAmis;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.environment.ec2.IEC2Env;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeAmisRequest;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.aws.reservation.IReservation;
import com.thomsonreuters.aws.reservation.IReservations;

public class EC2EnvStub implements IEC2Env {

	private IAmis _amis;
	private IReservations _reservations;
	
	public EC2EnvStub(IReservations reservations) {
		_reservations = reservations;
	}

	@Override
	public IAmis describeAmis(IDescribeAmisRequest request) {
		return _amis;
	}

	@Override
	public IReservations describeEC2s(IDescribeEC2sRequest request) {
		return _reservations;
	}

}
