package com.educorp.eduinteractive.exceptions;


import java.io.PrintStream;
import java.io.PrintWriter;

public class EducorpExceptions extends Exception {
	
		
	public EducorpExceptions() {
		super();
	}
	
	public EducorpExceptions(String message) {
		this(message, null);		
	}
	
	public EducorpExceptions(Throwable cause) {
		this(null,cause);		
	}
	
	public EducorpExceptions(String message, Throwable cause) {
		super(message,cause);		
	}			
	
	public void printStackTrace() {
		if (getCause()!=null) {
			getCause().printStackTrace();
		} else {
			super.printStackTrace();
		}
	}
	

	public void printStackTrace(PrintStream s) {
		if (getCause()!=null) {
			getCause().printStackTrace(s);
		} else {
			super.printStackTrace(s);
		}
	}	
	
	public void printStackTrace(PrintWriter w) {
		if (getCause()!=null) {
			getCause().printStackTrace(w);
		} else {
			super.printStackTrace(w);
		}
	}	
}
