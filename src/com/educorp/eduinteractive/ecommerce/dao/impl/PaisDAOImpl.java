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
import com.educorp.eduinteractive.ecommerce.dao.spi.PaisDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Pais;

public class PaisDAOImpl implements PaisDAO{

	private Logger logger = LogManager.getLogger(PaisDAOImpl.class);
	
	@Override
	public List<Pais> findAll(Connection connection, String idIdioma) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("all");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			// Create "preparedStatement"       
			String queryString = 
					"select ID_PAIS, PAIS " + 
					"from idioma_pagina_pais  " +
					"WHERE ID_IDIOMA_PAGINA = ? "+
					"ORDER BY PAIS ASC ";
			if(logger.isDebugEnabled()) logger.debug(queryString);
			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;                
			preparedStatement.setString(i++, idIdioma);

			resultSet = preparedStatement.executeQuery();
			
			
			// Recupera la pagina de resultados
			List<Pais> results = new ArrayList<Pais>();                        
			Pais p = null;

			while (resultSet.next()) {
				p = loadNext (resultSet);
				results.add(p);
			}
			return results;

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	private Pais loadNext(ResultSet resultSet)
			throws SQLException, DataException {

		int i = 1;               
		String idPais = resultSet.getString(i++);	                
		String Pais = resultSet.getString(i++);

		Pais m = new Pais();		
		m.setIdPais(idPais);
		m.setNombrePais(Pais);


		return m;
	}
	
}
