package com.thomsonreuters.lambda.demo;

import java.util.Arrays;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.thomsonreuters.aws.ami.IAmis;
import com.thomsonreuters.aws.environment.ec2.EC2Env;
import com.thomsonreuters.aws.environment.ec2.IEC2Env;
import com.thomsonreuters.aws.filter.Filter;
import com.thomsonreuters.aws.filter.Filters;
import com.thomsonreuters.aws.filter.IFilter;
import com.thomsonreuters.aws.environment.ec2.request.ami.DescribeAmisRequest;
import com.thomsonreuters.aws.environment.ec2.request.ami.IDescribeAmisRequest;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        //test
        // TODO: implement your handler
        
       IEC2Env ec2Env = EC2Env.create();
        /*IDescribeAmisRequest req = DescribeAmisRequest.create();
        IFilter filter = Filter.create("tag:tr-latest", "True");
        IFilter filter2 = Filter.create("tag:tr-supported", "True");
        req.setFilters(Filters.create(Arrays.asList(filter, filter2)));
        IAmis amis = ec2Env.describeAmis(req);*/
        
        //return amis.toString();
        return "hello";

    }    
}
