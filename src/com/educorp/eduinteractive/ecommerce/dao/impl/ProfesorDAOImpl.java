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
import com.educorp.eduinteractive.ecommerce.dao.spi.ProfesorDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Profesor;
import com.educorp.eduinteractive.ecommerce.service.criteria.ProfesorCriteria;
import com.educorp.eduinteractive.exceptions.PasswordEncryptionUtil;

public class ProfesorDAOImpl implements ProfesorDAO {

	private Logger logger = LogManager.getLogger(ProfesorDAOImpl.class);

	@Override
	public Profesor findById(Connection connection, Integer id) 
			throws InstanceNotFoundException, DataException {
		if(logger.isDebugEnabled()) logger.debug("id: {}", id);
		Profesor p = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT P.ID_PROFESOR, P.EMAIL, P.PSSWD, P.ID_PAIS, P.NOMBRE, P.APELLIDO1, P.APELLIDO2, P.ANO_NACIMIENTO, P.FECHA_SUBSCRIPCION, P.PRECIO_SESION, P.ID_IDIOMA, P.ID_GENERO, P.ID_NIVEL, P.ACTIVADA, P.DESCRIPCION, P.CODIGO_DE_RECUPERACION "
					+" from profesor p  "
					+"where P.id_profesor = ? ";
			if(logger.isDebugEnabled()) logger.debug(sql);
			// Preparar a query
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set						
			if (resultSet.next()) {				
				p = loadNext(connection, resultSet);				
			} else {
				if(logger.isDebugEnabled()) logger.debug("Non se encontrou o profesor {}", id);
			}
			if (resultSet.next()) {
				if(logger.isDebugEnabled()) logger.debug("Profesor {} duplicado", id);
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		return p;
	}

	@Override
	public Profesor findByEmail (Connection connection, String email)
			throws DataException{
		if(logger.isDebugEnabled()) logger.debug("Email: {}", email);
		Profesor e = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT P.ID_PROFESOR, P.EMAIL, P.PSSWD, P.ID_PAIS, P.NOMBRE, P.APELLIDO1, P.APELLIDO2, P.ANO_NACIMIENTO, P.FECHA_SUBSCRIPCION, P.PRECIO_SESION, P.ID_IDIOMA, P.ID_GENERO, P.ID_NIVEL, P.ACTIVADA, P.DESCRIPCION, P.CODIGO_DE_RECUPERACION "
					+" from profesor p  "
					+" WHERE upper(p.email) like upper(?) ";
			if(logger.isDebugEnabled()) logger.debug(sql);
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
				if(logger.isDebugEnabled()) logger.debug("Non se encontrou o estudiante {}", email);
			}
			if (resultSet.next()) {
				if(logger.isDebugEnabled()) logger.debug("Estudainte {} duplicado", email);
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		return e;
	}


	@Override
	public Results<Profesor> findByCriteria(Connection connection, ProfesorCriteria profesor,
			int startIndex, int count) 
					throws DataException {
		if(logger.isDebugEnabled()) logger.debug("Profesor: {}", profesor);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;

		try {

			queryString = new StringBuilder(
					"select P.ID_PROFESOR, P.EMAIL, P.PSSWD, P.ID_PAIS, P.NOMBRE, P.APELLIDO1, P.APELLIDO2, P.ANO_NACIMIENTO, P.FECHA_SUBSCRIPCION, P.PRECIO_SESION, P.ID_IDIOMA, P.ID_GENERO, P.ID_NIVEL, P.ACTIVADA, P.DESCRIPCION, P.CODIGO_DE_RECUPERACION, punt.puntuacion "
							+" from profesor p left join estudiante_puntua_profesor punt on (p.id_profesor = punt.id_profesor) "
							+" inner join horario h on (p.id_profesor = h.id_profesor) where  p.activada <> 0");
			boolean first = false;

			if (profesor.getIdProfesor() != null) {
				DAOUtils.addClause(queryString, first, " p.ID_PROFESOR =  ? ");
				first = false;
			}	

			if (profesor.getEmail() != null) {
				DAOUtils.addClause(queryString, first, " upper(p.email) LIKE upper(?) ");
				first = false;
			}

			if (profesor.getPsswd() != null) {
				DAOUtils.addClause(queryString, first, " p.psswd = ? ");
				first = false;
			}

			if (profesor.getIdPais() != null) {
				DAOUtils.addClause(queryString, first, " p.id_pais = ?");
				first = false;
			}

			if (profesor.getNombre() != null) {
				DAOUtils.addClause(queryString, first, " upper(p.nombre) LIKE upper(?) ");
				first = false;
			}

			if (profesor.getApellido1() != null) {
				DAOUtils.addClause(queryString, first, " upper(p.apellido1) LIKE upper(?) ");
				first = false;
			}

			if (profesor.getApellido2() != null) {
				DAOUtils.addClause(queryString, first, " upper(p.apellido2) LIKE upper(?) ");
				first = false;
			}

			if (profesor.getAnoNacimiento() != null) {
				DAOUtils.addClause(queryString, first, " p.ano_nacimiento = ? ");
				first = false;
			}

			if (profesor.getFechaSubscripcion() != null) {
				DAOUtils.addClause(queryString, first, " p.fecha_subscripcion = ?" );
				first = false;
			}

			if (profesor.getPrecioSesion() != null) {
				DAOUtils.addClause(queryString, first, " p.precio_sesion > ? ");
				first = false;
			}

			if (profesor.getPrecioSesionHasta() != null) {
				DAOUtils.addClause(queryString, first, " p.precio_sesion < ? ");
				first = false;
			}

			if (profesor.getIdIdioma() != null) {
				DAOUtils.addClause(queryString, first, " p.id_idioma = ? ");
				first = false;
			}

			if (profesor.getIdGenero() != null) {
				DAOUtils.addClause(queryString, first, " p.id_genero = ? ");
				first = false;
			}

			if (profesor.getIdNivel() != null) {
				DAOUtils.addClause(queryString, first, " p.id_nivel = ? ");
				first = false;
			}

			if (profesor.getAceptado() != null) {
				DAOUtils.addClause(queryString, first, " p.activada = ? ");
			}

			if (profesor.getDescripcion() != null) {
				DAOUtils.addClause(queryString, first, " p.descripcion = ? ");
			}

			if (profesor.getPuntuacion() != null) {
				DAOUtils.addClause(queryString, first, " (SELECT AVG(puntuacion) FROM estudiante_puntua_profesor puntu where puntu.id_profesor = p.id_profesor) >= ? ");
			}

			if (profesor.getDiaSesion() != null) {
				DAOUtils.addClause(queryString, first, " h.id_dia = ? ");
			}

			queryString.append(" group By p.id_profesor"
					+ " order by punt.puntuacion desc ");
			if(logger.isDebugEnabled()) logger.debug(queryString);
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
				preparedStatement.setString(i++,"%" + profesor.getNombre() + "%");
			if (profesor.getApellido1()!=null) 
				preparedStatement.setString(i++,"%" + profesor.getApellido1() + "%");
			if (profesor.getApellido2()!=null) 
				preparedStatement.setString(i++,"%" + profesor.getApellido2() + "%");
			if (profesor.getAnoNacimiento() != null)
				preparedStatement.setInt(i++, profesor.getAnoNacimiento());
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
			if (profesor.getPuntuacion() != null)
				preparedStatement.setDouble(i++, profesor.getPuntuacion());
			if(profesor.getDiaSesion() != null)
				preparedStatement.setInt(i++, profesor.getDiaSesion());


			resultSet = preparedStatement.executeQuery();

			List<Profesor> profesores = new ArrayList<Profesor>();                        
			Profesor p = null;
			int currentCount = 0;

			if((startIndex >= 1) && resultSet.absolute(startIndex)) {
				do {
					p = loadNext(connection, resultSet);						
					profesores.add(p);
					currentCount++;
				}while((currentCount < count) && resultSet.next());
			}

			int totalRows = JDBCUtils.getTotalRows(resultSet);
			
			if(logger.isDebugEnabled()) logger.debug("Total rows: " + totalRows);
			
			Results<Profesor> results = new Results<Profesor>(profesores, startIndex, totalRows);
			
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
	public Profesor create(Connection c, Profesor p) 
			throws DuplicateInstanceException, DataException {
		if(logger.isDebugEnabled()) logger.debug("Profesor = email: {}; idPais: {}; psswd: {}; nombre: {}; apellido1: {}; apellido2: {}; fecha_subscripcion: {}; precio_sesion: {}; id_idioma: {}; id_genero: {}; id_nivel: {}; descripcion: {}",
				p.getEmail(), p.getIdPais(), p.getPsswd()==null, p.getNombre(), p.getApellido1(), p.getApellido2(), p.getFechaSubscripcion(), p.getPrecioSesion(), p.getIdIdioma(), p.getIdGenero(), p.getIdNivel(), p.getDescripcion()); 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			// Creamos el preparedstatement
			String queryString = "INSERT INTO PROFESOR (EMAIL, ID_PAIS, PSSWD, NOMBRE, APELLIDO1, APELLIDO2, ANO_NACIMIENTO, FECHA_SUBSCRIPCION,PRECIO_SESION, ID_IDIOMA, ID_GENERO, ID_NIVEL, ACTIVADA, DESCRIPCION) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			if(logger.isDebugEnabled()) logger.debug(queryString);
			preparedStatement = c.prepareStatement(queryString,
					Statement.RETURN_GENERATED_KEYS);

			// Rellenamos el "preparedStatement"
			int i = 1;    

			preparedStatement.setString(i++,p.getEmail() );
			preparedStatement.setString(i++, p.getIdPais());
			preparedStatement.setString(i++, PasswordEncryptionUtil.encryptPassword(p.getPsswd()));
			preparedStatement.setString(i++,p.getNombre());
			preparedStatement.setString(i++,p.getApellido1());
			preparedStatement.setString(i++,p.getApellido2());
			preparedStatement.setInt(i++, p.getAnoNacimiento());
			preparedStatement.setDate(i++, new java.sql.Date (new Date().getTime()));
			preparedStatement.setDouble(i++, p.getPrecioSesion());
			preparedStatement.setString(i++, p.getIdIdioma());
			preparedStatement.setString(i++, p.getIdGenero());
			preparedStatement.setInt(i++, p.getIdNivel());
			preparedStatement.setInt(i++, 0);
			preparedStatement.setString(i++, p.getDescripcion());

			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				if(logger.isDebugEnabled()) logger.debug("Can not add row to table 'Profesor'");
			}

			// Recuperamos la PK generada
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Integer pk = resultSet.getInt(1); 
				p.setIdProfesor(pk);;
			} else {
				if(logger.isDebugEnabled()) logger.debug("Unable to fetch autogenerated primary key");
			}

			// Return the DTO
			return p;

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public void update(Connection c, Profesor p) 
			throws InstanceNotFoundException, DataException {
		if(logger.isDebugEnabled()) logger.debug("Profesor: {}", p);
		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;
		try {	

			queryString = new StringBuilder(
					" UPDATE PROFESOR" 
					);


			boolean first = true;

			if (p.getIdProfesor() != null) {
				DAOUtils.addUpdate(queryString, first, " ID_PROFESOR =  ? ");
				first = false;
			}	

			if (p.getEmail() != null) {
				DAOUtils.addUpdate(queryString, first, " email = ? ");
				first = false;
			}

			if (p.getPsswd() != null) {
				DAOUtils.addUpdate(queryString, first, " psswd = ? ");
				first = false;
			}

			if (p.getIdPais() != null) {
				DAOUtils.addUpdate(queryString, first, "id_pais = ?");
				first = false;
			}

			if (p.getNombre() != null) {
				DAOUtils.addUpdate(queryString, first, " nombre = ? ");
				first = false;
			}

			if (p.getApellido1() != null) {
				DAOUtils.addUpdate(queryString, first, " apellido1 = ? ");
				first = false;
			}

			if (p.getApellido2() != null) {
				DAOUtils.addUpdate(queryString, first, " apellido2 = ? ");
				first = false;
			}

			if (p.getAnoNacimiento() != null) {
				DAOUtils.addUpdate(queryString, first, " ano_nacimiento = ? ");
				first = false;
			}

			if (p.getFechaSubscripcion() != null) {
				DAOUtils.addUpdate(queryString, first, " fecha_subscripcion = ? ");
				first = false;
			}

			if (p.getPrecioSesion() != null) {
				DAOUtils.addUpdate(queryString, first, " precio_sesion = ? ");
				first = false;
			}

			if (p.getIdIdioma() != null) {
				DAOUtils.addUpdate(queryString, first, " id_idioma = ? ");
				first = false;
			}

			if (p.getIdGenero() != null) {
				DAOUtils.addUpdate(queryString, first, " id_genero = ? ");
				first = false;
			}

			if (p.getIdNivel() != null) {
				DAOUtils.addUpdate(queryString, first, " id_nivel = ? ");
				first = false;
			}

			if (p.getAceptado() != null) {
				DAOUtils.addUpdate(queryString, first, " activada = ? ");
				first = false;
			}

			if (p.getDescripcion() != null) {
				DAOUtils.addUpdate(queryString, first, " descripcion = ? ");
				first = false;
			}

			if(p.getCodigoDeRecuperacion() != null) {
				DAOUtils.addUpdate(queryString, first, " codigo_de_recuperacion = ? ");
				first = false;
			}


			queryString.append(" WHERE id_profesor = ? ");
			if(logger.isDebugEnabled()) logger.debug(queryString);
			preparedStatement = c.prepareStatement(queryString.toString());


			int i = 1;
			if (p.getIdProfesor() != null)
				preparedStatement.setInt(i++, p.getIdProfesor());
			if (p.getEmail()!=null) 
				preparedStatement.setString(i++,p.getEmail() );
			if (p.getPsswd()!=null) 
				preparedStatement.setString(i++, PasswordEncryptionUtil.encryptPassword(p.getPsswd()));
			if (p.getIdPais() != null)
				preparedStatement.setString(i++, p.getIdPais());
			if (p.getNombre()!=null)
				preparedStatement.setString(i++,p.getNombre());
			if (p.getApellido1()!=null) 
				preparedStatement.setString(i++,p.getApellido1());
			if (p.getApellido2()!=null) 
				preparedStatement.setString(i++,p.getApellido1());
			if (p.getAnoNacimiento() != null)
				preparedStatement.setInt(i++, p.getAnoNacimiento());
			if (p.getFechaSubscripcion() != null)
				preparedStatement.setDate(i++, new java.sql.Date(new Date().getTime()));
			if (p.getPrecioSesion() != null) 
				preparedStatement.setDouble(i++, p.getPrecioSesion());
			if (p.getIdIdioma() != null)
				preparedStatement.setString(i++, p.getIdIdioma());
			if (p.getIdGenero() != null)
				preparedStatement.setString(i++, p.getIdGenero());
			if (p.getIdNivel() != null)
				preparedStatement.setInt(i++, p.getIdNivel());
			if (p.getAceptado() != null)
				preparedStatement.setInt(i++, p.getAceptado());
			if (p.getDescripcion() != null)
				preparedStatement.setString(i++, p.getDescripcion());
			if(p.getCodigoDeRecuperacion() != null)
				preparedStatement.setInt(i++, p.getCodigoDeRecuperacion());

			preparedStatement.setInt(i++, findByEmail(c, p.getEmail()).getIdProfesor());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				if(logger.isDebugEnabled()) logger.debug("Non se actualizou o profesor");
			}

			if (updatedRows > 1) {
				if(logger.isDebugEnabled()) logger.debug("Duplicate row for id = {} in table 'Profesor'",
						p.getIdProfesor());
			}     

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);    
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	private Profesor loadNext(Connection connection, ResultSet resultSet) throws SQLException, DataException {

		Profesor p = new Profesor();
		int i = 1;
		Double puntuacion = 0.0d;

		Integer id = resultSet.getInt(i++);
		String email = resultSet.getString(i++);
		String contra = resultSet.getString(i++);
		String idPais = resultSet.getString(i++);
		String nombre = resultSet.getString(i++);
		String apellido1 = resultSet.getString(i++);	
		String apellido2 = resultSet.getString(i++);
		Integer anoNacimiento = resultSet.getInt(i++);
		Date fechaSubscripcion = resultSet.getDate(i++);
		Double precioSesion = resultSet.getDouble(i++);
		String idIdioma = resultSet.getString(i++);
		String idGenero = resultSet.getString(i++);
		Integer idNivel = resultSet.getInt(i++);
		Integer activada = resultSet.getInt(i++);
		String descripcion = resultSet.getString(i++);
		Integer codigoDeRecuperacion = resultSet.getInt(i++);


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
		p.setCodigoDeRecuperacion(codigoDeRecuperacion);

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		String queryString = "SELECT AVG(PUNTUACION) "
				+ " FROM ESTUDIANTE_PUNTUA_PROFESOR"
				+ " WHERE ID_PROFESOR = " +p.getIdProfesor();
		if(logger.isDebugEnabled()) logger.debug(queryString);
		preparedStatement = connection.prepareStatement(queryString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = preparedStatement.executeQuery();
		if (rs.next()) {
			puntuacion = rs.getDouble(1);
		}

		p.setPuntuacion(puntuacion);


		return p;

	}

}
