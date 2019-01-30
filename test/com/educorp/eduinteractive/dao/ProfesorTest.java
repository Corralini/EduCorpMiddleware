package com.educorp.eduinteractive.dao;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.impl.ProfesorDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.ProfesorDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Profesor;
import com.educorp.eduinteractive.ecommerce.service.criteria.ProfesorCriteria;

public class ProfesorTest {


	private ProfesorDAO profesorDAO = null;

	public ProfesorTest() {
		profesorDAO = new ProfesorDAOImpl();
	}


	public void findByIdTest (Integer id) throws DataException {
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			Profesor e = profesorDAO.findById(c, id);

			System.out.println(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(c);
		}
	}
	
	public void findByCriteriaTest (ProfesorCriteria criteria) throws DataException{
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			List <Profesor> profesores = profesorDAO.findByCriteria(c, criteria);
			
			for(Profesor p: profesores) {
				System.out.println("" + p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(c);
		}
	}

	public static void main(String[] args) {
		ProfesorTest test = new ProfesorTest();
		ProfesorCriteria criteria = new ProfesorCriteria();
		try {
//			test.findByIdTest(6);
			criteria.setPrecioSesion(5.0d);
			criteria.setPrecioSesionHasta(7.0d);
			test.findByCriteriaTest(criteria);
		} catch (DataException e) {

			e.printStackTrace();
		}
	}

}
