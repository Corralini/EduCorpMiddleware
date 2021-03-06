package com.educorp.eduinteractive.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.PuntuacionDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Puntuacion;

public class PuntuacionDAOImpl implements PuntuacionDAO {

	private Logger logger = LogManager.getLogger(PuntuacionDAOImpl.class);
	
	@Override
	public Puntuacion findEstudiantePuntuacion(Connection connection, Integer id) 
			throws InstanceNotFoundException, DataException {
		if(logger.isDebugEnabled()) logger.debug("id: {}", id);
		Puntuacion p = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT id_profesor, id_estudiante, fecha_puntuacion, SUM(puntuacion)/count(puntuacion) "
					+"FROM profesor_puntua_estudiante "
					+"WHERE id_estudiante = ? ";
			if(logger.isDebugEnabled()) logger.debug(sql);
			// Preparar a query
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set						
			if (resultSet.next()) {				
				p = loadNextPuntuacionEstudiante(resultSet);				
			} else {
				if(logger.isDebugEnabled()) logger.debug("Non se encontrou puntuacion para o estudiante {}", id);
			}
			if (resultSet.next()) {
				if(logger.isDebugEnabled()) logger.debug("Estudiante {} duplicado", id);
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}  	

		return p;
	}

	@Override
	public Puntuacion findProfesorPuntuacion(Connection connection, Integer id) 
			throws InstanceNotFoundException, DataException {
		if(logger.isDebugEnabled()) logger.debug("Id: {}", id);
		Puntuacion p = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT id_profesor, id_estudiante, fecha_puntuacion, SUM(puntuacion)/count(puntuacion) "
					+"FROM profesor_puntua_estudiante "
					+"WHERE id_estudiante = ? ";
			if(logger.isDebugEnabled()) logger.debug(sql);
			// Preparar a query
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set						
			if (resultSet.next()) {				
				p = loadNextPuntuacionProfesor(resultSet);				
			} else {
				if(logger.isDebugEnabled()) logger.debug("Non se encontrou puntuacion para o profesor {}", id);
			}
			if (resultSet.next()) {
				if(logger.isDebugEnabled()) logger.debug("Estudiante {} duplicado", id);
			}

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}  	

		return p;
	}

	@Override
	public Puntuacion createPuntuacionProfesor(Connection connection, Puntuacion p) 
			throws DuplicateInstanceException, DataException {
		if(logger.isDebugEnabled()) logger.debug("Puntuacion: {}", p);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			// Creamos el preparedstatement
			String queryString = "INSERT INTO ESTUDIANTE_PUNTUA_PROFESOR (ID_ESTUDIANTE, ID_PROFESOR, FECHA_PUNTUACION, PUNTUACION) "
					+ "VALUES (?, ?, ?, ?)";
			if(logger.isDebugEnabled()) logger.debug(queryString);
			preparedStatement = connection.prepareStatement(queryString,
									Statement.RETURN_GENERATED_KEYS);

			// Rellenamos el "preparedStatement"
			int i = 1;    
				
				preparedStatement.setInt(i++, p.getIdEstudiante());
				preparedStatement.setInt(i++, p.getIdProfesor());
				preparedStatement.setDate(i++, new java.sql.Date (p.getFechaPuntuacion().getTime()));
				preparedStatement.setDouble(i++, p.getPuntuacion());
				
			
			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				if(logger.isDebugEnabled()) logger.debug("Can not add row to table 'Puntuacion'");
			}

			// Return the DTO
			return p;

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public Puntuacion createPuntuacionEstudiante(Connection connection, Puntuacion p) 
			throws DuplicateInstanceException, DataException {
		if(logger.isDebugEnabled()) logger.debug("Puntuacion: {}", p);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			// Creamos el preparedstatement
			String queryString = "INSERT INTO PROFESOR_PUNTUA_ESTUDIANTE (ID_ESTUDIANTE, ID_PROFESOR, FECHA_PUNTUACION, PUNTUACION) "
					+ "VALUES (?, ?, ?, ?)";
			if(logger.isDebugEnabled()) logger.debug(queryString);
			preparedStatement = connection.prepareStatement(queryString,
									Statement.RETURN_GENERATED_KEYS);

			// Rellenamos el "preparedStatement"
			int i = 1;    
				
				preparedStatement.setInt(i++, p.getIdEstudiante());
				preparedStatement.setInt(i++, p.getIdProfesor());
				preparedStatement.setDate(i++, new java.sql.Date (p.getFechaPuntuacion().getTime()));
				preparedStatement.setDouble(i++, p.getPuntuacion());
				
			
			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				if(logger.isDebugEnabled()) logger.debug("Can not add row to table 'Puntuacion'");
			}

			// Return the DTO
			return p;

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	private Puntuacion loadNextPuntuacionEstudiante(ResultSet resultSet) throws SQLException, DataException {

		Puntuacion p = new Puntuacion();
		int i = 1;
		Integer idEstudiante = resultSet.getInt(i++);
		Double puntuacion = resultSet.getDouble(i++);


		p = new Puntuacion();
		p.setIdEstudiante(idEstudiante);
		p.setPuntuacion(puntuacion);


		return p;

	}
	
	private Puntuacion loadNextPuntuacionProfesor(ResultSet resultSet) throws SQLException, DataException {

		Puntuacion p = new Puntuacion();
		int i = 1;
		Integer idProfesor = resultSet.getInt(i++);
		Double puntuacion = resultSet.getDouble(i++);


		p = new Puntuacion();
		p.setIdEstudiante(idProfesor);
		p.setPuntuacion(puntuacion);


		return p;

	}
	
}
