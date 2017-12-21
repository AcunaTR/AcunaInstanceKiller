package com.thomsonreuters.aws.reservation;

import com.thomsonreuters.aws.ec2.IEC2s;

public interface IReservation {

	IEC2s getInstances();
	
}
