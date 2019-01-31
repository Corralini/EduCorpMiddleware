package com.educorp.eduinteractive.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.impl.GeneroDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.spi.GeneroDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Genero;
import com.educorp.eduinteractive.ecommerce.service.spi.GeneroService;

public class GeneroServiceImpl implements GeneroService{

	private GeneroDAO generoDAO = null;
	
	public GeneroServiceImpl(){
		generoDAO = new GeneroDAOImpl();
	}
	
	@Override
	public List<Genero> findAll () throws DataException{
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			
			return generoDAO.findAll(c);
			
		}catch(SQLException ex) {
			throw new DataException(ex);
		}
	}
}
