package com.ms.order.Exceptions;

public class OrderIdNotFound extends RuntimeException{
	
	private static final long serialVersionUID = 3L;

	public OrderIdNotFound(String result){
		super(result);
		}
}