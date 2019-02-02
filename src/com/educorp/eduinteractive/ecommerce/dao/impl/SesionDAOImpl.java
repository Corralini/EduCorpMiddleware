package com.educorp.eduinteractive.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.service.DAOUtils;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.SesionDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Sesion;

public class SesionDAOImpl implements SesionDAO{

	@Override
	public Sesion findById(Connection connection, Integer id) throws InstanceNotFoundException, DataException {
		Sesion s = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_SESION, ID_PROFESOR, ID_ESTUDIANTE, ID_MES,ID_HORARIO, FECHA_INICIO, FECHA_FIN, PRECIO, ANO, ID_ESTADO, FECHA_CAMBIO_ESTADO "
					+"FROM SESION "
					+"WHERE ID_SESION = ? ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set						
			if (resultSet.next()) {				
				s = loadNext(resultSet);				
			} else {
				throw new DataException("Non se encontrou a sesion "+id);
			}
			if (resultSet.next()) {
				throw new DataException("Sesion"+id+" duplicado");
			}

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		return s;
	}

	@Override
	public List<Sesion> findByCalendario(Connection connection, Integer idEstudiante) throws DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
					"SELECT S.ID_SESION, S.ID_PROFESOR, S.ID_ESTUDIANTE, S.FECHA_SESION,S.ID_HORARIO, S.FECHA_INICIO, S.FECHA_FIN, S.PRECIO, S.ID_ESTADO, S.FECHA_CAMBIO_ESTADO " + 
					"FROM SESION S INNER JOIN HORARIO H ON (S.ID_HORARIO = H.ID_HORARIO)  " +
					"WHERE S.ID_ESTUDIANTE = ? AND (S.ID_ESTADO = 'A' OR S.ID_ESTADO = 'S')  " +
					"ORDER BY FECHA_SESION ASC";

			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setInt(i++, idEstudiante);
			
			resultSet = preparedStatement.executeQuery();

			List<Sesion> sesiones = new ArrayList<Sesion>();                        
			Sesion s = null;

			while (resultSet.next()) {
				s = loadNext(resultSet);						
				sesiones.add(s);
			} 

