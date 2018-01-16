/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.tag.impl;

import com.amazonaws.services.ec2.model.Tag;

import com.thomsonreuters.aws.tag.ITag;
import com.thomsonreuters.aws.tag.ITags;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author U6067157
 */
public class TagsImpl implements ITags {
    private final Map<String, Tag> _tags;
    
    public TagsImpl(List<Tag> tags)
    {
    	_tags = new HashMap<>();
        tags.forEach((t) -> {_tags.put(t.getKey(), t);});
    }
    
    @Override
    public int size()
    {
        return _tags.size();
    }
    
    @Override
    public boolean isEmpty()
    {
        return _tags.isEmpty();
    }
    
    @Override
    public ITag getTag(String key)
    {
        return new TagImpl(_tags.get(key));
    }

    @Override
    public String getValue(String key) {
        return _tags.get(key).getValue();
    }

    @Override
    public String toString() {
        return _tags.toString();
    }
}
