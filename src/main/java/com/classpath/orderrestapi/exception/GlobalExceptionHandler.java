package com.classpath.orderrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleInvaliOrderId(IllegalArgumentException exception) {
		log.error("Exception while fetching invalid order id :: {}", exception.getMessage());
		return new Error(101, exception.getMessage());
	}
}

@Getter
@AllArgsConstructor
class Error {
	private int code;
	private String message;
}
