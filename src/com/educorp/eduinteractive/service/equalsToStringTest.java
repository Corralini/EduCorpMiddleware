package com.educorp.eduinteractive.service;

import com.educorp.eduinteractive.model.Sesion;

public class equalsToStringTest {

	public static void main(String args[]) {
		
		Sesion s = new Sesion();
		
		s.setIdProfesor(25);
		s.setIdEstudiante(25);
		
		System.out.println(s.getIdProfesor().toString().equals(s.getIdEstudiante().toString()));
		
	}
	
}
