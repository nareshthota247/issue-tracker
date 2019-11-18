package com.pinguin.issuetracker.exceptionhandeling;

public class NoRecordException extends RuntimeException {

	private static final long serialVersionUID = 8150909800926226181L;
	
	public NoRecordException() {
		super();
	}
	
	public NoRecordException(String exception) {
		super(exception);
	}
	
}
