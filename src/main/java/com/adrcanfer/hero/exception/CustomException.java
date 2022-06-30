package com.adrcanfer.hero.exception;

public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8886830164206311067L;
	
	private Integer httpStatus;
	
	public CustomException(String message, Integer status) {
		super(message);
		this.httpStatus = status;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	

}
