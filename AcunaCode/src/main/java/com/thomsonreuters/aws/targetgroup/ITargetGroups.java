package com.thomsonreuters.aws.targetgroup;

public interface ITargetGroups {

    int size();
    
    boolean isEmpty();
    
    ITargetGroup get(int idx);	
}
