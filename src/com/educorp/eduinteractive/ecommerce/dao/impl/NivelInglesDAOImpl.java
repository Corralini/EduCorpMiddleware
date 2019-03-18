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
import com.educorp.eduinteractive.ecommerce.dao.spi.NivelInglesDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Dia;
import com.educorp.eduinteractive.ecommerce.model.NivelIngles;

public class NivelInglesDAOImpl implements NivelInglesDAO{

	private Logger logger = LogManager.getLogger(NivelInglesDAOImpl.class);
	
	@Override
	public List<NivelIngles> findAll(Connection connection) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("all");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
					"SELECT ID_NIVEL, NIVEL " 
							+"FROM NIVEL_INGLES "
							+"ORDER BY ID_NIVEL ASC";
			if(logger.isDebugEnabled()) logger.debug(queryString);
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
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

	}
	
	@Override
	public NivelIngles findById(Connection connection, Integer id) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("all");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
					"SELECT ID_NIVEL, NIVEL " 
							+" FROM NIVEL_INGLES " 
							+" WHERE ID_NIVEL = ? "
							+" ORDER BY ID_NIVEL ASC";
			if(logger.isDebugEnabled()) logger.debug(queryString);
			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			
			resultSet = preparedStatement.executeQuery();

			NivelIngles t = null;

			if (resultSet.next()) {				
				t = loadNext(resultSet);				
			} else {
				if(logger.isDebugEnabled()) logger.debug("Non se encontrou o dia {}", id);
			}
			if (resultSet.next()) {
				if(logger.isDebugEnabled()) logger.debug("Dia {} duplicado", id);
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
