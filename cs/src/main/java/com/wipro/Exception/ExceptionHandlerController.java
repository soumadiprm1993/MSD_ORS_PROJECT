package com.wipro.Exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionHandlerController {
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	@ResponseStatus(value=HttpStatus.IM_USED)
	@ResponseBody
	ExceptionResponse handleResourceNotFoundException(ResourceAlreadyExistException exception, HttpServletRequest request)
	{
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorMessage(exception.getMessage());
		response.setRequestURI(request.getRequestURI());
		
		return response;
		
	}

}
