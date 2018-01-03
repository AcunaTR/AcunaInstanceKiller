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
	private static final int BUFFER = 5;
	
    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        
        try {
        	
			terminateServers(getOldServers(getAllServers()));
			
		} catch (InvalidInstancesException | NoTargetGroupException | InvalidTargetGroupsException
				| NoInstancesException | EmptyReservationException | NoReservationException e) {
			
			ISNSEnv snsEnv = SNSEnv.create();
			
			String errorMessage = "AcunaLambdaKillter failed to execute - Caught Exception - " + e.getMessage();
			
			context.getLogger().log(errorMessage);
			snsEnv.publish(ERROR_TOPIC_ARN, errorMessage);
			return errorMessage;
		}      
               
        return "woot!";
    }  
    
    private IEC2s getAllServers() throws InvalidInstancesException, NoInstancesException, EmptyReservationException, NoReservationException{
    	IEC2Env ec2Env = EC2Env.create();
    	IDescribeEC2sRequestFactory factory = new DescribeEC2sRequestFactory();
 	
    	return InstanceHandler.getInstanceByTagname(ec2Env, "acuna.jenkins.server", factory);

    }

    private IEC2s getOldServers(IEC2s allServers) throws InvalidInstancesException, NoTargetGroupException, InvalidTargetGroupsException {
    	IELBEnv elbEnv = ELBEnv.create();
        IDescribeTargetGroupsRequestFactory reqFactory = new DescribeTargetGroupsRequestFactory();
        return OldServer.identifyOldServers(elbEnv, BUFFER, allServers, reqFactory);
    }

    private void terminateServers(IEC2s servers) {
    	IEC2Env ec2Env = EC2Env.create();
    	ITerminateInstancesRequestFactory terminateReqFactory = new TerminateInstancesRequestFactory();	
		TerminateServers.terminateInstances(servers, ec2Env, terminateReqFactory);  
    }
}
