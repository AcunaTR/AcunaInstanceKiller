/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thomsonreuters.aws.ami;

/**
 *
 * @author U6067157
 */
public interface IAmis {
    int size();
    
    boolean isEmpty();
    
    IAmi get(int idx);
}
