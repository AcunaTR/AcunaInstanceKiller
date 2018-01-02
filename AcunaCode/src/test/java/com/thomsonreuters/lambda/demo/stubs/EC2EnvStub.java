package com.thomsonreuters.lambda.demo.stubs;

import com.thomsonreuters.aws.ami.IAmis;
import com.thomsonreuters.aws.environment.ec2.IEC2Env;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeAmisRequest;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.aws.environment.ec2.request.ITerminateInstancesRequest;
import com.thomsonreuters.aws.reservation.IReservations;

public class EC2EnvStub implements IEC2Env {

	private IAmis _amis;
	private IReservations _reservations;
	private int _describeEC2sCounter;
	private IDescribeEC2sRequest _request;
	
	
	public EC2EnvStub(IReservations reservations) {
		_describeEC2sCounter = 0;
		_reservations = reservations;
	}

	@Override
	public IAmis describeAmis(IDescribeAmisRequest request) {
		return _amis;
	}

	@Override
	public IReservations describeEC2s(IDescribeEC2sRequest request) {
		_request = request;
		_describeEC2sCounter++;
		return _reservations;
	}

	public int getDescribeEC2sCounter() {
		return _describeEC2sCounter;
	}
	
	public IDescribeEC2sRequest getRequest() {
		return _request;
	}
	
	public IReservations getReservations() {
		return _reservations;
	}

	@Override
	public void terminateInstances(ITerminateInstancesRequest req) {
		// TODO Auto-generated method stub
		
	}
}
