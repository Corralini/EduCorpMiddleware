package com.educorp.eduinteractive.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.EstadoDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Estado;

public class EstadoDAOImpl implements EstadoDAO{

	private Logger logger = LogManager.getLogger(EstadoDAOImpl.class);
	
	@Override
	public Estado findById(Connection connection, String idEstado) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("id: {}", idEstado);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {          
			String queryString = 
							"SELECT ID_ESTADO, ESTADO " 
							+"FROM ESTADO " 
							+"WHERE ID_ESTADO = ? ; ";
			if(logger.isDebugEnabled()) logger.debug(queryString);
			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;                
			preparedStatement.setString(i++, idEstado);

			resultSet = preparedStatement.executeQuery();

			Estado e = null;

			if (resultSet.next()) {
				e = loadNext(resultSet);				
			} else {
				if(logger.isDebugEnabled()) logger.debug("Estado with id {} not found", idEstado);
			}

			return e;

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	private Estado loadNext(ResultSet resultSet)
			throws SQLException, DataException {

				int i = 1;
				String idEstado = resultSet.getString(i++);	                
				String estado = resultSet.getString(i++);	                

		
				Estado e = new Estado();		
				e.setIdEstado(idEstado);;
				e.setEstado(estado);

				
				return e;
			}
	
}
