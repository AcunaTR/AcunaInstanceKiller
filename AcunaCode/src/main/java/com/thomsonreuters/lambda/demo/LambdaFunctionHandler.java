package com.thomsonreuters.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.thomsonreuters.aws.ec2.IEC2s;
import com.thomsonreuters.aws.environment.ec2.EC2Env;
import com.thomsonreuters.aws.environment.ec2.IEC2Env;
import com.thomsonreuters.aws.environment.elb.ELBEnv;
import com.thomsonreuters.aws.environment.elb.IELBEnv;
import com.thomsonreuters.aws.environment.sns.ISNSEnv;
import com.thomsonreuters.aws.environment.sns.SNSEnv;
import com.thomsonreuters.lambda.demo.exceptions.EmptyReservationException;
import com.thomsonreuters.lambda.demo.exceptions.InvalidInstancesException;
import com.thomsonreuters.lambda.demo.exceptions.InvalidTargetGroupsException;
import com.thomsonreuters.lambda.demo.exceptions.NoInstancesException;
import com.thomsonreuters.lambda.demo.exceptions.NoReservationException;
import com.thomsonreuters.lambda.demo.exceptions.NoTargetGroupException;
import com.thomsonreuters.lambda.demo.factories.IDescribeEC2sRequestFactory;
import com.thomsonreuters.lambda.demo.factories.IDescribeTargetGroupsRequestFactory;
import com.thomsonreuters.lambda.demo.factories.ITerminateInstancesRequestFactory;
import com.thomsonreuters.lambda.demo.factories.impl.DescribeEC2sRequestFactory;
import com.thomsonreuters.lambda.demo.factories.impl.DescribeTargetGroupsRequestFactory;
import com.thomsonreuters.lambda.demo.factories.impl.TerminateInstancesRequestFactory;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

	private static final String ERROR_TOPIC_ARN = "arn:aws:sns:us-east-1:015887481462:AcunaLambdaFailTopic";
	
    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        IEC2Env ec2Env = EC2Env.create();
        ISNSEnv snsEnv = SNSEnv.create();
        IDescribeEC2sRequestFactory factory = new DescribeEC2sRequestFactory();
        
        IEC2s allServers;
        
        try {
        	
        	allServers = InstanceHandler.getInstanceByTagname(ec2Env, "acuna.jenkins.server", factory);

        } catch (InvalidInstancesException e) {
			context.getLogger().log("Caught InvalidInstancesException - " + e.getMessage());
			snsEnv.publish(ERROR_TOPIC_ARN, "AcunaLambdaKillter failed to execute - Caught InvalidInstancesException - " + e.getMessage());
			return "Caught InvalidInstancesException - " + e.getMessage();
		} catch (NoInstancesException e) {
			context.getLogger().log("Caught NoInstancesException - " + e.getMessage());
			snsEnv.publish(ERROR_TOPIC_ARN, "AcunaLambdaKillter failed to execute - Caught NoInstancesException - " + e.getMessage());
			return "Caught NoInstancesException - " + e.getMessage();
		} catch (EmptyReservationException e) {
			context.getLogger().log("Caught EmptyReservationException - " + e.getMessage());
			snsEnv.publish(ERROR_TOPIC_ARN, "AcunaLambdaKillter failed to execute - Caught EmptyReservationException - " + e.getMessage());
			return "Caught EmptyReservationException - " + e.getMessage();
		} catch (NoReservationException e) {
			context.getLogger().log("Caught NoReservationException - " + e.getMessage());
			snsEnv.publish(ERROR_TOPIC_ARN, "AcunaLambdaKillter failed to execute - Caught NoReservationException - " + e.getMessage());
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
			snsEnv.publish(ERROR_TOPIC_ARN, "AcunaLambdaKillter failed to execute - Caught InvalidInstancesException - " + e.getMessage());
			return "Caught InvalidInstancesException - " + e.getMessage();
		} catch (NoTargetGroupException e) {
			context.getLogger().log("Caught NoTargetGroupException - " + e.getMessage());
			snsEnv.publish(ERROR_TOPIC_ARN, "AcunaLambdaKillter failed to execute - Caught NoTargetGroupException - " + e.getMessage());
			return "Caught NoTargetGroupException - " + e.getMessage();
		} catch (InvalidTargetGroupsException e) {
			context.getLogger().log("Caught InvalidTargetGroupsException - " + e.getMessage());
			snsEnv.publish(ERROR_TOPIC_ARN, "AcunaLambdaKillter failed to execute - Caught InvalidTargetGroupsException - " + e.getMessage());
			return "Caught InvalidTargetGroupsException - " + e.getMessage();
		}

        
       
        ITerminateInstancesRequestFactory terminateReqFactory = new TerminateInstancesRequestFactory();	
		TerminateServers.terminateInstances(oldServers, ec2Env, terminateReqFactory);        
        
        
        
               
        return "woot!";
        

    }  
}
