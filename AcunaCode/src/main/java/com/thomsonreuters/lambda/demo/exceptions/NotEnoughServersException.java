package com.thomsonreuters.lambda.demo.exceptions;

public class NotEnoughServersException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotEnoughServersException(String message) {
		super(message);
	}
	

}
