package com.thomsonreuters.lambda.demo.instancehandler;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CreateRequestTestClass.class,
	RunAPICallTestClass.class,
	RunTestClass.class,
	ParsingResultTestClass.class,
	CheckEC2sTestClass.class
})

public class InstanceHandlerTestSuite {

	

}
