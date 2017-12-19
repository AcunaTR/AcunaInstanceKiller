/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.ami.wrappers.impl;

import com.thomsonreuters.aws.ami.wrappers.impl.TagImpl;
import com.amazonaws.services.ec2.model.Tag;
import java.util.List;
import com.thomsonreuters.aws.ami.wrappers.ITags;
import com.thomsonreuters.aws.ami.wrappers.ITag;

/**
 *
 * @author U6067157
 */
public class TagsImpl implements ITags {
    private final List<Tag> _tags;
    
    public TagsImpl(List<Tag> tags)
    {
        _tags = tags;
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
    public ITag get(int idx)
    {
        return new TagImpl(_tags.get(idx));
    }
}
