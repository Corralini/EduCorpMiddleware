package com.educorp.eduinteractive.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.dao.service.DAOUtils;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.service.Results;
import com.educorp.eduinteractive.ecommerce.dao.spi.HorarioDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Horario;

public class HorarioDAOImpl implements HorarioDAO{

	private Logger logger = LogManager.getLogger(HoraDAOImpl.class);

	@Override
	public Horario findById(Connection connection, Integer id) 
			throws InstanceNotFoundException, DataException {
		if(logger.isDebugEnabled()) logger.debug("id: {}", id);
		Horario h = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_HORARIO, ID_PROFESOR, ID_DIA, ID_HORA "
					+"FROM HORARIO "
					+"WHERE ID_HORARIO = ? ";
			if(logger.isDebugEnabled()) logger.debug(sql);
			// Preparar a query

			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set						
			if (resultSet.next()) {				
				h = loadNext(resultSet);				
			} else {
				if(logger.isDebugEnabled()) logger.debug("Non se encontrou o horario {}", id);
			}
			if (resultSet.next()) {
				if(logger.isDebugEnabled()) logger.debug("horario {} duplicado", id);
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		return h;
	}


	@Override
	public Results<Horario> findBy(Connection connection, Integer idProfesor, Integer idDia, Date fecha, 
			int startIndex, int count) 
					throws DataException {
		if(logger.isDebugEnabled()) logger.debug("Busqueda= idProfesor: {}; idDia: {}; fecha: {}", idProfesor, idDia, fecha); 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		
		try {

			queryString = new StringBuilder(
					"SELECT ID_HORARIO, ID_PROFESOR, ID_DIA, ID_HORA "
							+" FROM HORARIO "
							+ " where id_horario not in (SELECT ID_HORARIO "
							+ " FROM sesion " 
							+ " where fecha_sesion = ? and id_profesor = ? and (id_estado = 'S' || id_estado = 'A')) "
							+ " and id_profesor = ? and id_dia = ? " );
			if(logger.isDebugEnabled()) logger.debug(queryString);
			preparedStatement = connection.prepareStatement(queryString.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;  

			preparedStatement.setDate(i++, new java.sql.Date(fecha.getTime()));
			preparedStatement.setInt(i++, idProfesor);
			preparedStatement.setInt(i++, idProfesor);
			preparedStatement.setInt(i++, idDia);



			resultSet = preparedStatement.executeQuery();

			List<Horario> horarios = new ArrayList<Horario>();                        
			Horario h = null;
			int currentCount = 0;

			if((startIndex >= 1) && resultSet.absolute(startIndex)) {
				do {
					h = loadNext(resultSet);						
					horarios.add(h);
					currentCount++;
				}while((currentCount < count) && resultSet.next());
			}

			int totalRows = JDBCUtils.getTotalRows(resultSet);
			
			if(logger.isDebugEnabled()) logger.debug("Total rows: " + totalRows);
			
			Results<Horario> results = new Results<Horario>(horarios, startIndex, totalRows);

			return results;

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public void update(Connection connection, Horario h) 
			throws InstanceNotFoundException, DataException {
		if(logger.isDebugEnabled()) logger.debug("Horario= idHorario: {}; idProfesor: {}; idDia: {}; idHora: {}",
				h.getIdHorario(), h.getIdProfesor(), h.getIdDia(), h.getIdHora());
		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;
		try {	

			queryString = new StringBuilder(
					" UPDATE HORARIO" 
					);

			boolean first = true;

			if (h.getIdHorario() != null) {
				DAOUtils.addUpdate(queryString, first, " ID_HORARIO =  ? ");
				first = false;
			}

			if (h.getIdProfesor() != null) {
				DAOUtils.addUpdate(queryString, first, " ID_PROFESOR =  ? ");
				first = false;
			}


			if (h.getIdDia() != null) {
				DAOUtils.addUpdate(queryString, first, " ID_DIA =  ? ");
				first = false;
			}

			if (h.getIdHora() != null) {
				DAOUtils.addUpdate(queryString, first, " ID_HORA =  ? ");
				first = false;
			}


			queryString.append("WHERE ID_PROFESOR = ?");
			if(logger.isDebugEnabled()) logger.debug(queryString);
			preparedStatement = connection.prepareStatement(queryString.toString());


			int i = 1;
			if (h.getIdHorario() != null)
				preparedStatement.setInt(i++, h.getIdHorario());
			if (h.getIdProfesor() != null)
				preparedStatement.setInt(i++, h.getIdProfesor());
			if (h.getIdDia() != null)
				preparedStatement.setInt(i++, h.getIdDia());
			if (h.getIdHora() != null)
				preparedStatement.setInt(i++, h.getIdHora());


			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				if(logger.isDebugEnabled()) logger.debug("Non se actualizou o horario {}", h.getIdHorario());
			}

			if (updatedRows > 1) {
				if(logger.isDebugEnabled()) logger.debug("Duplicate row from id_horario = {}", h.getIdHorario());
			}     

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);    
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public Horario create(Connection connection, Horario h) 
			throws DuplicateInstanceException, DataException {
		if(logger.isDebugEnabled()) logger.debug("Horario= idHorario: {}; idProfesor: {}; idDia: {}; idHora: {}",
				h.getIdHorario(), h.getIdProfesor(), h.getIdDia(), h.getIdHora());
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			// Creamos el preparedstatement
			String queryString = "INSERT INTO HORARIO (ID_PROFESOR, ID_DIA, ID_HORA) "
					+ "VALUES (?, ?, ?)";
			if(logger.isDebugEnabled()) logger.debug(queryString);
			preparedStatement = connection.prepareStatement(queryString,
					Statement.RETURN_GENERATED_KEYS);

			// Rellenamos el "preparedStatement"
			int i = 1;    
			preparedStatement.setInt(i++, h.getIdProfesor());
			preparedStatement.setInt(i++, h.getIdDia());
			preparedStatement.setInt(i++, h.getIdHora());

			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				if(logger.isDebugEnabled()) logger.debug("Can not add row to table 'Horario'");
			}

			// Recuperamos la PK generada
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Integer pk = resultSet.getInt(1); 
				h.setIdHorario(pk);
			} else {
				if(logger.isDebugEnabled()) logger.debug("Unable to fetch autogenerated primary key");
			}

			// Return the DTO
			return h;

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public Horario delete(Connection connection, Horario h) throws DataException {

		return null;
	}

	private Horario loadNext(ResultSet resultSet) throws SQLException, DataException {

		Horario h = new Horario();
		int i = 1;
		Integer idHorario = resultSet.getInt(i++);
		Integer idProfesor = resultSet.getInt(i++);
		Integer idDia = resultSet.getInt(i++);
		Integer idHora = resultSet.getInt(i++);



		h = new Horario();
		h.setIdHorario(idHorario);
		h.setIdProfesor(idProfesor);
		h.setIdDia(idDia);
		h.setIdHora(idHora);


		return h;

	}
}
