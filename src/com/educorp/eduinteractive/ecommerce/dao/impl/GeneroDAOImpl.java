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
import com.educorp.eduinteractive.ecommerce.dao.spi.GeneroDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Genero;
import com.educorp.eduinteractive.ecommerce.model.Hora;

public class GeneroDAOImpl implements GeneroDAO{

	private Logger logger = LogManager.getLogger(GeneroDAOImpl.class);
	
	@Override
	public List<Genero> findAll(Connection connection) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("all");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
							"SELECT ID_GENERO, GENERO " 
							+"FROM GENERO "
							+"ORDER BY ID_GENERO ASC";
			if(logger.isDebugEnabled()) logger.debug(queryString);
			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = preparedStatement.executeQuery();

			List<Genero> genero = new ArrayList<Genero>();                        
			Genero g = null;

			while (resultSet.next()) {
				g = loadNext(resultSet);						
				genero.add(g);
			}                	              	

			return genero;
		}catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}
	
	@Override
	public Genero findById(Connection connection, String id) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("all");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
							" SELECT ID_GENERO, GENERO " 
							+" FROM GENERO "
							+" WHERE ID_GENERO LIKE ? "
							+" ORDER BY ID_GENERO ASC";
			if(logger.isDebugEnabled()) logger.debug(queryString);
			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setString(i++, id);
			
			resultSet = preparedStatement.executeQuery();
                        
			Genero t = null;

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

	private Genero loadNext(ResultSet resultSet)
			throws SQLException, DataException {

				int i = 1;
				String idGenero = resultSet.getString(i++);	                
				String genero = resultSet.getString(i++);	                

		
				Genero g = new Genero();		
				g.setIdGenero(idGenero);
				g.setGenero(genero);

				
				return g;
			}
	
}
