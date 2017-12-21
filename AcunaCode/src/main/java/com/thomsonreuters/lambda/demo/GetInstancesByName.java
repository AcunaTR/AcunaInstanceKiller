package com.thomsonreuters.lambda.demo;

import java.util.List;

import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.environment.ec2.IEC2Env;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.aws.filter.Filter;
import com.thomsonreuters.aws.filter.IFilter;
import com.thomsonreuters.aws.reservation.IReservations;
import com.thomsonreuters.lambda.demo.exceptions.InvalidInstancesException;
import com.thomsonreuters.lambda.demo.factories.IDescribeEC2sRequestFactory;

public class GetInstancesByName {

	public static IEC2s run(IEC2Env env, String serverName, IDescribeEC2sRequestFactory reqFactory) throws InvalidInstancesException {
		IDescribeEC2sRequest req =  createRequest(reqFactory, serverName);
		/*IReservations res = runApiCall(req, env);
		IEC2s ec2s = parsingEC2s(res);
		if (checkEc2s(ec2s)) {
			return ec2s;
		}
		throw new InvalidInstancesException("EC2s returned did not match - " + serverName + "/n Actual result - " + ec2s.toString());
		*/
		return null;
	}

	private static boolean checkEc2s(IEC2s ec2s) {
		// TODO Auto-generated method stub
		return false;
	}

	private static IEC2s parsingEC2s(IReservations res) {
		// TODO Auto-generated method stub
		return null;
	}

	private static IReservations runApiCall(IDescribeEC2sRequest req, IEC2Env env) {
		// TODO Auto-generated method stub
		return null;
	}

	private static IDescribeEC2sRequest createRequest(IDescribeEC2sRequestFactory reqFactory, String serverName) {
		IFilter filter = Filter.create("tag:Name", serverName);
		reqFactory.addFilter(filter);
		return reqFactory.createRequest();
	}
}
