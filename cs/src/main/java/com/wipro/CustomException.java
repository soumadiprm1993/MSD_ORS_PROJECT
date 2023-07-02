package com.wipro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomException {

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(NullPointerException e){
		return new ResponseEntity<>("your Data is empty!!",HttpStatus.BAD_REQUEST);
		
	}
}
