package com.educorp.eduinteractive.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.dao.impl.GeneroDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.GeneroDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Genero;
import com.educorp.eduinteractive.ecommerce.service.spi.GeneroService;

public class GeneroServiceImpl implements GeneroService{

	private Logger logger = LogManager.getLogger(GeneroServiceImpl.class);
	
	private GeneroDAO generoDAO = null;
	
	public GeneroServiceImpl(){
		generoDAO = new GeneroDAOImpl();
	}
	
	@Override
	public List<Genero> findAll () throws DataException{
		if(logger.isDebugEnabled()) logger.debug("all");
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			
			return generoDAO.findAll(c);
			
		}catch(SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c);
		}
	}

	@Override
	public Genero findById(String id) throws DataException {
	
		if(logger.isDebugEnabled()) logger.debug("Find by id --> {}", id);
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			
			return generoDAO.findById(c, id);
			
		}catch(SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c);
		}
		
	}
}
