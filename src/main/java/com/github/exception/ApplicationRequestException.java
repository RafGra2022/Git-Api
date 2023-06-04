package com.github.exception;

public class ApplicationRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8885368098566584110L;

	public ApplicationRequestException(String message) {
		super(message);
	}
	
	public ApplicationRequestException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
}
