package com.educorp.eduinteractive.dao;

import java.sql.Connection;
import java.util.Date;

import com.educorp.eduinteractive.dao.impl.EstudianteDAOImpl;
import com.educorp.eduinteractive.dao.service.ConnectionManager;
import com.educorp.eduinteractive.model.Estudiante;

public class EstudianteCreateTest {

	public static void main (String args[]) throws Exception {
		EstudianteDAO dao = new EstudianteDAOImpl();
		Estudiante e = new Estudiante();
		Connection c = ConnectionManager.getConnection();

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
		
		dao.create(c, e);
	}

}
