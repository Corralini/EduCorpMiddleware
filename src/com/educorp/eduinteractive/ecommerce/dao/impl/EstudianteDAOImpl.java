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
import com.educorp.eduinteractive.ecommerce.dao.spi.EstudianteDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.service.criteria.EstudianteCriteria;
import com.educorp.eduinteractive.exceptions.PasswordEncryptionUtil;

public class EstudianteDAOImpl implements EstudianteDAO{
	
	private static Logger logger = LogManager.getLogger(EstudianteDAOImpl.class);
	
	@Override
	public Estudiante findById(Connection connection, Integer id)
			throws  InstanceNotFoundException,DataException {
		
		logger.debug("id = {} ", id);
		
		Estudiante e = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_ESTUDIANTE, EMAIL, ID_PAIS, PSSWD, NOMBRE, APELLIDO1, APELLIDO2, ANO_NACIMIENTO, FECHA_SUBSCRIPCION, ID_NIVEL, ID_GENERO, CODIGO_DE_RECUPERACION "
					+"FROM ESTUDIANTE "
					+"WHERE ID_ESTUDIANTE = ? ";
			
			logger.debug(sql);
			// Preparar a query
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setInt(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set						
			if (resultSet.next()) {				
				e = loadNext(connection, resultSet);				
			} else {
				logger.debug("Non se encontrou o estudiante {}", id);
			}
			if (resultSet.next()) {
				logger.debug("Estudainte {} duplicado" , id);
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		return e;
	}


	@Override
	public List<Estudiante> findByNombre(Connection connection, String nombre)
			throws DataException {	
		logger.debug("nombre = {} ", nombre);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			String sql;
			sql =  	 "SELECT ID_ESTUDIANTE, EMAIL, ID_PAIS, PSSWD, NOMBRE, APELLIDO1, APELLIDO2, ANO_NACIMIENTO, FECHA_SUBSCRIPCION, ID_NIVEL, ID_GENERO, CODIGO_DE_RECUPERACION "
					+"  FROM ESTUDIANTE "
					+"	WHERE UPPER(NOMBRE) LIKE UPPER(?) ";

			// Preparar a query
			logger.debug(sql);
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setString(i++, "%" + nombre.toUpperCase() + "%");			

			resultSet = preparedStatement.executeQuery();			

			//STEP 5: Extract data from result set			
			List<Estudiante> empleados = new ArrayList<Estudiante>();
			Estudiante e = null;
			while (resultSet.next()) {
				e = loadNext(connection, resultSet);						
				empleados.add(e);
			} 
			return empleados;
		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException("Non se encontrou o estudiante " + nombre);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}  	
	}

	@Override
	public List<Estudiante> findByCriteria (Connection connection, EstudianteCriteria estudiante)
			throws DataException{
		
		logger.debug("criteria = {}", estudiante);
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;

		try {

			queryString = new StringBuilder(
					"SELECT e.ID_ESTUDIANTE, e.EMAIL, e.ID_PAIS, e.PSSWD, e.NOMBRE, e.APELLIDO1, e.APELLIDO2, e.ANO_NACIMIENTO, e.FECHA_SUBSCRIPCION, e.ID_NIVEL, e.ID_GENERO, e.CODIGO_DE_RECUPERACION, punt.puntuacion "
							+" from estudiante e  left join profesor_puntua_estudiante punt on (e.ID_ESTUDIANTE = punt.ID_ESTUDIANTE) ");
			
			
			boolean first = true;

			if (estudiante.getIdEstudiante() != null) {
				DAOUtils.addClause(queryString, first, " e.id_estudiante =  ? ");
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
				DAOUtils.addClause(queryString, first, " ano_nacimiento = ? ");
				first = false;
			}

			if (estudiante.getFechaSubscripcion() != null) {
				DAOUtils.addClause(queryString, first, " fecha_nacimiento = ? ");
				first = false;
			}

			if (estudiante.getIdNivel() != null) {
				DAOUtils.addClause(queryString, first, " id_nivel = ? ");
				first = false;
			}

			if (estudiante.getIdGenero() != null) {
				DAOUtils.addClause(queryString, first, " id_genero = ? "
						+ "");
				first = false;
			}
			
			if (estudiante.getPuntuacion() != null) {
				DAOUtils.addClause(queryString, first, " (SELECT AVG(puntuacion) FROM profesor_puntua_estudiante puntu where puntu.ID_ESTUDIANTE = e.ID_ESTUDIANTE) > ? ");
			}
			
			queryString.append(" group By e.id_estudiante " 
								+ " order by punt.puntuacion desc ");

			logger.debug(queryString);

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
				preparedStatement.setString(i++, "%" + estudiante.getNombre() + "%");
			if (estudiante.getApellido1()!=null) 
				preparedStatement.setString(i++, "%" + estudiante.getApellido1() + "%");
			if (estudiante.getApellido2()!=null) 
				preparedStatement.setString(i++, "%" + estudiante.getApellido2() + "%");
			if (estudiante.getAnoNacimiento() != null)
				preparedStatement.setInt(i++, estudiante.getAnoNacimiento());
			if (estudiante.getFechaSubscripcion() != null)
				preparedStatement.setDate(i++, new java.sql.Date(new Date().getTime()));
			if(estudiante.getIdNivel() != null)
				preparedStatement.setInt(i++, estudiante.getIdNivel());
			if (estudiante.getIdGenero() != null)
				preparedStatement.setString(i++, estudiante.getIdGenero());
			if (estudiante.getPuntuacion() != null)
				preparedStatement.setDouble(i++, estudiante.getPuntuacion());
			

			resultSet = preparedStatement.executeQuery();

			List<Estudiante> estudiantes = new ArrayList<Estudiante>();                        
			Estudiante e = null;

				while (resultSet.next()) {
					e = loadNext(connection, resultSet);						
					estudiantes.add(e);
				}
				
				if (estudiantes.isEmpty()) {
					throw new DataException("No se han encontrado resultados");
				}

				return estudiantes;
		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException("Hemos encontrado un problema" + e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public Estudiante findByEmail (Connection connection, String email)
			throws DataException{
		logger.debug("Email = {}", (email==null));
		Estudiante e = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_ESTUDIANTE, EMAIL, ID_PAIS, PSSWD, NOMBRE, APELLIDO1, APELLIDO2, ANO_NACIMIENTO, FECHA_SUBSCRIPCION, ID_NIVEL, ID_GENERO, CODIGO_DE_RECUPERACION "
					+"FROM ESTUDIANTE "
					+"WHERE upper(email) like upper(?) ";
			logger.debug(sql);
			// Preparar a query
			
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setString(i++, email);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set						
			if (resultSet.next()) {				
				e = loadNext(connection, resultSet);				
			} else {
				logger.debug("Non se encontrou o estudiante {}", email);
			}
			if (resultSet.next()) {
				logger.debug("Estudainte {} duplicado", email);
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		return e;
	}

	@Override
	public Boolean exists(Connection connection, String email) 
			throws DataException {
		boolean exist = false;
		logger.debug("Email = {}", (email==null));
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
					"SELECT  EMAIL " + 
							" FROM ESTUDIANTE " +
							" WHERE UPPER(EMAIL) LIKE UPPER(?) ";
			logger.debug(queryString);
			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setString(i++, email);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				exist = true;
			}

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

		return exist;
	}	

	@Override
	public Estudiante create(Connection connection, Estudiante e) 
			throws DuplicateInstanceException, DataException {
		logger.debug("Estudiante = {}" + e);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			// Creamos el preparedstatement
			String queryString = "INSERT INTO ESTUDIANTE (EMAIL, ID_PAIS, PSSWD, NOMBRE, APELLIDO1, APELLIDO2, ANO_NACIMIENTO, FECHA_SUBSCRIPCION,ID_NIVEL, ID_GENERO) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			logger.debug(queryString);
			preparedStatement = connection.prepareStatement(queryString,
					Statement.RETURN_GENERATED_KEYS);

			// Rellenamos el "preparedStatement"
			int i = 1;    
			preparedStatement.setString(i++, e.getEmail());
			preparedStatement.setString(i++, e.getIdPais().toUpperCase());
			preparedStatement.setString(i++, PasswordEncryptionUtil.encryptPassword(e.getPsswd()));
			preparedStatement.setString(i++, e.getNombre());
			preparedStatement.setString(i++, e.getApellido1());
			preparedStatement.setString(i++, e.getApellido2());
			preparedStatement.setInt(i++,  e.getAnoNacimiento());
			preparedStatement.setDate(i++, new java.sql.Date(new Date().getTime()));
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
				logger.warn("Unable to fetch autogenerated primary key");
			}

			// Return the DTO
			return e;

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException("Hemos tenido algunos problemas, por favor introduzca de nuevo los datos" + ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public void update(Connection c, Estudiante e) 
			throws  InstanceNotFoundException, DataException {
		logger.debug("Estudiante = {}" + e);
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
				DAOUtils.addUpdate(queryString, first, " email = ? ");
				first = false;
			}

			if (e.getIdPais() != null) {
				DAOUtils.addUpdate(queryString, first, "id_pais = ? ");
				first = false;
			}

			if (e.getPsswd() != null) {
				DAOUtils.addUpdate(queryString, first, " psswd = ? ");
				first = false;
			}

			if (e.getNombre() != null) {
				DAOUtils.addUpdate(queryString, first, " nombre = ? ");
				first = false;
			}

			if (e.getApellido1() != null) {
				DAOUtils.addUpdate(queryString, first, " apelllido1 = ? ");
				first = false;
			}

			if (e.getApellido2() != null) {
				DAOUtils.addUpdate(queryString, first, " apelllido2 = ? ");
				first = false;
			}

			if (e.getAnoNacimiento() != null) {
				DAOUtils.addUpdate(queryString, first, "ano_nacimiento = ? ");
				first = false;
			}

			if (e.getFechaSubscripcion() != null) {
				DAOUtils.addUpdate(queryString, first, "fecha_nacimiento = ? ");
				first = false;
			}

			if (e.getIdNivel() != null) {
				DAOUtils.addUpdate(queryString, first, "id_nivel = ? ");
				first = false;
			}

			if (e.getIdGenero() != null) {
				DAOUtils.addUpdate(queryString, first, "id_genero = ? ");
				first = false;
			}

			if(e.getCodigoDeRecuperacion() != null) {
				DAOUtils.addUpdate(queryString, first, " codigo_de_recuperacion = ? ");
				first = false;
			}


			queryString.append(" WHERE id_estudiante = ? ");
			logger.debug(queryString);
			preparedStatement = c.prepareStatement(queryString.toString());


			int i = 1;
			if (e.getIdEstudiante() != null)
				preparedStatement.setInt(i++, e.getIdEstudiante());
			if (e.getEmail()!=null) 
				preparedStatement.setString(i++,e.getEmail() );
			if (e.getIdPais() != null)
				preparedStatement.setString(i++, e.getIdPais().toUpperCase());
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
			if (e.getIdNivel() != null)
				preparedStatement.setInt(i++, e.getIdNivel());
			if(e.getIdGenero() != null)
				preparedStatement.setString(i++, e.getIdGenero().toUpperCase());
			if(e.getCodigoDeRecuperacion() != null)
				preparedStatement.setInt(i++, e.getCodigoDeRecuperacion());

			preparedStatement.setInt(i++, findByEmail(c, e.getEmail()).getIdEstudiante());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				logger.debug("Non se actualizou o estudiante");
			}

			if (updatedRows > 1) {
				logger.debug("Duplicate row for id = '{}' in table 'E'" , e.getIdEstudiante());
			}     

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);    
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}

	}

	private Estudiante loadNext(Connection connection, ResultSet resultSet) throws SQLException, DataException {

		Estudiante e = new Estudiante();
		int i = 1;
		Double puntuacion = 0.0d;
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
		Integer codigoRecuperacion = resultSet.getInt(i++);



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
		e.setCodigoDeRecuperacion(codigoRecuperacion);
		
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		String queryString = "SELECT AVG(PUNTUACION) "
							+ " FROM profesor_puntua_estudiante"
							+ " WHERE ID_ESTUDIANTE = " +e.getIdEstudiante();
		preparedStatement = connection.prepareStatement(queryString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = preparedStatement.executeQuery();
		if (rs.next()) {
			puntuacion = rs.getDouble(1);
		}
		
		e.setPuntuacion(puntuacion);

		return e;

	}
}
