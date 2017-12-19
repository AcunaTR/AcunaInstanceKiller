/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.ami.wrappers.impl;

import com.amazonaws.services.ec2.model.Image;
import com.thomsonreuters.aws.ami.wrappers.IAmi;
import com.thomsonreuters.aws.ami.wrappers.ITags;

/**
 *
 * @author U6067157
 */
public class AmiImpl implements IAmi {
    private final Image _ami;
	
	public AmiImpl(Image ami) {
            _ami = ami;
	}
	
    @Override
	public String getImageId() {
		
            return _ami.getImageId();
	}

    @Override
	public ITags getTags() {
            return new TagsImpl(_ami.getTags());
	}
	
    @Override
	public String getImageName() {
            return _ami.getName();
	}
}
