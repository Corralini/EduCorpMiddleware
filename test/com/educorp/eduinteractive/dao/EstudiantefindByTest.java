package com.educorp.eduinteractive.dao;

import com.educorp.eduinteractive.dao.impl.EstudianteDAOImpl;
import com.educorp.eduinteractive.model.Estudiante;

public class EstudiantefindByTest {

	public static void main (String args[]) {
	try {
		
		int id = 2;
		
		Estudiante e = new Estudiante ();
		
		EstudianteDAOImpl estudianteDAO = new EstudianteDAOImpl();
		
		e = estudianteDAO.findById(id);
		System.out.println(e);
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
}
