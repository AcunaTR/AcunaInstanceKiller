package com.thomsonreuters.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.thomsonreuters.aws.environment.ec2.EC2Env;
import com.thomsonreuters.aws.environment.ec2.IEC2Env;
import com.thomsonreuters.lambda.demo.exceptions.EmptyReservationException;
import com.thomsonreuters.lambda.demo.exceptions.InvalidInstancesException;
import com.thomsonreuters.lambda.demo.exceptions.NoInstancesException;
import com.thomsonreuters.lambda.demo.exceptions.NoReservationException;
import com.thomsonreuters.lambda.demo.factories.IDescribeEC2sRequestFactory;
import com.thomsonreuters.lambda.demo.factories.impl.DescribeEC2sRequestFactory;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        IEC2Env ec2Env = EC2Env.create();
        IDescribeEC2sRequestFactory factory = new DescribeEC2sRequestFactory();
        //test
        // TODO: implement your handler
        try {
			
        	
        	GetInstancesByName.run(ec2Env, "acuna.jenkins.server", factory);
		
        
        
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
        
        
        
        return "hello";
        

    }  
    
    /*IEC2Env ec2Env = EC2Env.create();
    IDescribeAmisRequest req = DescribeAmisRequest.create();
    IFilter filter = Filter.create("tag:tr-latest", "True");
    IFilter filter2 = Filter.create("tag:tr-supported", "True");
    req.setFilters(Filters.create(Arrays.asList(filter, filter2)));
    IAmis amis = ec2Env.describeAmis(req);*/
}
