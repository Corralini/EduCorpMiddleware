package com.gzone.ecommerce.exceptions;

public class ServiceException extends BusinessException {
       

	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		this(message,null);		
	}
	

	public ServiceException(Throwable cause) {
		this(null,cause);		
	}
	
	public ServiceException(String message, Throwable cause) {
		super(message,cause);		
	}
    
}
