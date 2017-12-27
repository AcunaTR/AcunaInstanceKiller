package com.amazonaws.lambda.demo.stubs;

import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.reservation.IReservation;

public class ReservationStub implements IReservation {

	private IEC2s _ec2s;
	private int _getInstancesCounter;
	

	public ReservationStub(IEC2s ec2s) {
		_ec2s = ec2s;
		_getInstancesCounter = 0;
	}

	@Override
	public IEC2s getInstances() {
		_getInstancesCounter++;
		return _ec2s;
	}
	
	public int get_getInstancesCounter() {
		return _getInstancesCounter;
	}

	public int removeOneInstance() {
		_getInstancesCounter = _getInstancesCounter - 1;
		return _getInstancesCounter;
		
	}

}
