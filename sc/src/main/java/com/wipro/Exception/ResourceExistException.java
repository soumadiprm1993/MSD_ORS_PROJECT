package com.wipro.Exception;

import org.springframework.http.HttpStatus;

public class ResourceExistException extends Exception{

	public ResourceExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResourceExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ResourceExistException(String customer_Cart, HttpStatus conflict) {
		// TODO Auto-generated constructor stub
	}
	
}
