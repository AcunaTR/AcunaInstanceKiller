package com.thomsonreuters.aws.environment.ec2;

import com.thomsonreuters.aws.ami.IAmis;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeAmisRequest;
import com.thomsonreuters.aws.environment.ec2.request.IDescribeEC2sRequest;
import com.thomsonreuters.aws.environment.ec2.request.ITerminateInstancesRequest;
import com.thomsonreuters.aws.reservation.IReservations;

public interface IEC2Env  {

    IAmis describeAmis(IDescribeAmisRequest request);

    IReservations describeEC2s(IDescribeEC2sRequest request);
    
    void terminateInstances(ITerminateInstancesRequest req);
}
