package com.amazonaws.lambda.demo.stubs;

import java.util.ArrayList;
import java.util.List;

import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.ec2.IEC2s;

public class EC2sStub implements IEC2s {

	private List<IEC2> _ec2s;
	
	public EC2sStub(IEC2 ec2) {
		_ec2s = new ArrayList<>();
		_ec2s.add(ec2);
	}
	
	public EC2sStub(List<IEC2> ec2s) {
		_ec2s = ec2s;
	}

	@Override
	public int size() {
		return _ec2s.size();
	}

	@Override
	public boolean isEmpty() {
		return _ec2s.isEmpty();
	}

	@Override
	public IEC2 get(int idx) {
		return _ec2s.get(idx);
	}

}
