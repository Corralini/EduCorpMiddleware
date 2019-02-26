package com.educorp.eduinteractive.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.NivelInglesDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.NivelIngles;

public class NivelInglesDAOImpl implements NivelInglesDAO{

	public List<NivelIngles> findAll(Connection connection) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
					"SELECT ID_NIVEL, NIVEL " 
							+"FROM NIVEL_INGLES "
							+"ORDER BY ID_NIVEL ASC";

			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = preparedStatement.executeQuery();

			List<NivelIngles> niveles = new ArrayList<NivelIngles>();                        
			NivelIngles t = null;

			while (resultSet.next()) {
				t = loadNext(resultSet);						
				niveles.add(t);
			}                	              	

			return niveles;
		}catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

	}

	private NivelIngles loadNext(ResultSet resultSet)
			throws SQLException, DataException {

		int i = 1;
		Integer idNivel = resultSet.getInt(i++);	                
		String nivel = resultSet.getString(i++);	                


		NivelIngles n = new NivelIngles();		
		n.setIdNivel(idNivel);
		n.setNivel(nivel);


		return n;
	}
	
}
