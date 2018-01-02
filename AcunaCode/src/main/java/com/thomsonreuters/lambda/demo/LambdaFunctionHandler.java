package com.thomsonreuters.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.environment.ec2.EC2Env;
import com.thomsonreuters.aws.environment.ec2.IEC2Env;
import com.thomsonreuters.aws.environment.elb.ELBEnv;
import com.thomsonreuters.aws.environment.elb.IELBEnv;
import com.thomsonreuters.lambda.demo.exceptions.EmptyReservationException;
import com.thomsonreuters.lambda.demo.exceptions.InvalidInstancesException;
import com.thomsonreuters.lambda.demo.exceptions.InvalidTargetGroupsException;
import com.thomsonreuters.lambda.demo.exceptions.NoInstancesException;
import com.thomsonreuters.lambda.demo.exceptions.NoReservationException;
import com.thomsonreuters.lambda.demo.exceptions.NoTargetGroupException;
import com.thomsonreuters.lambda.demo.factories.IDescribeEC2sRequestFactory;
import com.thomsonreuters.lambda.demo.factories.IDescribeTargetGroupsRequestFactory;
import com.thomsonreuters.lambda.demo.factories.impl.DescribeEC2sRequestFactory;
import com.thomsonreuters.lambda.demo.factories.impl.DescribeTargetGroupsRequestFactory;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        IEC2Env ec2Env = EC2Env.create();
        IDescribeEC2sRequestFactory factory = new DescribeEC2sRequestFactory();
        
        IEC2s allServers;

        try {
        	
        	allServers = InstanceHandler.getInstanceByTagname(ec2Env, "acuna.jenkins.server", factory);

        } catch (InvalidInstancesException e) {
			context.getLogger().log("Caught InvalidInstancesException - " + e.getMessage());
			return "Caught InvalidInstancesException - " + e.getMessage();
		} catch (NoInstancesException e) {
			context.getLogger().log("Caught NoInstancesException - " + e.getMessage());
			return "Caught NoInstancesException - " + e.getMessage();
		} catch (EmptyReservationException e) {
			context.getLogger().log("Caught EmptyReservationException - " + e.getMessage());
			return "Caught EmptyReservationException - " + e.getMessage();
		} catch (NoReservationException e) {
			context.getLogger().log("Caught NoReservationException - " + e.getMessage());
			return "Caught NoReservationException - " + e.getMessage();
		}
        
        int buffer = 5;
        
        IELBEnv elbEnv = ELBEnv.create();
        IDescribeTargetGroupsRequestFactory reqFactory = new DescribeTargetGroupsRequestFactory();
        IEC2s oldServers;
        
        try {
        	
			oldServers = OldServer.identifyOldServers(elbEnv, buffer, allServers, reqFactory);
			
		} catch (InvalidInstancesException e) {
			context.getLogger().log("Caught InvalidInstancesException - " + e.getMessage());
			return "Caught InvalidInstancesException - " + e.getMessage();
		} catch (NoTargetGroupException e) {
			context.getLogger().log("Caught NoTargetGroupException - " + e.getMessage());
			return "Caught NoTargetGroupException - " + e.getMessage();
		} catch (InvalidTargetGroupsException e) {
			context.getLogger().log("Caught InvalidTargetGroupsException - " + e.getMessage());
			return "Caught InvalidTargetGroupsException - " + e.getMessage();
		}

        return oldServers.toString();
        

    }  
}
