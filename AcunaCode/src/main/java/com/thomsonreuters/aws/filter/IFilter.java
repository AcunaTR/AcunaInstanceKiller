/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.filter;

import java.util.List;

/**
 *
 * @author U6067157
 */
public interface IFilter {
    
    String getName();
    
    void addValue(String value);
    
    List<String> getValues();
}
