package com.thomsonreuters.lambda.demo;

import com.thomsonreuters.aws.environment.elb.IELBEnv;
import com.thomsonreuters.aws.environment.elb.request.IDescribeTargetGroupsRequest;
import com.thomsonreuters.aws.targetgroup.ITargetGroup;
import com.thomsonreuters.aws.targetgroup.ITargetGroups;
import com.thomsonreuters.lambda.demo.exceptions.InvalidTargetGroupsException;
import com.thomsonreuters.lambda.demo.exceptions.NoTargetGroupException;
import com.thomsonreuters.lambda.demo.factories.IDescribeTargetGroupsRequestFactory;

public class ELBHandler {

	public static ITargetGroup getTargetGroup(IELBEnv elbEnv, IDescribeTargetGroupsRequestFactory reqFactory) throws NoTargetGroupException, InvalidTargetGroupsException {
		IDescribeTargetGroupsRequest req =  createRequest(reqFactory, "acuna-jenkins-target-group");
		ITargetGroups res = runApiCall(req, elbEnv);
		if(checkTargetGroups(res)) {
			return res.get(0);
		}

		throw new InvalidTargetGroupsException("InvalidTargetGroupsException - Target group named - acuna-jenkins-target-group not found");

	}

	public static boolean checkTargetGroups(ITargetGroups res) throws NoTargetGroupException {
		if (res.size() == 0) {

			throw new NoTargetGroupException("NoTargetGroupException - No target group found with name - acuna-jenkins-target-group");

		}
	return true;
	}

	private static ITargetGroups runApiCall(IDescribeTargetGroupsRequest req, IELBEnv elbEnv) {
		return elbEnv.describeTargetGroups(req);
	}

	private static IDescribeTargetGroupsRequest createRequest(IDescribeTargetGroupsRequestFactory reqFactory,
			String name) {
		reqFactory.setName(name);
		return reqFactory.createRequest();
	}

}
