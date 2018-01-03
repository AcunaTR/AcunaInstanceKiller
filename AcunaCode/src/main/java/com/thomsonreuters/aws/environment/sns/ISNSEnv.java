package com.thomsonreuters.aws.environment.sns;

public interface ISNSEnv {
	
	void publish(String topicArn, String message);
}
