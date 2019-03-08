package com.educorp.eduinteractive.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.dao.impl.HoraDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.HoraDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Hora;
import com.educorp.eduinteractive.ecommerce.service.spi.HoraServices;

public class HoraServicesImpl implements HoraServices{
	
	private Logger logger = LogManager.getLogger(HoraServicesImpl.class);
	
	private HoraDAO horaDAO = null;
	
	public HoraServicesImpl() {
		horaDAO = new HoraDAOImpl();
	}

	@Override
	public Hora findById(Integer id) throws InstanceNotFoundException, DataException {
		if(logger.isDebugEnabled()) logger.debug("id: {}", id);
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			return horaDAO.findById(c, id);

		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {   
			JDBCUtils.closeConnection(c);
		}
	}

	@Override
	public List<Hora> findAll() throws DataException {
		if(logger.isDebugEnabled()) logger.debug("all");
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			
			return horaDAO.findAll(c);
			
		}catch(SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c);
		}
	}


}
