package com.educorp.eduinteractive.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.EstudianteDAO;
import com.educorp.eduinteractive.ecommerce.dao.service.DAOUtils;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.service.EstudianteCriteria;
import com.educorp.eduinteractive.ecommerce.service.PasswordEncryptionUtil;

public class EstudianteDAOImpl implements EstudianteDAO{
	@Override
	public Estudiante findById(Connection connection, Integer id)
			throws  DataException {

		Estudiante e = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

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
				throw new DataException("Non se encontrou o estudiante "+id);
			}
			if (resultSet.next()) {
				throw new DataException("Estudainte"+id+" duplicado");
			}

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		return e;
	}
	
	
	@Override
	public List<Estudiante> findByNombre(Connection connection, String criterioNombre)
			throws DataException {	

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			String sql;
			sql =  	 "SELECT ID_ESTUDIANTE, EMAIL, ID_PAIS, PSSWD, NOMBRE, APELLIDO1, APELLIDO2, ANO_NACIMIENTO, FECHA_SUBSCRIPCION, ID_NIVEL, ID_GENERO "
					+"  FROM ESTUDIANTE "
					+"	UPPER(FIRST_NAME) LIKE ? " 
					+"  AND"
					+"  UPPER(LAST_NAME) LIKE  ? ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setString(i++, "%" + criterioNombre.toUpperCase() + "%");			

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

	@Override
	public List<Estudiante> findByCriteria (Connection connection, EstudianteCriteria estudiante)
		throws DataException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;

		try {
    
			queryString = new StringBuilder(
					"SELECT ID_ESTUDIANTE, EMAIL,ID_NIVEL, PSSWD, NOMBRE, APELLIDO1, APELLIDO2, ANO_NACIMIENTO, FECHA_SUBSCRIPCION, ID_NNIVEL, ID_GENERO "
					+" FROM ESTUDIANTE ");
			
			boolean first = true;
			
			if (estudiante.getIdEstudiante() != null) {
				DAOUtils.addClause(queryString, first, " id_estudiante =  ? ");
				first = false;
			}	

			if (estudiante.getEmail() != null) {
				DAOUtils.addClause(queryString, first, " upper(email) LIKE upper(?) ");
				first = false;
			}
			
			if (estudiante.getIdPais() != null) {
				DAOUtils.addClause(queryString, first, "id_pais = ?");
				first = false;
			}
			
			if (estudiante.getPsswd() != null) {
				DAOUtils.addClause(queryString, first, " psswd = ? ");
				first = false;
			}
			
			if (estudiante.getNombre() != null) {
				DAOUtils.addClause(queryString, first, " upper(nombre) LIKE upper(?) ");
				first = false;
			}
			
			if (estudiante.getApellido1() != null) {
				DAOUtils.addClause(queryString, first, " upper(apellido1) LIKE upper(?) ");
				first = false;
			}
			
			if (estudiante.getApellido2() != null) {
				DAOUtils.addClause(queryString, first, " upper(apellido2) LIKE upper(?) ");
				first = false;
			}
			
			if (estudiante.getAnoNacimiento() != null) {
				DAOUtils.addClause(queryString, first, "ano_nacimiento = ?");
				first = false;
			}
			
			if (estudiante.getFechaSubscripcion() != null) {
				DAOUtils.addClause(queryString, first, "fecha_nacimiento = ?");
				first = false;
			}
			
			if (estudiante.getIdNivel() != null) {
				DAOUtils.addClause(queryString, first, "id_nivel = ?");
				first = false;
			}
			
			if (estudiante.getIdGenero() != null) {
				DAOUtils.addClause(queryString, first, "id_genero = ?");
				first = false;
			}

			
			
			preparedStatement = connection.prepareStatement(queryString.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;  
			
			if (estudiante.getIdEstudiante() != null)
				preparedStatement.setInt(i++, estudiante.getIdEstudiante());
			if (estudiante.getEmail()!=null) 
				preparedStatement.setString(i++,estudiante.getEmail() );
			if (estudiante.getIdPais() != null)
				preparedStatement.setString(i++, estudiante.getIdPais());
			if (estudiante.getPsswd()!=null) 
				preparedStatement.setString(i++, estudiante.getPsswd());
			if (estudiante.getNombre()!=null)
				preparedStatement.setString(i++,estudiante.getNombre());
			if (estudiante.getApellido1()!=null) 
				preparedStatement.setString(i++,estudiante.getApellido1());
			if (estudiante.getApellido2()!=null) 
				preparedStatement.setString(i++,estudiante.getApellido1());
			if (estudiante.getAnoNacimiento() != null)
				preparedStatement.setInt(i++, estudiante.getAnoNacimiento());
			if (estudiante.getFechaSubscripcion() != null)
				preparedStatement.setDate(i++, (java.sql.Date) estudiante.getFechaSubscripcion());



			resultSet = preparedStatement.executeQuery();
			
			List<Estudiante> estudiantes = new ArrayList<Estudiante>();                        
			Estudiante e = null;

			while (resultSet.next()) {
				e = loadNext(resultSet);						
				estudiantes.add(e);
			}

			return estudiantes;
	
			} catch (SQLException e) {
				throw new DataException(e);
			} finally {
				JDBCUtils.closeResultSet(resultSet);
				JDBCUtils.closeStatement(preparedStatement);
		}
	}
	
	@Override
	public Estudiante findByEmail (Connection connection, String email)
			throws DataException{
		Estudiante e = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_ESTUDIANTE, EMAIL,ID_NIVEL, PSSWD, NOMBRE, APELLIDO1, APELLIDO2, ANO_NACIMIENTO, FECHA_SUBSCRIPCION, ID_NNIVEL, ID_GENERO "
					+"FROM ESTUDIANTE "
					+"WHERE upper(email) like upper(?) ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setString(i++, email);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set						
			if (resultSet.next()) {				
				e = loadNext(resultSet);				
			} else {
				throw new DataException("Non se encontrou o estudiante "+email);
			}
			if (resultSet.next()) {
				throw new DataException("Estudainte"+email+" duplicado");
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

	@Override
	public Boolean exists(Connection connection, String email) 
			throws DataException {
		boolean exist = false;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
					"SELECT  EMAIL " + 
							" FROM ESTUDIANTE " +
							" WHERE UPPER(EMAIL) LIKE UPPER(?) ";

			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setString(i++, email);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				exist = true;
			}

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

		return exist;
	}	

	@Override
	public Estudiante create(Connection connection, Estudiante e) 
			throws DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			// Creamos el preparedstatement
			String queryString = "INSERT INTO ESTUDIANTE (EMAIL, ID_PAIS, PSSWD, NOMBRE, APELLIDO1, APELLIDO2, ANO_NACIMIENTO, FECHA_SUBSCRIPCION,ID_NIVEL, ID_GENERO) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(queryString,
									Statement.RETURN_GENERATED_KEYS);

			// Rellenamos el "preparedStatement"
			int i = 1;    
			preparedStatement.setString(i++, e.getEmail());
			preparedStatement.setString(i++, e.getIdPais());
			preparedStatement.setString(i++, PasswordEncryptionUtil.encryptPassword(e.getPsswd()));
			preparedStatement.setString(i++, e.getNombre());
			preparedStatement.setString(i++, e.getApellido1());
			preparedStatement.setString(i++, e.getApellido2());
			preparedStatement.setInt(i++, e.getAnoNacimiento());
			preparedStatement.setDate(i++, new java.sql.Date(e.getFechaSubscripcion().getTime()));
			preparedStatement.setInt(i++, e.getIdNivel());
			preparedStatement.setString(i++, e.getIdGenero());
			
			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Estudiante'");
			}

			// Recuperamos la PK generada
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Integer pk = resultSet.getInt(1); 
				e.setIdEstudiante(pk);
			} else {
				throw new DataException("Unable to fetch autogenerated primary key");
			}

			// Return the DTO
			return e;

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public void update(Connection c, Estudiante e) throws  DataException {
		
		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;
		try {	
			
			queryString = new StringBuilder(
					" UPDATE Estudiante" 
					);
			
			boolean first = true;
			
			if (e.getIdEstudiante() != null) {
				DAOUtils.addUpdate(queryString, first, " id_estudiante =  ? ");
				first = false;
			}	

			if (e.getEmail() != null) {
				DAOUtils.addUpdate(queryString, first, " upper(email) LIKE upper(?) ");
				first = false;
			}
			
			if (e.getIdPais() != null) {
				DAOUtils.addUpdate(queryString, first, "id_pais = ?");
				first = false;
			}
			
			if (e.getPsswd() != null) {
				DAOUtils.addUpdate(queryString, first, " psswd = ? ");
				first = false;
			}
			
			if (e.getNombre() != null) {
				DAOUtils.addUpdate(queryString, first, " upper(nombre) LIKE upper(?) ");
				first = false;
			}
			
			if (e.getApellido1() != null) {
				DAOUtils.addUpdate(queryString, first, " upper(apellido1) LIKE upper(?) ");
				first = false;
			}
			
			if (e.getApellido2() != null) {
				DAOUtils.addUpdate(queryString, first, " upper(apellido2) LIKE upper(?) ");
				first = false;
			}
			
			if (e.getAnoNacimiento() != null) {
				DAOUtils.addUpdate(queryString, first, "ano_nacimiento = ?");
				first = false;
			}
			
			if (e.getFechaSubscripcion() != null) {
				DAOUtils.addUpdate(queryString, first, "fecha_nacimiento = ?");
				first = false;
			}
			
			if (e.getIdNivel() != null) {
				DAOUtils.addUpdate(queryString, first, "id_nivel = ?");
				first = false;
			}
			
			if (e.getIdGenero() != null) {
				DAOUtils.addUpdate(queryString, first, "id_genero = ?");
				first = false;
			}
			
			
						
			queryString.append("WHERE id_estudiante = ?");
			
			preparedStatement = c.prepareStatement(queryString.toString());
			

			int i = 1;
			if (e.getIdEstudiante() != null)
				preparedStatement.setInt(i++, e.getIdEstudiante());
			if (e.getEmail()!=null) 
				preparedStatement.setString(i++,e.getEmail() );
			if (e.getIdPais() != null)
				preparedStatement.setString(i++, e.getIdPais());
			if (e.getPsswd()!=null) 
				preparedStatement.setString(i++, PasswordEncryptionUtil.encryptPassword(e.getPsswd()));
			if (e.getNombre()!=null)
				preparedStatement.setString(i++,e.getNombre());
			if (e.getApellido1()!=null) 
				preparedStatement.setString(i++,e.getApellido1());
			if (e.getApellido2()!=null) 
				preparedStatement.setString(i++,e.getApellido1());
			if (e.getAnoNacimiento() != null)
				preparedStatement.setInt(i++, e.getAnoNacimiento());
			if (e.getFechaSubscripcion() != null)
				preparedStatement.setDate(i++, (java.sql.Date) e.getFechaSubscripcion());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new DataException("Non se actualizou o estudiante");
			}

			if (updatedRows > 1) {
				throw new SQLException("Duplicate row for id = '" + 
						e.getIdEstudiante() + "' in table 'E'");
			}     
			
		} catch (SQLException ex) {
			throw new DataException(ex);    
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
		
	}
	
	private Estudiante loadNext(ResultSet resultSet) throws SQLException, DataException {

		Estudiante e = new Estudiante();
		int i = 1;
		Integer id = resultSet.getInt(i++);
		String email = resultSet.getString(i++);
		String idPais = resultSet.getString(i++);
		String contra = resultSet.getString(i++);
		String nombre = resultSet.getString(i++);
		String apellido1 = resultSet.getString(i++);	
		String apellido2 = resultSet.getString(i++);
		Integer anoNacimiento = resultSet.getInt(i++);
		Date fechaSubscripcion = resultSet.getDate(i++);
		Integer idNivel = resultSet.getInt(i++);
		String idGenero = resultSet.getString(i++);
		


		e = new Estudiante();
		e.setIdEstudiante(id);
		e.setEmail(email);
		e.setIdPais(idPais);
		e.setPsswd(contra);
		e.setNombre(nombre);
		e.setApellido1(apellido1);
		e.setApellido2(apellido2);
		e.setAnoNacimiento(anoNacimiento);
		e.setFechaSubscripcion(fechaSubscripcion);
		e.setIdNivel(idNivel);
		e.setIdGenero(idGenero);

		return e;

	}
}
