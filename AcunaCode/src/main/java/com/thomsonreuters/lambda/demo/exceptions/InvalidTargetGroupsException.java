package com.thomsonreuters.lambda.demo.exceptions;

public class InvalidTargetGroupsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTargetGroupsException(String message){
		super(message);
	}
}
