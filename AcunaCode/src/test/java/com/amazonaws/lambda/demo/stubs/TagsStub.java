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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ITag get(int idx) {
		return _tag;
	}

}
