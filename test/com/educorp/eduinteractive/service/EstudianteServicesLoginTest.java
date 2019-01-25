package com.educorp.eduinteractive.service;

import com.educorp.eduinteractive.service.impl.EstudianteServiceImpl;

public class EstudianteServicesLoginTest {
	
	
	public static void main (String args[]) throws Exception {
		EstudianteService estudianteService = new EstudianteServiceImpl();
		
		
		estudianteService.login("BenBooth@gustr.com", "Ec5oodae6yui");
		
	}
	
}
