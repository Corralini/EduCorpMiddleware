package com.educorp.eduinteractive.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.EstadoDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Estado;

public class EstadoDAOImpl implements EstadoDAO{

	@Override
	public Estado findId(Connection connection, String idEstado) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {          
			String queryString = 
							"SELECT ID_ESTADO, ESTADO " 
							+"FROM ESTADO " 
							+"WHERE ID_ESTADO = ? ; ";
			
			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;                
			preparedStatement.setString(i++, idEstado);

			resultSet = preparedStatement.executeQuery();

			Estado e = null;

			if (resultSet.next()) {
				e = loadNext(resultSet);				
			} else {
				throw new DataException("Estado with id " + idEstado + 
						"not found");
			}

			return e;

		} catch (SQLException e) {
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
