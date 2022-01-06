package com.crud.client.resources.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {

	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> notFound(NotFoundException e, HttpServletResponse response) {
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<StandardError> badRequestException(BadRequestException e, HttpServletResponse response) { 
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationStandardError> validationException(MethodArgumentNotValidException e, HttpServletResponse response) {
		ValidationStandardError standardError = new ValidationStandardError();
		e.getFieldErrors().forEach(error -> {
			standardError.getErrors().add(FieldError.builder()
											.field(error.getField())
											.message(error.getDefaultMessage())
											.build());
		});
		standardError.setError(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
		standardError.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		standardError.setMessage("ValidationError");
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(standardError);
	
	}
}
