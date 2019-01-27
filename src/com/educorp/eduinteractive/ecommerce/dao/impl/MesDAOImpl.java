package com.educorp.eduinteractive.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Mes;

public class MesDAOImpl {

	public List<Mes> findAll(Connection connection) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
					"SELECT ID_MES, MES " 
							+"FROM MES "
							+"ORDER BY ID_MES ASC";

			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = preparedStatement.executeQuery();

			List<Mes> idiomas = new ArrayList<Mes>();                        
			Mes t = null;

			while (resultSet.next()) {
				t = loadNext(resultSet);						
				idiomas.add(t);
			}                	              	

			return idiomas;
		}catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

	}

	private Mes loadNext(ResultSet resultSet)
			throws SQLException, DataException {

		int i = 1;
		Integer idMes = resultSet.getInt(i++);	                
		String mes = resultSet.getString(i++);	                


		Mes m = new Mes();		
		m.setIdMes(idMes);
		m.setMes(mes);


		return m;
	}

}
