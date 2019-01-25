package com.educorp.eduinteractive.service;

import java.util.Date;

import com.educorp.eduinteractive.model.Estudiante;
import com.educorp.eduinteractive.service.impl.EstudianteServiceImpl;

public class EstudianteSignUpTest {

	public static void main (String args[]) throws Exception {
		EstudianteService estudianteService = new EstudianteServiceImpl();
		Estudiante e = new Estudiante();
		
		e.setEmail("acorralfdez@gmail.com");
		e.setIdPais("ES");
		e.setPsswd("alejandrocorral");
		e.setNombre("Alejandro");
		e.setApellido1("Corral");
		e.setApellido2("Fernández");
		e.setAnoNacimiento(2000);
		e.setFechaSubscripcion(new Date());
		e.setIdNivel(3);
		e.setIdGenero("H");
		estudianteService.signUp(e);
		
	}
}
