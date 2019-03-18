package com.educorp.eduinteractive.service;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Genero;
import com.educorp.eduinteractive.ecommerce.service.impl.GeneroServiceImpl;
import com.educorp.eduinteractive.ecommerce.service.spi.GeneroService;

public class GeneroServiceTest {

	public static void findByIdTest() {
		GeneroService generoService = new GeneroServiceImpl();
		Genero genero;
		try {
			genero = generoService.findById("h");
			System.out.println(genero);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		findByIdTest();
	}
	
}
