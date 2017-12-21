package com.amazonaws.lambda.demo.stubs;

import java.util.ArrayList;
import java.util.List;

import com.thomsonreuters.aws.reservation.IReservation;
import com.thomsonreuters.aws.reservation.IReservations;

public class ReservationsStub implements IReservations {

	private List<IReservation> _res;
	
	public ReservationsStub(IReservation reservation) {
		_res = new ArrayList<>();	
		_res.add(reservation);
	}

	public ReservationsStub(List<IReservation> reservations) {
		_res = reservations;		
	}

	@Override
	public int size() {
		return _res.size();
	}

	@Override
	public boolean isEmpty() {
		return _res.isEmpty();
	}

	@Override
	public IReservation get(int idx) {
		return _res.get(idx);
	}

}
