package com.educorp.eduinteractive.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.impl.IdiomaDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.IdiomaDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Idioma;
import com.educorp.eduinteractive.ecommerce.service.spi.IdiomaServices;

public class IdiomaServicesImpl implements IdiomaServices{

	private IdiomaDAO idiomaDAO= null;
	
	public IdiomaServicesImpl() {
		idiomaDAO = new IdiomaDAOImpl();
	}
			
	
	@Override
	public List<Idioma> findAll() throws DataException {
		
		Connection c = null;
		
		try {
			
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			
			return idiomaDAO.findAll(c);
			
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c);
		}
	}

}
