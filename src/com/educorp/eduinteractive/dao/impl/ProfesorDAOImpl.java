package com.educorp.eduinteractive.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.educorp.eduinteractive.dao.ProfesorDAO;
import com.educorp.eduinteractive.dao.service.JDBCUtils;
import com.educorp.eduinteractive.exceptions.DataException;
import com.educorp.eduinteractive.model.Profesor;
import com.educorp.eduinteractive.service.ProfesorCriteria;

public class ProfesorDAOImpl implements ProfesorDAO {

	@Override
	public Profesor findById(Connection connection, Integer id) throws Exception {
		Profesor p = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_PROFESOR, EMAIL, PSSWD, ID_PAIS, NOMBRE, APELLIDO1, APELLIDO2, ANO_NACIMIENTO, FECHA_SUBSCRIPCION,PRECIO_SESION, ID_IDIOMA, ID_GENERO, ID_NIVEL, ACTIVADA, DESCRIPCION "
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
				p = loadNext(resultSet);				
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

		return p;
	}

	@Override
	public List<Profesor> findByNombre(Connection connection, String nombre) throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

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
			preparedStatement.setString(i++, "%" + nombre.toUpperCase() + "%");			

			resultSet = preparedStatement.executeQuery();			

			//STEP 5: Extract data from result set			
			List<Profesor> profesores = new ArrayList<Profesor>();
			Profesor p = null;
			while (resultSet.next()) {
				p = loadNext(resultSet);						
				profesores.add(p);
			} 
			return profesores;
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}  	
	}

	@Override
	public List<Profesor> findByCriteria(Connection connection, ProfesorCriteria profesor) throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;

		try {
    
			queryString = new StringBuilder(
					"SELECT ID_ESTUDIANTE, EMAIL,ID_NIVEL, PSSWD, NOMBRE, APELLIDO1, APELLIDO2, ANO_NACIMIENTO, FECHA_SUBSCRIPCION, ID_NNIVEL, ID_GENERO "
					+" FROM ESTUDIANTE ");
			
			boolean first = true;
			
			if (profesor.getIdProfesor() != null) {
				addClause(queryString, first, " id_usuario =  ? ");
				first = false;
			}	

			if (profesor.getEmail() != null) {
				addClause(queryString, first, " upper(email) LIKE upper(?) ");
				first = false;
			}
			
			if (profesor.getPsswd() != null) {
				addClause(queryString, first, " psswd = ? ");
				first = false;
			}
			
			if (profesor.getIdPais() != null) {
				addClause(queryString, first, "id_pais = ?");
				first = false;
			}
			
			if (profesor.getNombre() != null) {
				addClause(queryString, first, " upper(nombre) LIKE upper(?) ");
				first = false;
			}
			
			if (profesor.getApellido1() != null) {
				addClause(queryString, first, " upper(apellido1) LIKE upper(?) ");
				first = false;
			}
			
			if (profesor.getApellido2() != null) {
				addClause(queryString, first, " upper(apellido2) LIKE upper(?) ");
				first = false;
			}
			
			if (profesor.getAnoNacimiento() != null) {
				addClause(queryString, first, "ano_nacimiento = ?");
				first = false;
			}
			
			if (profesor.getFechaSubscripcion() != null) {
				addClause(queryString, first, "fecha_nacimiento = ?");
				first = false;
			}
			
			if (profesor.getPrecioSesion() != null) {
				addClause(queryString, first, "precio sesion > ?");
				first = false;
			}
			
			if (profesor.getPrecioSesionHasta() != null) {
				addClause(queryString, first, "precio sesion < ?");
				first = false;
			}

			if (profesor.getIdIdioma() != null) {
				addClause(queryString, first, "id_idioma = ?");
				first = false;
			}
			
			if (profesor.getIdGenero() != null) {
				addClause(queryString, first, "id_genero = ?");
				first = false;
			}
			
			if (profesor.getIdNivel() != null) {
				addClause(queryString, first, "id_nivel = ?");
				first = false;
			}
			
			preparedStatement = connection.prepareStatement(queryString.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;  
			
			if (profesor.getIdProfesor() != null)
				preparedStatement.setInt(i++, profesor.getIdProfesor());
			if (profesor.getEmail()!=null) 
				preparedStatement.setString(i++,profesor.getEmail() );
			if (profesor.getPsswd()!=null) 
				preparedStatement.setString(i++, profesor.getPsswd());
			if (profesor.getIdPais() != null)
				preparedStatement.setString(i++, profesor.getIdPais());
			if (profesor.getNombre()!=null)
				preparedStatement.setString(i++,profesor.getNombre());
			if (profesor.getApellido1()!=null) 
				preparedStatement.setString(i++,profesor.getApellido1());
			if (profesor.getApellido2()!=null) 
				preparedStatement.setString(i++,profesor.getApellido1());
			if (profesor.getAnoNacimiento() != null)
				preparedStatement.setDate(i++, (java.sql.Date) profesor.getAnoNacimiento());
			if (profesor.getFechaSubscripcion() != null)
				preparedStatement.setDate(i++, (java.sql.Date) profesor.getFechaSubscripcion());
			if (profesor.getPrecioSesion() != null) 
				preparedStatement.setDouble(i++, profesor.getPrecioSesion());
			if (profesor.getPrecioSesionHasta() != null) 
				preparedStatement.setDouble(i++, profesor.getPrecioSesionHasta());
			if (profesor.getIdIdioma() != null)
				preparedStatement.setString(i++, profesor.getIdIdioma());
			if (profesor.getIdGenero() != null)
				preparedStatement.setString(i++, profesor.getIdGenero());
			if (profesor.getIdNivel() != null)
				preparedStatement.setInt(i++, profesor.getIdNivel());
			if (profesor.getAceptado() != null)
				preparedStatement.setInt(i++, profesor.getAceptado());
			if (profesor.getDescripcion() != null)
				preparedStatement.setString(i++, profesor.getDescripcion());


			resultSet = preparedStatement.executeQuery();
			
			List<Profesor> profesores = new ArrayList<Profesor>();                        
			Profesor p = null;

			while (resultSet.next()) {
				p = loadNext(resultSet);						
				profesores.add(p);
			}

			return profesores;
	
			} catch (SQLException e) {
				throw new DataException(e);
			} finally {
				JDBCUtils.closeResultSet(resultSet);
				JDBCUtils.closeStatement(preparedStatement);
		}
		
	}

	@Override
	public Profesor create(Connection c, Profesor p) throws Exception {
		PreparedStatement preparedStatement = null;
		try {          

			//Check if the primary key already exists
			//			if (exists(connection, e.getId())) {
			//				throw new Exception("Duplicate employee "+e.getId());
			//			}


			String queryString = "INSERT INTO ESTUDIANTE (EMAIL, ID_PAIS, PSSWD, NOMBRE, APELLIDO1, APELLIDO2, ANO_NACIMIENTO, FECHA_SUBSCRIPCION,ID_NIVEL, ID_GENERO) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = c.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setString(i++, p.getEmail());
			preparedStatement.setString(i++, p.getIdPais());
			preparedStatement.setString(i++, p.getPsswd());
			preparedStatement.setString(i++, p.getNombre());
			preparedStatement.setString(i++, p.getApellido1());
			preparedStatement.setString(i++, p.getApellido2());
			preparedStatement.setInt(i++, p.getAnoNacimiento());
			preparedStatement.setDate(i++, new java.sql.Date(p.getFechaSubscripcion().getTime()));
			preparedStatement.setInt(i++, p.getIdNivel());
			preparedStatement.setString(i++, p.getIdGenero());


			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Employees'");
			}

			//...
			return p;					

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
		return null;
	}

	@Override
	public Profesor update(Connection c, Profesor p) throws Exception {
		
		return null;
	}

	private Profesor loadNext(ResultSet resultSet) throws Exception {

		Profesor p = new Profesor();
		int i = 1;
		Integer id = resultSet.getInt(i++);
		String email = resultSet.getString(i++);
		String contra = resultSet.getString(i++);
		String idPais = resultSet.getString(i++);
		String nombre = resultSet.getString(i++);
		String apellido1 = resultSet.getString(i++);	
		String apellido2 = resultSet.getString(i++);
		Date anoNacimiento = resultSet.getDate(i++);
		Date fechaSubscripcion = resultSet.getDate(i++);
		Double precioSesion = resultSet.getDouble(i++);
		String idIdioma = resultSet.getString(i++);
		String idGenero = resultSet.getString(i++);
		Integer idNivel = resultSet.getInt(i++);
		Integer activada = resultSet.getInt(i++);
		String descripcion = resultSet.getString(i++);


		p = new Profesor();
		p.setIdProfesor(id);
		p.setEmail(email);
		p.setPsswd(contra);
		p.setIdPais(idPais);
		p.setNombre(nombre);
		p.setApellido1(apellido1);
		p.setApellido2(apellido2);
		p.setAnoNacimiento(anoNacimiento);
		p.setFechaSubscripcion(fechaSubscripcion);
		p.setPrecioSesion(precioSesion);
		p.setIdIdioma(idIdioma);
		p.setIdGenero(idGenero);
		p.setIdNivel(idNivel);
		p.setAceptado(activada);
		p.setDescripcion(descripcion);
		

		return p;

	}
	
	private void addClause(StringBuilder queryString, boolean first, String clause) {
		queryString.append(first?" WHERE ": " AND ").append(clause);
	}
	
	private void addUpdate(StringBuilder queryString, boolean first, String clause) {
		queryString.append(first?" SET ": " , ").append(clause);
	}
	
}
