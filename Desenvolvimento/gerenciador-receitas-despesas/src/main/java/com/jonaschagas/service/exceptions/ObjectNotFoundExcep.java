package com.jonaschagas.service.exceptions;

public class ObjectNotFoundExcep extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundExcep(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundExcep(String msg, Throwable cause) {
		super(msg, cause);
	}
}
