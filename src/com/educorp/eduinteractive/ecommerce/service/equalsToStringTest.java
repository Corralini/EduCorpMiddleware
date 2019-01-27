package com.educorp.eduinteractive.ecommerce.service;

import com.educorp.eduinteractive.ecommerce.model.Sesion;

public class equalsToStringTest {

	public static void main(String args[]) {
		
		Sesion s = new Sesion();
		
		s.setIdProfesor(25);
		s.setIdEstudiante(25);
		
		System.out.println(s.getIdProfesor().toString().equals(s.getIdEstudiante().toString()));
		
	}
	
}
