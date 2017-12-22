package com.amazonaws.lambda.demo.stubs;

import com.thomsonreuters.aws.tag.ITag;
import com.thomsonreuters.aws.tag.ITags;

public class TagsStub implements ITags {

	private ITag _tag;
	
	public TagsStub(String name) {
		_tag = new TagStub(name);
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public ITag getTag(String key) {
		return _tag;
	}

	@Override
	public String getValue(String key) {
		return _tag.getValue();
	}

}
