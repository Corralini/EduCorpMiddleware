package com.educorp.eduinteractive.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.HoraDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Hora;

public class HoraDAOImpl implements HoraDAO{
	
	private Logger logger = LogManager.getLogger(HoraDAOImpl.class);
	
	@Override
	public Hora findById(Connection connection, Integer id) throws InstanceNotFoundException, DataException {
		if(logger.isDebugEnabled()) logger.debug("id: {}", id);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
									"SELECT ID_HORA, HORA " 
									+"FROM HORA "
									+"where ID_HORA = ?";

			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			
			resultSet = preparedStatement.executeQuery();
                       
			Hora t = null;

			if (resultSet.next()) {				
				t = loadNext(resultSet);				
			} else {
				if(logger.isDebugEnabled()) logger.debug("Non se encontrou a hora {}", id);
			}
			if (resultSet.next()) {
				if(logger.isDebugEnabled()) logger.debug("Hora {} duplicado", id);
			}                	              	

			return t;
		}catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	public List<Hora> findAll(Connection connection) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("all");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
					"SELECT ID_HORA, HORA " 
							+"FROM HORA "
							+"ORDER BY ID_HORA ASC";
			if(logger.isDebugEnabled()) logger.debug(queryString);
			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = preparedStatement.executeQuery();

			List<Hora> dias = new ArrayList<Hora>();                        
			Hora t = null;

			while (resultSet.next()) {
				t = loadNext(resultSet);						
				dias.add(t);
			}                	              	

			return dias;
		}catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

	}

	private Hora loadNext(ResultSet resultSet)
			throws SQLException, DataException {

		int i = 1;
		Integer idHora = resultSet.getInt(i++);	                
		String hora = resultSet.getString(i++);	                


		Hora h = new Hora();		
		h.setIdHora(idHora);
		h.setHora(hora);


		return h;
	}

}
