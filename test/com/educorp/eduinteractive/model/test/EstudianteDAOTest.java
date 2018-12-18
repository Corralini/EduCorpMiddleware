package com.educorp.eduinteractive.model.test;

import java.util.List;

import com.educorp.eduinteractive.dao.impl.EstudianteDAOImpl;
import com.educorp.eduinteractive.model.Estudiante;

public class EstudianteDAOTest {

	private EstudianteDAOImpl dao = null;
	
	public EstudianteDAOTest () {
		dao = new EstudianteDAOImpl();
	}
	
	public void testFindById()
		throws Exception {
		Estudiante e = dao.findById(5);
		System.out.println(e);
	}
	
	public void testFindByPais () 
		throws Exception {
		List<Estudiante> estudiantes = dao.findByPais(174);
		for (Estudiante e: estudiantes) {
			System.out.println(e);
		}
	}
	
	
	public static void main (String args[]) {
		try {
			EstudianteDAOTest test = new EstudianteDAOTest();
			test.testFindByPais();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
