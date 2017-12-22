package com.thomsonreuters.lambda.demo.exceptions;

public class EmptyReservationException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmptyReservationException(String message) {
		super(message);
	}
}
