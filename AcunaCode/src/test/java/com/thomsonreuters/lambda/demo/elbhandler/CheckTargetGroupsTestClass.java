package com.thomsonreuters.lambda.demo.elbhandler;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetGroupsRequest;
import com.thomsonreuters.aws.targetgroup.ITargetGroup;
import com.thomsonreuters.aws.targetgroup.ITargetGroups;
import com.thomsonreuters.lambda.demo.ELBHandler;
import com.thomsonreuters.lambda.demo.elbhandler.CheckTargetGroupsStubs.ELBEnvStub;
import com.thomsonreuters.lambda.demo.exceptions.InvalidTargetGroupsException;
import com.thomsonreuters.lambda.demo.exceptions.NoTargetGroupException;
import com.thomsonreuters.lambda.demo.stubs.DescribeTargetGroupsRequestFactoryStub;
import com.thomsonreuters.lambda.demo.stubs.DescribeTargetGroupsRequestStub;
import com.thomsonreuters.lambda.demo.stubs.TargetGroupStub;
import com.thomsonreuters.lambda.demo.stubs.TargetGroupsStub;

public class CheckTargetGroupsTestClass {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTargetGroupNull() {
	ITargetGroups targetGroups = null;
	//List<ITargetGroup> exp = new ArrayList<>();
	//exp.add(TargetGroup tag);
	boolean checkPassed = false;
			try {
				checkPassed = ELBHandler.checkTargetGroups(targetGroups);
				Assert.assertTrue(checkPassed);
			} catch (NullPointerException e) {
					
			
			} catch (NoTargetGroupException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	} 
	
	@Test
	public void testTargetGroup() {
	ITargetGroups targetGroups = new TargetGroupsStub(null);
	//List<ITargetGroup> exp = new ArrayList<>();
	//exp.add(TargetGroup tag);
	boolean checkPassed = false;
			try {
				checkPassed = ELBHandler.checkTargetGroups(targetGroups);
				Assert.assertTrue(checkPassed);
			} catch (NullPointerException e) {
					
			
			} catch (NoTargetGroupException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	} 
	
/*	@Test
	public void testValidTargetGroup() {
		
		IDescribeTargetGroupsRequest reqStub = new DescribeTargetGroupsRequestStub();
		DescribeTargetGroupsRequestFactoryStub reqFactory = new DescribeTargetGroupsRequestFactoryStub(reqStub);
		ELBEnvStub elbEnv = new ELBEnvStub();
	//	ITargetGroup targetGroup = null;
	//	List<ITargetGroup> targetGroupList = new ArrayList<>();
	//	targetGroupList.add(targetGroup);		
	//	ITargetGroups targetGroups = new ITargetGroups();targetGroup);
	//	(ITargetGroups) targetGroupList;
		
		boolean checkPassed = false;
		

		try {			
			ELBHandler.getTargetGroup(elbEnv, reqFactory);
		} catch (NullPointerException e) {
			
			//try {
				//checkPassed = ELBHandler.checkTargetGroups(targetGroups);
			//} catch (NoTargetGroupException e1) {	
			//	fail("Unexpected NoTargetGroupException - " + e.getMessage());
			//}
			
			Assert.assertTrue(checkPassed);
			Assert.assertEqual(elbEnv.describeTargetGroups(req));
		} catch (NoTargetGroupException e) {	
			fail("Unexpected NoTargetGroupException - " + e.getMessage());
		} catch (InvalidTargetGroupsException e) {
			fail("Unexpected InvalidTargetGroupsException - " + e.getMessage());
		}
		
	}
	
	@Test
	public void testSeeIfthisWorks() {
		IDescribeTargetGroupsRequest reqStub = new DescribeTargetGroupsRequestStub();
		DescribeTargetGroupsRequestFactoryStub reqFactory = new DescribeTargetGroupsRequestFactoryStub(reqStub);
		ELBEnvStub elbEnv = new ELBEnvStub();
		
		
	} */



}
