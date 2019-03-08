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
import com.educorp.eduinteractive.ecommerce.dao.spi.DiaDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Dia;

public class DiaDAOImpl implements DiaDAO{

	private Logger logger = LogManager.getLogger(DiaDAOImpl.class);
	
	@Override
	public Dia findById(Connection connection, Integer id) throws InstanceNotFoundException, DataException {
		if(logger.isDebugEnabled()) logger.debug("id: {}", id);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
									"SELECT ID_DIA, DIA " 
									+"FROM DIA "
									+"where id_dia = ?";
			if(logger.isDebugEnabled()) logger.debug(queryString);
			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			
			resultSet = preparedStatement.executeQuery();
                       
			Dia t = null;

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
	
	public List<Dia> findAll(Connection connection) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("all");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
									"SELECT ID_DIA, DIA " 
									+"FROM DIA "
									+"ORDER BY ID_DIA ASC";
			if(logger.isDebugEnabled()) logger.debug(queryString);
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
			logger.warn(e.getMessage(), e);
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


