package com.amazonaws.lambda.demo.stubs;

import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.reservation.IReservation;

public class ReservationStub implements IReservation {

	private IEC2s _ec2s;
	
	public ReservationStub(IEC2s ec2s) {
		_ec2s = ec2s;
	}

	@Override
	public IEC2s getInstances() {
		return _ec2s;
	}

}