			return sesiones;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
		
	}

	@Override
	public List<Sesion> findByEstado(Connection connection, String idEstado) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
			
		try {
			
			String queryString = 
					"SELECT ID_SESION, ID_PROFESOR, ID_ESTUDIANTE, FECHA_SESION, S.ID_HORARIO, FECHA_INICIO, FECHA_FIN, PRECIO, ID_ESTADO, FECHA_CAMBIO_ESTADO " + 
					"FROM SESION   " +
					"WHERE UPPER(ID_ESTADO) = LIKE UPPER(?) ";

			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setString(i++, "%" + idEstado + "%");
			
			resultSet = preparedStatement.executeQuery();

			List<Sesion> sesiones = new ArrayList<Sesion>();                        
			Sesion s = null;

			while (resultSet.next()) {
				s = loadNext(resultSet);						
				sesiones.add(s);
			} 

			return sesiones;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public Sesion create(Connection connection, Sesion s) 
			throws DuplicateInstanceException, DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			// Creamos el preparedstatement
			String queryString = "INSERT INTO SESION (ID_PROFESOR, ID_ESTUDIANTE,FECHA_SESION, ID_HORARIO, FECHA_INICIO, FECHA_FIN, PRECIO, ID_ESTADO, FECHA_CAMBIO_ESTADO) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(queryString,
									Statement.RETURN_GENERATED_KEYS);

			// Rellenamos el "preparedStatement"
			int i = 1;    
			preparedStatement.setInt(i++, s.getIdProfesor());
			preparedStatement.setInt(i++, s.getIdEstudiante());
			preparedStatement.setDate(i++, new java.sql.Date(s.getFechaSesion().getTime()));
			preparedStatement.setInt(i++, s.getIdHorario());
			preparedStatement.setDate(i++, null);
			preparedStatement.setDate(i++, null);
			preparedStatement.setDouble(i++, s.getPrecio());
			preparedStatement.setString(i++, s.getIdEstado());
			preparedStatement.setDate(i++, new java.sql.Date(s.getFechaCambioEstado().getTime()));
			
			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Estudiante'");
			}

			// Recuperamos la PK generada
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Integer pk = resultSet.getInt(1); 
				s.setIdEstudiante(pk);
			} else {
				throw new DataException("Unable to fetch autogenerated primary key");
			}

			// Return the DTO
			return s;

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public void update(Connection connection, Sesion s) 
			throws InstanceNotFoundException, DataException {
		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;
		try {	
			
			queryString = new StringBuilder(
					" UPDATE Sesion" 
					);
			
			boolean first = true;
			
			if (s.getIdProfesor() != null) {
				DAOUtils.addUpdate(queryString, first, " id_profesor =  ? ");
				first = false;
			}
			
			if (s.getIdEstudiante() != null) {
				DAOUtils.addUpdate(queryString, first, " id_estudiante =  ? ");
				first = false;
			}	

			if (s.getFechaSesion() != null) {
				DAOUtils.addUpdate(queryString, first, " fecha_sesion = ? ");
				first = false;
			}
			
			if (s.getIdHorario() != null) {
				DAOUtils.addUpdate(queryString, first, "id_horario = ?");
				first = false;
			}
			
			if (s.getFechaInicio() != null) {
				DAOUtils.addUpdate(queryString, first, " fecha_inicio = ? ");
				first = false;
			}
			
			if (s.getFechaFin() != null) {
				DAOUtils.addUpdate(queryString, first, " fecha_fin = ? ");
				first = false;
			}
			
			if (s.getPrecio() != null) {
				DAOUtils.addUpdate(queryString, first, " precio = ? ");
				first = false;
			}
			
			if (s.getIdEstado() != null) {
				DAOUtils.addUpdate(queryString, first, "id_estado = ?");
				first = false;
			}
			
			if (s.getFechaCambioEstado() != null) {
				DAOUtils.addUpdate(queryString, first, "fecha_CAMBIO_ESTADO = ?");
				first = false;			
			}
						
			queryString.append("WHERE id_sESION = ?");
			
			preparedStatement = connection.prepareStatement(queryString.toString());
			

			int i = 1;
			if (s.getIdProfesor() != null)
				preparedStatement.setInt(i++, s.getIdProfesor());
			if (s.getIdEstudiante() != null)
				preparedStatement.setInt(i++, s.getIdEstudiante());
			if (s.getFechaSesion()!=null) 
				preparedStatement.setDate(i++,(java.sql.Date) s.getFechaSesion());
			if (s.getIdHorario() != null)
				preparedStatement.setInt(i++, s.getIdHorario());
			if (s.getFechaInicio()!=null) 
				preparedStatement.setDate(i++, new java.sql.Date (s.getFechaInicio().getTime()));
			if (s.getFechaFin()!=null) 
				preparedStatement.setDate(i++, new java.sql.Date (s.getFechaFin().getTime()));
			if (s.getPrecio()!=null) 
				preparedStatement.setDouble(i++,s.getPrecio());
			if (s.getIdEstado() != null)
				preparedStatement.setString(i++, s.getIdEstado().toUpperCase());
			if (s.getFechaCambioEstado() != null)
				preparedStatement.setDate(i++, new java.sql.Date (s.getFechaCambioEstado().getTime()));
				
			preparedStatement.setInt(i++, s.getIdSesion());
			
			int updatedRows = preparedStatement.executeUpdate();
			
				

			if (updatedRows == 0) {
				throw new DataException("Non se actualizou o estudiante");
			}

			if (updatedRows > 1) {
				throw new SQLException("Duplicate row for id = '" + 
						s.getIdEstudiante() + "' in table 'E'");
			}     
			
		} catch (SQLException ex) {
			throw new DataException(ex);    
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	private Sesion loadNext(ResultSet resultSet) throws SQLException, DataException {

		Sesion s = new Sesion();
		int i = 1;
		Integer idSesion = resultSet.getInt(i++);
		Integer idProfesor = resultSet.getInt(i++);
		Integer idEstudiante = resultSet.getInt(i++);
		Date fechaSesion = resultSet.getDate(i++);
		Integer idHorario = resultSet.getInt(i++);
		Date fechaInicio = resultSet.getDate(i++);
		Date fechaFIn = resultSet.getDate(i++);
		Double precio = resultSet.getDouble(i++);
		String idEstado = resultSet.getString(i++);
		Date fechaCambioEstado = resultSet.getDate(i++);
		
		s = new Sesion();
		s.setIdSesion(idSesion);
		s.setIdProfesor(idProfesor);
		s.setIdEstudiante(idEstudiante);
		s.setFechaSesion(fechaSesion);
		s.setIdHorario(idHorario);
		s.setFechaInicio(fechaInicio);
		s.setFechaFin(fechaFIn);
		s.setPrecio(precio);
		s.setIdEstado(idEstado);
		s.setFechaCambioEstado(fechaCambioEstado);

		return s;

	}
	
}
