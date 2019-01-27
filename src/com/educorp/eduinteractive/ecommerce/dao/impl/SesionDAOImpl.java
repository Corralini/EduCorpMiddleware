package com.educorp.eduinteractive.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.SesionDAO;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Sesion;

public class SesionDAOImpl implements SesionDAO{

	@Override
	public Sesion findById(Connection connection, Integer id) throws DataException {
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
	public List<Sesion> findByCalendario(Connection connection, Integer idUsuario, Integer mes, Integer ano) throws DataException {
		
		return null;
	}

	@Override
	public List<Sesion> findByEstado(Connection connection, Integer idEstado) throws DataException {
		
		return null;
	}

	@Override
	public Sesion create(Connection connection, Sesion s) throws DataException {
		
		return null;
	}

	@Override
	public Sesion update(Connection connection, Sesion s) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	private Sesion loadNext(ResultSet resultSet) throws SQLException, DataException {

		Sesion s = new Sesion();
		int i = 1;
		Integer idSesion = resultSet.getInt(i++);
		Integer idProfesor = resultSet.getInt(i++);
		Integer idEstudiante = resultSet.getInt(i++);
		Integer idMes = resultSet.getInt(i++);
		Integer idHorario = resultSet.getInt(i++);
		Date fechaInicio = resultSet.getDate(i++);
		Date fechaFIn = resultSet.getDate(i++);
		Double precio = resultSet.getDouble(i++);
		Integer ano = resultSet.getInt(i++);
		String idEstado = resultSet.getString(i++);
		Date fechaCambioEstado = resultSet.getDate(i++);
		
		s = new Sesion();
		s.setIdSesion(idSesion);
		s.setIdProfesor(idProfesor);
		s.setIdEstudiante(idEstudiante);
		s.setIdMes(idMes);
		s.setIdHorario(idHorario);
		s.setFechaInicio(fechaInicio);
		s.setFechaFIn(fechaFIn);
		s.setPrecio(precio);
		s.setAno(ano);
		s.setIdEstado(idEstado);
		s.setFechaCambioEstado(fechaCambioEstado);

		return s;

	}
	
}
