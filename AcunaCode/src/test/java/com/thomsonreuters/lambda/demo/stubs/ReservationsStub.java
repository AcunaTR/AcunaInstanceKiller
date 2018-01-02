package com.thomsonreuters.lambda.demo.stubs;

import java.util.ArrayList;
import java.util.List;

import com.thomsonreuters.aws.reservation.IReservation;
import com.thomsonreuters.aws.reservation.IReservations;

public class ReservationsStub implements IReservations {

	private List<ReservationStub> _res;
	private int _getReservationCounter;
	
	public ReservationsStub(ReservationStub reservation) {
		_res = new ArrayList<>();	
		_res.add(reservation);
		_getReservationCounter = 0;
	}

	public ReservationsStub(List<ReservationStub> reservations) {
		_res = reservations;	
		_getReservationCounter = 0;
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
		_getReservationCounter++;
		return _res.get(idx);
	}
	
	public int get_getReservationCounter() {
			return _getReservationCounter;
		}
	
	public List<ReservationStub> getReservations(){
		return _res;
	}

}
