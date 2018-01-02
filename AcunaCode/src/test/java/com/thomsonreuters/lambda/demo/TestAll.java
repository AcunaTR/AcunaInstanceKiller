package com.thomsonreuters.lambda.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.thomsonreuters.lambda.demo.instancehandler.InstanceHandlerTestSuite;
import com.thomsonreuters.lambda.demo.oldserver.OldServerTestSuite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	InstanceHandlerTestSuite.class,
	OldServerTestSuite.class
	
	

})

public class TestAll {


}
