package com.carTravelsky.excetion;

public class AppRuntimeException extends RuntimeException{
	private static final long serialVersionUID = 5721989783196421405L;
	
	/**
	 * @param message
	 */
	public AppRuntimeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AppRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AppRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
