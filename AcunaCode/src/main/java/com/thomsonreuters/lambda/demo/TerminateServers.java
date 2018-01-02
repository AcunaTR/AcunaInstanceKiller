package com.thomsonreuters.lambda.demo;

import java.util.ArrayList;
import java.util.List;

import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.environment.ec2.IEC2Env;
import com.thomsonreuters.aws.environment.ec2.request.ITerminateInstancesRequest;
import com.thomsonreuters.lambda.demo.factories.ITerminateInstancesRequestFactory;

public class TerminateServers {

	public static void terminateInstances(IEC2s oldServers, IEC2Env ec2Env, ITerminateInstancesRequestFactory reqFactory) {
		List<String> instanceIds = new ArrayList<>();
		for (int i = 0; i< oldServers.size(); i++) {
			String ec2Id = oldServers.get(i).getInstanceID();
			instanceIds.add(ec2Id);
		}
		
		ITerminateInstancesRequest req =  createRequest(reqFactory, instanceIds);
		runApiCall(req, ec2Env);		
	}

	private static ITerminateInstancesRequest createRequest(ITerminateInstancesRequestFactory reqFactory,
			List<String> instanceIds) {
		reqFactory.setInstanceIds(instanceIds);
		return reqFactory.createRequest();
	}

	private static void runApiCall(ITerminateInstancesRequest req, IEC2Env ec2Env) {
		ec2Env.terminateInstances(req);
	}
}





/*  
 * 
 *    private void terminateServer(AmazonEC2 env, List<Instance> oldServers) {

List<String> instanceIds = new ArrayList<>(); 
for (Instance oldServ : oldServers) {
	String ec2Id = oldServ.getInstanceId();
	instanceIds.add(ec2Id);   		
}
TerminateInstancesRequest req = new TerminateInstancesRequest(instanceIds);	
env.terminateInstances(req);
}


*/