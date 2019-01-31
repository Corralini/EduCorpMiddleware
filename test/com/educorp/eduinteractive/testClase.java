package com.educorp.eduinteractive;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.MailException;
import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.service.impl.EstudianteServiceImpl;
import com.educorp.eduinteractive.ecommerce.service.spi.EstudianteService;

public class testClase {

public static void main(String[] args) {
		

		Estudiante e = new Estudiante();
		
		e.setNombre("Alejandro");
		e.setApellido1("Corral");
		e.setApellido2("Fernandez");
		e.setAnoNacimiento(1992);
		e.setEmail("acorralfdez@gmail.com");
		e.setPsswd("abc123.");
		e.setIdNivel(2);
		e.setIdPais("ES");
		e.setIdGenero("H");
		
		EstudianteService estudiante = new EstudianteServiceImpl();
		
		try {
			estudiante.signUp(e);
		} catch (MailException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Mail");
		} catch (DuplicateInstanceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Duplicado");
		} catch (DataException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Datos");
		}
		System.out.println("Correcto");
	}
	
}
