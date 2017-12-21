package com.thomsonreuters.lambda.demo;

import java.util.Arrays;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.thomsonreuters.aws.environment.ec2.EC2Env;
import com.thomsonreuters.aws.environment.ec2.IEC2Env;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        IEC2Env ec2Env = EC2Env.create();
        //test
        // TODO: implement your handler
        GetInstancesByName.run(ec2Env, "acuna.jenkins.server");
        
        
        
        return "hello";
        

    }  
    
    /*IEC2Env ec2Env = EC2Env.create();
    IDescribeAmisRequest req = DescribeAmisRequest.create();
    IFilter filter = Filter.create("tag:tr-latest", "True");
    IFilter filter2 = Filter.create("tag:tr-supported", "True");
    req.setFilters(Filters.create(Arrays.asList(filter, filter2)));
    IAmis amis = ec2Env.describeAmis(req);*/
}
