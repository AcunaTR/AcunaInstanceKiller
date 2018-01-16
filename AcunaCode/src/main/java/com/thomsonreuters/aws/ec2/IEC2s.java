package com.thomsonreuters.aws.ec2;

public interface IEC2s {

    int size();
    
    boolean isEmpty();
    
    IEC2 get(int idx);
    
    void addAll(IEC2s ec2s);
     
    IEC2s clone();
    
    boolean contains(IEC2 ec2);

    void add(IEC2 ec2);

    @Override
    String toString();
    
    IEC2 remove(int idx);
}
