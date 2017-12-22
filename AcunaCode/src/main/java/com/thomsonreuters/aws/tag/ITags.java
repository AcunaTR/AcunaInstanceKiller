/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.tag;

/**
 *
 * @author U6067157
 */
public interface ITags {
    int size();
    
    boolean isEmpty();
    
    ITag getTag(String key);
    
    String getValue(String key);
    
}
