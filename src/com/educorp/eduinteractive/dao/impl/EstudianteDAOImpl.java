package com.educorp.eduinteractive.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.educorp.eduinteractive.dao.service.ConnectionManager;
import com.educorp.eduinteractive.dao.service.JDBCUtils;
import com.educorp.eduinteractive.exceptions.DataException;
import com.educorp.eduinteractive.model.Estudiante;

public class EstudianteDAOImpl {

	public Estudiante findById (Integer id) 
			throws Exception{

		Estudiante e = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

			String sql;
			sql = "SELECT ID_ESTUDIANTE,NOMBRE, APELLIDO1 "
					+ "FROM ESTUDIANTE "
					+ "WHERE ID_ESTUDIANTE = ? ";

			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				e = loadNext(resultSet);
			}else {
				throw new Exception("Non se atopou o estudiante " + id);
			}
			if (resultSet.next()) {
				throw new Exception ("Empleado " + id + "duplicado");
			}

		}catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}

		return e;

	}


	public Estudiante[] findByNombre (String Nombre) 
			throws Exception{
			
		return null;
	}


	public List<Estudiante> findByPais (int pais) 
			throws Exception{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();
			
			String sql;
			sql = "SELECT ID_ESTUDIANTE, NOMBRE, APELLIDO1, ID_PAIS "
					+ "FROM ESTUDIANTE "
					+ "WHERE ID_PAIS = ? ";
			System.out.println("Creating statment...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, pais);
			
			resultSet  =preparedStatement.executeQuery();
			
		
			
			List<Estudiante> estudiantes = new ArrayList<Estudiante>();
			Estudiante e = null;
			while(resultSet.next()) {
				e = loadNext(resultSet);
				estudiantes.add(e);
			}
			return estudiantes;
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		} 
	}

	public Estudiante create (Estudiante e) 
			throws Exception{
		return null;
	}

	public boolean update (Estudiante e) 
			throws Exception{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = ConnectionManager.getConnection();
			
			String sql;
			sql = "SELECT ID_ESTUDIANTE, NOMBRE, APELLIDO1, ID_PAIS "
					+ "FROM ESTUDIANTE "
					+ "WHERE ID_PAIS = ? ";
			System.out.println("Creating statment...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
		}finally {}
		
		return false;
	}
	
	//load next
	
	private Estudiante loadNext(ResultSet resultSet) throws Exception {
		Estudiante e = new Estudiante();
		int i = 1;
		Integer idEstudiante = resultSet.getInt(i++);
		String nombre = resultSet.getString(i++);
		String apellido1 = resultSet.getString(i++);	
		String email = resultSet.getString(i++);
		Date fechaSubscripcion = resultSet.getDate(i++);

		
		e = new Estudiante();
		e.setIdEstudiante(idEstudiante);
		e.setNombre(nombre);
		e.setApellido1(apellido1);
		e.setEmail(email);
		e.setFechaSubscripcion(fechaSubscripcion);
		
		
		return e;
	}
	
	private Estudiante loadNextWithPais(ResultSet resultSet) throws Exception {
		Estudiante e = new Estudiante();
		int i = 1;
		Integer idEstudiante = resultSet.getInt(i++);
		String nombre = resultSet.getString(i++);
		String apellido1 = resultSet.getString(i++);	
		String email = resultSet.getString(i++);
		Date fechaSubscripcion = resultSet.getDate(i++);

		
		e = new Estudiante();
		e.setIdEstudiante(idEstudiante);
		e.setNombre(nombre);
		e.setApellido1(apellido1);
		e.setEmail(email);
		e.setFechaSubscripcion(fechaSubscripcion);
		
		
		return e;
	}
	

}
