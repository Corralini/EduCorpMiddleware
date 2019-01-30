package com.educorp.eduinteractive.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.DiaDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Dia;

public class DiaDAOImpl implements DiaDAO{

	@Override
	public Dia findById(Connection connection, Integer id) throws InstanceNotFoundException, DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
									"SELECT ID_DIA, DIA " 
									+"FROM DIA "
									+"where id_dia = ?";

			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			
			resultSet = preparedStatement.executeQuery();
                       
			Dia t = null;

			if (resultSet.next()) {				
				t = loadNext(resultSet);				
			} else {
				throw new DataException("Non se encontrou o dia "+id);
			}
			if (resultSet.next()) {
				throw new DataException("Dia"+id+" duplicado");
			}                	              	

			return t;
		}catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}
	
	public List<Dia> findAll(Connection connection) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
									"SELECT ID_DIA, DIA " 
									+"FROM DIA "
									+"ORDER BY ID_DIA ASC";

			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = preparedStatement.executeQuery();

			List<Dia> dias = new ArrayList<Dia>();                        
			Dia t = null;

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

	private Dia loadNext(ResultSet resultSet)
			throws SQLException, DataException {

				int i = 1;
				Integer idDia = resultSet.getInt(i++);	                
				String dia = resultSet.getString(i++);	                

		
				Dia d = new Dia();		
				d.setIdDia(idDia);
				d.setDia(dia);

				
				return d;
			}

	
}


