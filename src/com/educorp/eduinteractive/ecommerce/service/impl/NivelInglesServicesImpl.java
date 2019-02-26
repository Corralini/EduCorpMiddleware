package com.educorp.eduinteractive.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.impl.NivelInglesDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.NivelInglesDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.NivelIngles;
import com.educorp.eduinteractive.ecommerce.service.spi.NivelInglesServices;

public class NivelInglesServicesImpl implements NivelInglesServices{

	private NivelInglesDAO nivelInglesDAO = null;
	
	public NivelInglesServicesImpl() {
		nivelInglesDAO = new NivelInglesDAOImpl();
	}
	
	@Override
	public List<NivelIngles> findAll() throws DataException {
		Connection c = null;
		
		try {
			
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			
			return nivelInglesDAO.findAll(c);
			
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c);
		}
	}

}
