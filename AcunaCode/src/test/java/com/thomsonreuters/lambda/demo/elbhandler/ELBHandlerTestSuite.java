package com.thomsonreuters.lambda.demo.elbhandler;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	DescribeTargetGroupRequestTestClass.class,
	RunTargetGroupAPICallTestClass.class,
	CheckTargetGroupsTestClass.class
})

public class ELBHandlerTestSuite {

}






