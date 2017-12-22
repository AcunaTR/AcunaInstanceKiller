package com.thomsonreuters.lambda.demo;

import com.thomsonreuters.aws.ec2.EC2s;
import com.thomsonreuters.aws.ec2.IEC2;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.environment.ec2.IEC2Env;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.aws.filter.Filter;
import com.thomsonreuters.aws.filter.IFilter;
import com.thomsonreuters.aws.reservation.IReservations;
import com.thomsonreuters.lambda.demo.exceptions.EmptyReservationException;
import com.thomsonreuters.lambda.demo.exceptions.InvalidInstancesException;
import com.thomsonreuters.lambda.demo.exceptions.NoInstancesException;
import com.thomsonreuters.lambda.demo.exceptions.NoReservationException;
import com.thomsonreuters.lambda.demo.factories.IDescribeEC2sRequestFactory;

public class GetInstancesByName {

	public static IEC2s run(IEC2Env env,
							String serverName,
							IDescribeEC2sRequestFactory reqFactory) 
						throws
							InvalidInstancesException,
							NoInstancesException,
							EmptyReservationException,
							NoReservationException 
	{
		IDescribeEC2sRequest req =  createRequest(reqFactory, serverName);
		IReservations res = runApiCall(req, env);
		IEC2s ec2s = parsingEC2s(res);
		if (checkEc2s(ec2s, serverName)) {
			return ec2s;
		}
		throw new InvalidInstancesException("EC2s returned did not match - " + serverName + "/n Actual result - " + ec2s.toString());
		
	}

	private static boolean checkEc2s(IEC2s ec2s, String serverName) throws EmptyReservationException {
		if ((ec2s == null) || (ec2s.isEmpty())) {
			throw new EmptyReservationException("Reservation contains no instances");
		}
		
		for (int i = 0; i < ec2s.size(); i++) {
			IEC2 ec2 = ec2s.get(i);
			if(ec2.getTags().getValue("tag:Name") != serverName){
				return false;
			}
		}
		return true;
	}

	private static IEC2s parsingEC2s(IReservations res) throws EmptyReservationException, NoReservationException {
		if ((res == null) || (res.isEmpty())) {
			throw new NoReservationException("No reservations found");
		}
		
		IEC2s ec2s = res.get(0).getInstances();
		if ((ec2s == null) || (ec2s.isEmpty())) {
			throw new EmptyReservationException("Reservation contains no instances");
		}
		
		IEC2s fullList = ec2s.clone();
		
		for (int i = 1; i <res.size(); i++) {
			ec2s = res.get(i).getInstances();
			if ((ec2s == null) || (ec2s.isEmpty())) {
				throw new EmptyReservationException("Reservation contains no instances");
			}
			fullList.addAll(ec2s);
		}
		return fullList;
	}

	private static IReservations runApiCall(IDescribeEC2sRequest req, IEC2Env env) {
		return env.describeEC2s(req);
	}

	private static IDescribeEC2sRequest createRequest(IDescribeEC2sRequestFactory reqFactory, String serverName) {
		IFilter filter = Filter.create("tag:Name", serverName);
		reqFactory.addFilter(filter);
		
		return reqFactory.createRequest();
	}
}
