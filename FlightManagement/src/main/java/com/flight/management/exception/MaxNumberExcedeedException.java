package com.flight.management.exception;

public class MaxNumberExcedeedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1366133267783233904L;

	public MaxNumberExcedeedException(String message) {
		super(message);
	}

	public MaxNumberExcedeedException() {
	}

}
