package com.adrcanfer.hero.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

	private static final long serialVersionUID = -8886830164206311067L;
	
	private HttpStatus httpStatus;
	
	public CustomException(String message, HttpStatus status) {
		super(message);
		this.httpStatus = status;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	

}
