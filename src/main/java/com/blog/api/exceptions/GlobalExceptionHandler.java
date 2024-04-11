package com.blog.api.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.api.utils.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandler(
			MethodArgumentNotValidException ex) {
		HashMap<String, String> response = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String defaultMessage = error.getDefaultMessage();
			response.put(fieldName, defaultMessage);
		});

		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiResponse> BadCredentialsExceptionHander(BadCredentialsException ex) {
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<ApiResponse> DisabledExceptionHander(DisabledException ex) {
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> ExceptionHandler(Exception ex) {
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.UNAUTHORIZED);
	}

}
