package com.educorp.eduinteractive.service;

import java.util.Date;

import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.service.impl.MailServiceImpl;
import com.educorp.eduinteractive.ecommerce.service.spi.MailService;

public class SendMailTest {

	public static void main (String args[]) throws Exception {
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
		String sub = "Hola " + e.getNombre()
					+ " " + e.getApellido1()
					+ "el equipo de Educorp Interactive le da la bienvenida a la plataforma Educorp, "
					+ "donde el aprendizaje no conoce fronteras ni límites.";
		MailService mailService = new MailServiceImpl();
		mailService.sendEmail(e.getEmail(), "Bienvendio a EduCorp", sub);
	}

}
