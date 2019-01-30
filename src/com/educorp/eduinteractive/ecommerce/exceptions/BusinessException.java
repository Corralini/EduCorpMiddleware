package com.educorp.eduinteractive.ecommerce.exceptions;

import com.educorp.eduinteractive.exceptions.EducorpExceptions;

/**
 * Excepcion raiz para todas las excepciones de la capa de negocio del sistema.
 * 
 * (NO confundir esta capa "modelo" con la capa modelo en MVC,
 * que se usa en los diferentes tipos de clientes (web, app, desktop, etc.))
 */
public class BusinessException extends EducorpExceptions {
       

	public BusinessException() {
		super();
	}
	
	public BusinessException(String message) {
		this(message,null);		
	}
	

	public BusinessException(Throwable cause) {
		this(null,cause);		
	}
	
	public BusinessException(String message, Throwable cause) {
		super(message,cause);		
	}
    
}
