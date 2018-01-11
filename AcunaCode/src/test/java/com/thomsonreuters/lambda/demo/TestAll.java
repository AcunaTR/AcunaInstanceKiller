package com.thomsonreuters.lambda.demo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.thomsonreuters.lambda.demo.elbhandler.ELBHandlerTestSuite;
import com.thomsonreuters.lambda.demo.instancehandler.InstanceHandlerTestSuite;
import com.thomsonreuters.lambda.demo.oldserver.OldServerTestSuite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	InstanceHandlerTestSuite.class,
	OldServerTestSuite.class,
	ELBHandlerTestSuite.class
	
	

})

public class TestAll {


}
