package com.thomsonreuters.aws.environment.ec2;

import com.thomsonreuters.aws.ami.IAmis;
import com.thomsonreuters.aws.environment.ec2.request.ami.IDescribeAmisRequest;
import com.thomsonreuters.aws.environment.ec2.request.ec2.IDescribeEC2sRequest;
import com.thomsonreuters.aws.reservation.IReservations;

public interface IEC2Env  {

    IAmis describeAmis(IDescribeAmisRequest request);

    IReservations describeEC2s(IDescribeEC2sRequest request);

}
