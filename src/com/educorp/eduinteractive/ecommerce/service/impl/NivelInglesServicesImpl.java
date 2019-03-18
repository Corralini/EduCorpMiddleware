package com.educorp.eduinteractive.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.dao.impl.NivelInglesDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.NivelInglesDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.NivelIngles;
import com.educorp.eduinteractive.ecommerce.service.spi.NivelInglesServices;

public class NivelInglesServicesImpl implements NivelInglesServices{

	private Logger logger = LogManager.getLogger(NivelInglesServicesImpl.class);
	
	private NivelInglesDAO nivelInglesDAO = null;
	
	public NivelInglesServicesImpl() {
		nivelInglesDAO = new NivelInglesDAOImpl();
	}
	
	@Override
	public List<NivelIngles> findAll() throws DataException {
		if(logger.isDebugEnabled()) logger.debug("all");
		Connection c = null;
		
		try {
			
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			
			return nivelInglesDAO.findAll(c);
			
		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c);
		}
	}

	@Override
	public NivelIngles findById(Integer id) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("Find by id --> {}", id);
		Connection c = null;
		
		try {
			
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			
			return nivelInglesDAO.findById(c, id);
			
		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c);
		}
	}

}
