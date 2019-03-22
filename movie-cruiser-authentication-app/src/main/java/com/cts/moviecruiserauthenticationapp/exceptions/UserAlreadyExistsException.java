package com.cts.moviecruiserauthenticationapp.exceptions;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
