package com.ms.order.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderIdErrorHandler {
	
	@ExceptionHandler(OrderIdNotFound.class)
	ResponseEntity<ErrorDetails> handleOrderIdNotFound(OrderIdNotFound msg){
		ErrorDetails error = new ErrorDetails(LocalDateTime.now(),msg.getMessage(),"404 - id not found");
		return new ResponseEntity<ErrorDetails> (error,HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ErrorDetails> handleAllProblems(Exception e) {
		ErrorDetails details = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Problem in execution");
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
