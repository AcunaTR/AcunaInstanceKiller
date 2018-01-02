package com.thomsonreuters.lambda.demo.stubs;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.ec2.model.Instance;
import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.ec2.impl.IEC2sRaw;

public class EC2sStub implements IEC2s, IEC2sRaw{

	private List<IEC2> _ec2s;
	
	public EC2sStub(IEC2 ec2) {
		_ec2s = new ArrayList<>();
		_ec2s.add(ec2);
		
	}
	
	public EC2sStub(List<IEC2> ec2s) {
		_ec2s = ec2s;
	}
	
	//josh woz ere
	public EC2sStub() {
		_ec2s = new ArrayList<>();
	}


//	public Date getLaunchTime(IEC2 ec2) {
//		return ec2.getLaunchTime();
		
//	}

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

	@Override
	public void addAll(IEC2s ec2s) {
		EC2sStub stub = (EC2sStub) ec2s;
		_ec2s.addAll(stub.getEC2s());
		
	}
	
	@Override
	public IEC2s clone() {
		List<IEC2> newList = new ArrayList<>();
		newList.addAll(_ec2s);
		return new EC2sStub(newList);
	}
	
	public IEC2s empty() {
		List<IEC2> newList = new ArrayList<>();
		newList.addAll(_ec2s);
		newList.clear();
		return new EC2sStub(newList);
	}
	

	public List<IEC2> getEC2s(){
		return _ec2s;
	}

	@Override
	public boolean contains(IEC2 ec2) {
		return _ec2s.contains(ec2);
	}

	public void clear() {
		_ec2s.clear();		
	}
	
	public void nullify() {
		_ec2s.set(0, null);
	}

	@Override
	public void add(IEC2 ec2) {
		_ec2s.add(ec2);
		
	}

	@Override
	public List<Instance> getRaw() {
		
		return null;
	}

	
}
