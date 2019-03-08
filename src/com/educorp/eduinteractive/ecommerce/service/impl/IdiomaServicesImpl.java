package com.educorp.eduinteractive.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.dao.impl.IdiomaDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.IdiomaDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Idioma;
import com.educorp.eduinteractive.ecommerce.service.spi.IdiomaServices;

public class IdiomaServicesImpl implements IdiomaServices{

	private Logger logger = LogManager.getLogger(IdiomaServicesImpl.class);
	
	private IdiomaDAO idiomaDAO= null;
	
	public IdiomaServicesImpl() {
		idiomaDAO = new IdiomaDAOImpl();
	}
			
	
	@Override
	public List<Idioma> findAll() throws DataException {
		if(logger.isDebugEnabled()) logger.debug("all");
		Connection c = null;
		
		try {
			
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			
			return idiomaDAO.findAll(c);
			
		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c);
		}
	}

}
