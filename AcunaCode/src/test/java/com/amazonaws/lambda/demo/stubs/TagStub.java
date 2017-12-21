package com.amazonaws.lambda.demo.stubs;

import com.thomsonreuters.aws.tag.ITag;

public class TagStub implements ITag {

	private final String _name = "Name";
	private String _value;
	
	public TagStub(String name) {
		_value = name;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getValue() {
		return _value;
	}

}
