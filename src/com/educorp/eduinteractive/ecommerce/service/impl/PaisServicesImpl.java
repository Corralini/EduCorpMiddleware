package com.educorp.eduinteractive.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.dao.impl.PaisDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.PaisDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Pais;
import com.educorp.eduinteractive.ecommerce.service.spi.PaisServices;

public class PaisServicesImpl implements PaisServices{

	private Logger logger = LogManager.getLogger(PaisServicesImpl.class);
	
	private PaisDAO paisDAO = null;

	public PaisServicesImpl() {
		paisDAO = new PaisDAOImpl();
	}

	public List<Pais> findByIdioma (String idIdioma) 
			throws DataException{
		if(logger.isDebugEnabled()) logger.debug("idIdioma: {}", idIdioma);
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			return paisDAO.findAll(c, idIdioma);

		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {   
			JDBCUtils.closeConnection(c);
		} 
	}

}
