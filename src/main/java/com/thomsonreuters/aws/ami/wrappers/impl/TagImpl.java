/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.ami.wrappers.impl;

import com.amazonaws.services.ec2.model.Tag;
import com.thomsonreuters.aws.ami.wrappers.ITag;

/**
 *
 * @author U6067157
 */
public class TagImpl implements ITag {
    private final Tag _tag;
    
    public TagImpl(Tag tag)
    {
        _tag = tag;
    }
    
    public TagImpl(String name, String val) {
        _tag = new Tag(name, val);
    }
	
    @Override
    public String getName() {
            return _tag.getKey();
    }

    @Override
    public String getValue() {
            return _tag.getValue();
    }
}
