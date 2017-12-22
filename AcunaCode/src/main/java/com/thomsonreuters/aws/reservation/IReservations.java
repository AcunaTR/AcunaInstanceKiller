package com.thomsonreuters.aws.reservation;

public interface IReservations {

    int size();
    
    boolean isEmpty();
    
    IReservation get(int idx);
}
