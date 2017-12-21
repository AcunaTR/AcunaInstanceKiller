package com.thomsonreuters.aws.ec2;


public interface IEC2s {

    int size();
    
    boolean isEmpty();
    
    IEC2 get(int idx);
}
