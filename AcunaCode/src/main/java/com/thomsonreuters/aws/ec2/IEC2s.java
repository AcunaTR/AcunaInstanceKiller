package com.thomsonreuters.aws.ec2;

import java.util.List;

public interface IEC2s {

    int size();
    
    boolean isEmpty();
    
    IEC2 get(int idx);
    
    void addAll(IEC2s ec2s);
     
    IEC2s clone();
    
    boolean contains(IEC2 ec2);

	void add(IEC2 ec2);
}
