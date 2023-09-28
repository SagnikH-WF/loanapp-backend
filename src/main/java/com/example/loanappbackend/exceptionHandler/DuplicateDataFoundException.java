package com.example.loanappbackend.exceptionHandler;

public class DuplicateDataFoundException extends Exception{

	public DuplicateDataFoundException(String message) {
		super(message);
	}
}
