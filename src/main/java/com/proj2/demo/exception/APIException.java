package com.proj2.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class APIException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private ExceptionResponse response = new ExceptionResponse();
	
	public APIException(String message) {
		response.setTimeStamp(LocalDateTime.now());
		response.setError(HttpStatus.BAD_REQUEST);
		response.setMessage(message);
		response.setStatus(HttpStatus.BAD_REQUEST.value());
	}
	
	public APIException(String message, HttpStatus error) {
		response.setTimeStamp(LocalDateTime.now());
		response.setError(error);
		response.setMessage(message);
		response.setStatus(error.value());
	}
	public ExceptionResponse getResponse() {
		return response;
	}
	public String getMessage() {
		return response.getMessage();
	}
	public HttpStatus getStatus() {
		return response.getError();
	}
}