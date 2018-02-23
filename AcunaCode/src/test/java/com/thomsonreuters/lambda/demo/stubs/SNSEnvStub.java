package com.thomsonreuters.lambda.demo.stubs;

import com.thomsonreuters.aws.environment.sns.ISNSEnv;

public class SNSEnvStub implements ISNSEnv {

	private String _topicArn;
	private String _message;
	
	@Override
	public void publish(String topicArn, String message) {
		_topicArn = topicArn;
		_message = message;
	}

	public String getMessage() {
		return _message;
	}
	
	public String getTopicArn() {
		return _topicArn;
	}
	
}
