package com.educorp.eduinteractive.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Hora;

public class HoraDAOImpl {

	public List<Hora> findAll(Connection connection) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
					"SELECT ID_HORA, HORA " 
							+"FROM HORA "
							+"ORDER BY ID_HORA ASC";

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
