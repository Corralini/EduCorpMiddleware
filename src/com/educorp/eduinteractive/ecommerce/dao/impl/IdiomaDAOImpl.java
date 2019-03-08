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
import com.educorp.eduinteractive.ecommerce.dao.spi.IdiomaDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Idioma;

public class IdiomaDAOImpl implements IdiomaDAO{

	public Logger logger = LogManager.getLogger(IdiomaDAOImpl.class);
	
	@Override
	public List<Idioma> findAll(Connection connection) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("all");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
							"SELECT ID_IDIOMA, IDIOMA " 
							+"FROM IDIOMA ";
			if(logger.isDebugEnabled()) logger.debug(queryString);
			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = preparedStatement.executeQuery();

			List<Idioma> idiomas = new ArrayList<Idioma>();                        
			Idioma t = null;

			while (resultSet.next()) {
				t = loadNext(resultSet);						
				idiomas.add(t);
			}                	              	

			return idiomas;
		}catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

	}

	private Idioma loadNext(ResultSet resultSet)
			throws SQLException, DataException {

		int i = 1;
		String idIdioma = resultSet.getString(i++);	                
		String idioma = resultSet.getString(i++);	                


		Idioma idio = new Idioma();		
		idio.setIdIdioma(idIdioma);
		idio.setIdioma(idioma);


		return idio;
	}

}
