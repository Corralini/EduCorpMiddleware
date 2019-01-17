package com.educorp.eduinteractive.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.educorp.eduinteractive.dao.EstudianteDAO;
import com.educorp.eduinteractive.dao.service.ConnectionManager;
import com.educorp.eduinteractive.dao.service.JDBCUtils;
import com.educorp.eduinteractive.exceptions.DataException;
import com.educorp.eduinteractive.model.Estudiante;

public class EstudianteDAOImpl implements EstudianteDAO{

	public Estudiante findById(Integer id)
			throws Exception {
		
		Estudiante e = null;
				
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

			String sql;
			sql =  "SELECT ID_ESTUDIANTE, EMAIL, ID_PAIS, PSSWD, NOMBRE, APELLIDO1, APELLIDO2, ANO_NACIMIENTO, FECHA_SUBSCRIPCION, ID_NIVEL, ID_GENERO "
				  +"FROM ESTUDIANTE "
				  +"WHERE ID_ESTUDIANTE = ? ";
			
			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);
			
			
			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set						
			if (resultSet.next()) {				
				e = loadNext(resultSet);				
			} else {
				throw new Exception("Non se encontrou o estudiante "+id);
			}
			if (resultSet.next()) {
				throw new Exception("Estudainte"+id+" duplicado");
			}
						
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}  	
			
		return e;
	}
	
	public List<Estudiante> findByNombre(String criterioNombre, String ap1)
			throws Exception {	
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			
			connection = ConnectionManager.getConnection();

			String sql;
			sql =    " SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, HIRE_DATE, MANAGER_ID " 
					+" FROM EMPLOYEES "
					+" WHERE "
					+"	UPPER(FIRST_NAME) LIKE ? " 
					+"  AND"
					+"  UPPER(LAST_NAME) LIKE  ? ";
			
			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			// Establece os parámetros
			int i = 1;
			preparedStatement.setString(i++, "%" + criterioNombre.toUpperCase() + "%");
			preparedStatement.setString(i++, "%" + ap1.toUpperCase() + "%");			
			
			resultSet = preparedStatement.executeQuery();			

			//STEP 5: Extract data from result set			
			List<Estudiante> empleados = new ArrayList<Estudiante>();
			Estudiante e = null;
			while (resultSet.next()) {
				e = loadNext(resultSet);						
				empleados.add(e);
			} 
			return empleados;
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}  	
	}
	
	public Estudiante create(Estudiante e)
			throws Exception {
		Connection connection = null; 
		PreparedStatement preparedStatement = null;
		try {          

			connection = ConnectionManager.getConnection();
			//Check if the primary key already exists
//			if (exists(connection, e.getId())) {
//				throw new Exception("Duplicate employee "+e.getId());
//			}
			
			
			String queryString = "INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, EMAIL, HIRE_DATE, MANAGER_ID) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(queryString);
			
			int i = 1;
			preparedStatement.setString(i++, e.getEmail());
			preparedStatement.setString(i++, e.getNombre());
			preparedStatement.setString(i++, e.getApellido1());
			preparedStatement.setString(i++, e.getApellido2());
			preparedStatement.setDate(i++, (java.sql.Date) e.getAnoNacimiento());
			preparedStatement.setDate(i++, (java.sql.Date) e.getFechaSubscripcion());
			preparedStatement.setInt(i++, e.getIdNivel());
			preparedStatement.setString(i++, e.getIdPais());
			
			
			int insertedRows = preparedStatement.executeUpdate();
			
			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Employees'");
			}
			
			//...
			return e;					

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}
		
	}
	
	private Estudiante loadNext(ResultSet resultSet) throws Exception {
		
		Estudiante e = new Estudiante();
		int i = 1;
		Integer id = resultSet.getInt(i++);
		String email = resultSet.getString(i++);
		String nombre = resultSet.getString(i++);
		String apellido1 = resultSet.getString(i++);	
		String apellido2 = resultSet.getString(i++);
		Date anoNacimiento = resultSet.getDate(i++);
		Date fechaSubscripcion = resultSet.getDate(i++);
		Integer idNivel = resultSet.getInt(i++);
		String idPais = resultSet.getString(i++);
		
		
		e = new Estudiante();
		e.setIdEstudiante(id);
		e.setEmail(email);
		e.setNombre(nombre);
		e.setApellido1(apellido1);
		e.setApellido2(apellido2);
		e.setAnoNacimiento(anoNacimiento);
		e.setFechaSubscripcion(fechaSubscripcion);
		e.setIdNivel(idNivel);
		e.setIdPais(idPais);
		
		return e;
		
	}
}
