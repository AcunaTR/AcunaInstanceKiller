package com.thomsonreuters.lambda.demo.exceptions;

public class NoReservationException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoReservationException(String message) {
		super(message);
	}

}
