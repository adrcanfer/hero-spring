package com.adrcanfer.hero.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.adrcanfer.hero.exception.CustomException;
import com.adrcanfer.hero.exception.dto.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = { CustomException.class })
	protected ResponseEntity<Object> handleConflict(CustomException ex, WebRequest request) {
		ErrorResponse res = new ErrorResponse();
		res.setMessage(ex.getMessage());
		res.setStatus(ex.getHttpStatus().value());
		
		return handleExceptionInternal(ex, res, new HttpHeaders(), ex.getHttpStatus(), request);
	}
}
