/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.ami.wrappers.impl;

import com.amazonaws.services.ec2.model.Image;
import com.thomsonreuters.aws.ami.wrappers.IAmi;
import java.util.List;

/**
 *
 * @author U6067157
 */
public class AmisImpl {
    
    private final List<Image> _amis;
    
    public AmisImpl(List<Image> amis) {
        _amis = amis;
    }
    
    public int size() {
        return _amis.size();
    }
    
    public boolean isEmpty() {
        return _amis.isEmpty();
    }
    
    public IAmi get(int idx) {
        return new AmiImpl(_amis.get(idx));
    }
}
