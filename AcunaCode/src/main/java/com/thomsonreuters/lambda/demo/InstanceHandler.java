package com.thomsonreuters.lambda.demo;

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

public class InstanceHandler {

	public static IEC2s getInstanceByTagname(IEC2Env env,
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
		throw new InvalidInstancesException("Jenkins sandbox AcunaInstanceKiller error: InvalidInstancesException - EC2s returned did not match - " + serverName + " - Actual result - " + ec2s.get(0).getTags().getValue("Name"));
		
	}

	// This was changed from private to public to allow testing of EmptyReservationException from this access point
	public static boolean checkEc2s(IEC2s ec2s, String serverName) throws EmptyReservationException { // separate utility class and tests?
		if (ec2s == null) {
			throw new EmptyReservationException("Jenkins sandbox AcunaInstanceKiller error: Reservation contains no instances");
		
		}
		if (ec2s.isEmpty()) {
			throw new EmptyReservationException("Jenkins sandbox AcunaInstanceKiller error: Reservation contains no instances");
		}
		
		for (int i = 0; i < ec2s.size(); i++) {
			IEC2 ec2 = ec2s.get(i);
			if(!ec2.getTags().getValue("Name").equals(serverName)){
				return false;
			}
		}
		return true;
	}

	private static IEC2s parsingEC2s(IReservations res) throws EmptyReservationException, NoReservationException {// separate utility class and tests?
		if (res == null) { 
			throw new NoReservationException("Jenkins sandbox AcunaInstanceKiller error: NoReservationException - No reservations found");
		}
		if (res.isEmpty()) {
		throw new EmptyReservationException("Jenkins sandbox AcunaInstanceKiller error: EmptyReservationException - No reservations found");
		}
		
		IEC2s ec2s = res.get(0).getInstances();
		if ((ec2s == null) || (ec2s.isEmpty())) {
			throw new EmptyReservationException("Jenkins sandbox AcunaInstanceKiller error: EmptyReservationException - Reservation contains no instances");
		}
		
		IEC2s fullList = ec2s.clone();
		
		for (int i = 1; i <res.size(); i++) {
			ec2s = res.get(i).getInstances();
			if ((ec2s == null) || (ec2s.isEmpty())) {
				throw new EmptyReservationException("Jenkins sandbox AcunaInstanceKiller error: EmptyReservationException - Reservation contains no instances");
			}
			fullList.addAll(ec2s);
		}
		return fullList;
	}

	private static IReservations runApiCall(IDescribeEC2sRequest req, IEC2Env env) {// separate utility class and tests?
		return env.describeEC2s(req);
	}

	private static IDescribeEC2sRequest createRequest(IDescribeEC2sRequestFactory reqFactory, String serverName) {
		IFilter filter = Filter.create("tag:Name", serverName);
		reqFactory.addFilter(filter);
		
		return reqFactory.createRequest();
	}
}
