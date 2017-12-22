package com.thomsonreuters.aws.reservation.impl;

import java.util.List;

import com.amazonaws.services.ec2.model.Reservation;
import com.thomsonreuters.aws.reservation.IReservation;
import com.thomsonreuters.aws.reservation.IReservations;

public class ReservationsImpl implements IReservations {

    private final List<Reservation> _resList;

    public ReservationsImpl(List<Reservation> reservations) {
        _resList = reservations;
    }

    @Override
    public int size() {
        return _resList.size();
    }

    @Override
    public boolean isEmpty() {
        return _resList.isEmpty();
    }

    @Override
    public IReservation get(int idx) {
        return new ReservationImpl(_resList.get(idx));
    }
}
