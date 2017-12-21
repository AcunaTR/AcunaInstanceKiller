package com.amazonaws.lambda.demo.stubs;

import com.thomsonreuters.aws.ami.IAmi;
import com.thomsonreuters.aws.tag.ITags;

public class AmiStub implements IAmi {

	private String _name;
	
	public AmiStub(String name) {
		_name = name;
	}

	@Override
	public String getImageId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITags getTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getImageName() {
		return _name;
	}

}
