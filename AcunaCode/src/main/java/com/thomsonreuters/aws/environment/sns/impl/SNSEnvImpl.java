package com.thomsonreuters.aws.environment.sns.impl;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

import com.thomsonreuters.aws.environment.sns.ISNSEnv;

public class SNSEnvImpl implements ISNSEnv {

    private final AmazonSNS _env;

    public SNSEnvImpl(){
        _env = AmazonSNSClientBuilder.defaultClient();
    }

    @Override
    public void publish(String topicArn, String message) {
        _env.publish(topicArn, message);		
    }
}
