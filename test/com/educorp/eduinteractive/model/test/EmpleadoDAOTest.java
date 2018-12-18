package com.jal.training.dao;

import java.util.List;

import com.jal.training.model.Empleado;

public class EmpleadoDAOTest {
	
	private EmpleadoDAO dao = null;
	
	public EmpleadoDAOTest() {
		dao = new EmpleadoDAO();		
	}
	
	public void testFindById() 
		throws Exception {
		Empleado e = dao.findById(101);
		System.out.println(e);
	}
	
	public void testFindByNombre() 
		throws Exception {
		List<Empleado> empleados = dao.findByNombre("a", "b");
		for (Empleado e:empleados) {
			System.out.println(e);
		}
		
	}
	
	
	public static void main(String args[]) {
		try {
			EmpleadoDAOTest test = new EmpleadoDAOTest();
			test.testFindById();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
