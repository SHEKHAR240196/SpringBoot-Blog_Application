package com.bikkadit.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bikkadit.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourcNotFoundExceptionHandler(ResourceNotFoundException ex){
		
		String massage = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(massage ,false);
		
		return new ResponseEntity<ApiResponse>(apiResponse , HttpStatus.NOT_FOUND);
		
	}
	
	

}
