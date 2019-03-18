package com.educorp.eduinteractive.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.dao.impl.DiaDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.DiaDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Dia;
import com.educorp.eduinteractive.ecommerce.service.spi.DiaServices;

public class DiaServicesImpl implements DiaServices{

private Logger logger = LogManager.getLogger(HoraServicesImpl.class);
	
	private DiaDAO diaDAO = null;
	
	public DiaServicesImpl() {
		diaDAO = new DiaDAOImpl();
	}

	@Override
	public Dia findById(Integer id) throws InstanceNotFoundException, DataException {
		if(logger.isDebugEnabled()) logger.debug("id: {}", id);
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			return diaDAO.findById(c, id);

		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {   
			JDBCUtils.closeConnection(c);
		}
	}

	@Override
	public List<Dia> findAll() throws DataException {
		if(logger.isDebugEnabled()) logger.debug("all");
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			
			return diaDAO.findAll(c);
			
		}catch(SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c);
		}
	}
	
}
