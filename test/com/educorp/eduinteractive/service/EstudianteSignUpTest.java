package com.educorp.eduinteractive.service;

import java.sql.Connection;
import java.util.Date;

import com.educorp.eduinteractive.dao.EstudianteDAO;
import com.educorp.eduinteractive.dao.impl.EstudianteDAOImpl;
import com.educorp.eduinteractive.dao.service.ConnectionManager;
import com.educorp.eduinteractive.model.Estudiante;
import com.educorp.eduinteractive.service.impl.EstudianteServiceImpl;

public class EstudianteSignUpTest {

	public static void main (String args[]) throws Exception {
		EstudianteService estudianteService = new EstudianteServiceImpl();
		EstudianteDAO dao = new EstudianteDAOImpl();
		Connection c = ConnectionManager.getConnection();
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
		
		System.out.println("" + e);
		
	}
}
