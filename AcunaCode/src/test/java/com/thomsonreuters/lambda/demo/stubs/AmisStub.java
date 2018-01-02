package com.thomsonreuters.lambda.demo.stubs;

import java.util.ArrayList;
import java.util.List;

import com.thomsonreuters.aws.ami.IAmi;
import com.thomsonreuters.aws.ami.IAmis;

public class AmisStub implements IAmis {
	
	private List<IAmi> _amis;

	public AmisStub(List<IAmi> amis) {
		_amis = amis;
	}

	public AmisStub(IAmi ami1) {
		_amis = new ArrayList<>();
		_amis.add(ami1);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IAmi get(int idx) {
		return _amis.get(idx);
	}

}
