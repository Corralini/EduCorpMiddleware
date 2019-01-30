package com.educorp.eduinteractive.service;

import com.educorp.eduinteractive.ecommerce.service.impl.EstudianteServiceImpl;
import com.educorp.eduinteractive.ecommerce.service.spi.EstudianteService;

public class EstudianteServicesLoginTest {
	
	
	public static void main (String args[]) throws Exception {
		EstudianteService estudianteService = new EstudianteServiceImpl();
		
		
		estudianteService.login("BenBooth@gustr.com", "fdghgjj");
		
	}
	
}
