package com.educorp.eduinteractive.dao;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.impl.EstudianteDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.EstudianteDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.service.criteria.EstudianteCriteria;

public class EstudianteTest {
	
	private EstudianteDAO estudianteDAO = null;
	
	public EstudianteTest() {
		estudianteDAO = new EstudianteDAOImpl();
	}
	
	public void findByIdTest (Integer id) throws DataException {
		Connection c = null;
			try {
				c = ConnectionManager.getConnection();
				Estudiante e = estudianteDAO.findById(c, id);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCUtils.closeConnection(c);
			}
	}
	
	public void findByNombre (String nombre) throws DataException {
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			List <Estudiante> estudiantes = estudianteDAO.findByNombre(c, nombre);
			
			for(Estudiante e: estudiantes) {
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(c);
		}
	}
	
	public void findByCriteria (EstudianteCriteria criteria) throws DataException {
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			List <Estudiante> estudiantes = estudianteDAO.findByCriteria(c, criteria);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(c);
		}
	}
	public static void main(String[] args) {
		
	EstudianteTest test = new EstudianteTest();
	EstudianteCriteria criteria = new EstudianteCriteria();
	try {
//		test.findByIdTest(3);
//		test.findByNombre("R");
		criteria.setNombre("dytufvgyhbjksdgfhkjlijlbsnjñfdsñnjvfszknjñvsakjñvsknjñvfszkjñsakñvsañsañjvsañj");
		criteria.setIdNivel(7896523);
		test.findByCriteria(criteria);
		
	} catch (DataException e) {

		e.printStackTrace();
	}
			
	}
	
}