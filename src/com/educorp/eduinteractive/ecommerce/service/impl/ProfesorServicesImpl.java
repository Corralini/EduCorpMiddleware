package com.educorp.eduinteractive.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.dao.impl.ProfesorDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.impl.PuntuacionDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.service.Results;
import com.educorp.eduinteractive.ecommerce.dao.spi.ProfesorDAO;
import com.educorp.eduinteractive.ecommerce.dao.spi.PuntuacionDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.exceptions.MailException;
import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.model.Profesor;
import com.educorp.eduinteractive.ecommerce.model.Puntuacion;
import com.educorp.eduinteractive.ecommerce.service.criteria.ProfesorCriteria;
import com.educorp.eduinteractive.ecommerce.service.spi.MailService;
import com.educorp.eduinteractive.ecommerce.service.spi.ProfesorService;
import com.educorp.eduinteractive.ecommerce.service.spi.UsuariosUtils;
import com.educorp.eduinteractive.exceptions.PasswordEncryptionUtil;

public class ProfesorServicesImpl implements ProfesorService{
	
	private Logger logger = LogManager.getLogger(ProfesorServicesImpl.class);
	
	private ProfesorDAO profesorDAO = null;
	private PuntuacionDAO puntuacionDAO = null;
	private MailService mailService = new MailServiceImpl();

	public ProfesorServicesImpl () {
		profesorDAO = new ProfesorDAOImpl();
		puntuacionDAO = new PuntuacionDAOImpl();
	}

	@Override
	public Profesor findById(Integer id) 
			throws DataException {
		if(logger.isDebugEnabled()) logger.debug("id: {}", id);
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			return profesorDAO.findById(c, id);

		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {   
			JDBCUtils.closeConnection(c);
		}  	

	}

	@Override
	public Profesor login(String email, String psswd)
			throws DataException{
		if(logger.isDebugEnabled()) logger.debug("Email: {};Psswd: {}", email, psswd==null);
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			if (email == null || psswd == null) {
				return null;
			}

			Profesor p = profesorDAO.findByEmail(c, email);

			if(p==null) {
				return p;
			}

			if(PasswordEncryptionUtil.checkPassword(psswd, p.getPsswd())) {
				return p;
			}else {
				if(logger.isDebugEnabled()) logger.debug("Psswd o email erroneos");
				throw new DataException("Hemos detetado un problema, comprueba los datos introducidos");
			}

		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}
		finally {
			JDBCUtils.closeConnection(c);
		}
	}

	@Override
	public Profesor signUp(Profesor e)
			throws DuplicateInstanceException, MailException, DataException {
		if(logger.isDebugEnabled()) logger.debug("Profesor = email: {}; idPais: {}; psswd: {}; nombre: {}; apellido1: {}; apellido2: {}; fecha_subscripcion: {}; precio_sesion: {}; id_idioma: {}; id_genero: {}; id_nivel: {}; descripcion: {}",
				e.getEmail(), e.getIdPais(), e.getPsswd()==null, e.getNombre(), e.getApellido1(), e.getApellido2(), e.getFechaSubscripcion(), e.getPrecioSesion(), e.getIdIdioma(), e.getIdGenero(), e.getIdNivel(), e.getDescripcion());
		boolean commit = false;
		Connection c = null;
		
		Calendar calendario = Calendar.getInstance();
		Integer ano = calendario.get(Calendar.YEAR);
		
		if (e.getAnoNacimiento() < 1900 || e.getAnoNacimiento() > ano) {
			if(logger.isDebugEnabled()) logger.debug("Ano introducido incorrecto");
			throw new DataException("O ano de nacemento non e válido, introduce un ano maior que 1900");
		}
		
		if (checkEmail(e.getEmail())) {
			if(logger.isDebugEnabled()) logger.debug("Email incorrecto");
			throw new DataException("Email incorrecto");
		}

		try {
			c = ConnectionManager.getConnection();

			c.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			c.setAutoCommit(false);
			
			String mssg = "";
			
			if(e.getApellido2() == null) {
			
			mssg = "Hola " + e.getNombre()
			+ " " + e.getApellido1()
			+ " el equipo de Educorp Interactive le da la bienvenida a Educorp ";
			}else {
			mssg = "Hola " + e.getNombre()
			+ " " + e.getApellido1() + " " + e.getApellido2()
			+ " el equipo de Educorp Interactive le da la bienvenida a Educorp ";
			}
			e.setAceptado(0);
			
			Profesor result = profesorDAO.create(c, e);


			mailService.sendEmail(e.getEmail(), "Bienvenido a Educorp", mssg);

			commit = true;

			return result;

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);

		} finally {
			JDBCUtils.closeConnection(c, commit);
		}



	}

	@Override
	public Profesor update(Profesor e)
			throws InstanceNotFoundException, DataException {
		if(logger.isDebugEnabled()) logger.debug("Profesor = email: {}; idPais: {}; psswd: {}; nombre: {}; apellido1: {}; apellido2: {}; fecha_subscripcion: {}; precio_sesion: {}; id_idioma: {}; id_genero: {}; id_nivel: {}; descripcion: {}",
				e.getEmail(), e.getIdPais(), e.getPsswd()==null, e.getNombre(), e.getApellido1(), e.getApellido2(), e.getFechaSubscripcion(), e.getPrecioSesion(), e.getIdIdioma(), e.getIdGenero(), e.getIdNivel(), e.getDescripcion());
		Connection connection = null;
		boolean commit = false;

		try {

			connection = ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			connection.setAutoCommit(false);

			profesorDAO.update(connection, e);
			commit = true;

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);

		} finally {
			JDBCUtils.closeConnection(connection, commit);
		}
		return null;
	}

	@Override
	public Results<Profesor> findByCriteria(ProfesorCriteria criteria, int startIndex, int count)
			throws DataException {
		if(logger.isDebugEnabled()) logger.debug("Criteria: {}", criteria);
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);
			Results<Profesor> profesores = profesorDAO.findByCriteria(connection, criteria, startIndex, count);
			return profesores;

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public Profesor findByEmailToRecovery(String email) 
			throws MailException, DataException {
		if(logger.isDebugEnabled()) logger.debug("Email: {}", email);
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			Profesor e = new Profesor();
			e = profesorDAO.findByEmail(c, email);
			setCodigo(e);
			return e;

		}catch (SQLException ex) {
			logger.warn("Error en los datos");
			throw new DataException("Hemos encontrado algún problema, por favor comprueba los datos");
		} finally {   
			JDBCUtils.closeConnection(c);
		} 
	}

	public void setCodigo (Profesor e) 
			throws MailException, DataException{
		if(logger.isDebugEnabled()) logger.debug("Codigo: {}", e.getCodigoDeRecuperacion());
		Connection c = null;
		boolean commit = false;
		try {
			c = ConnectionManager.getConnection();
			e.setCodigoDeRecuperacion(UsuariosUtils.codRecuperacion());

			String mssg = "Hola "
			+ " Introduce este código para poder cambiar tu contraseña: " + e.getCodigoDeRecuperacion();

			c.setAutoCommit(false);
			profesorDAO.update(c, e);
			mailService.sendEmail(e.getEmail(), "Restablecer contraseña", mssg);
			commit = true;
		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void cambiarContra(Integer codigo, String email, String psswd) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("codigo: {}; email: {}; psswd: {}", codigo, email, psswd==null);
		Connection c = null;
		boolean commit = false;
		try {
		c = ConnectionManager.getConnection();
		Profesor p = new Profesor();
		Profesor cambio = new Profesor();
		
		ProfesorDAO estudianteDAO = new ProfesorDAOImpl();
		p = estudianteDAO.findByEmail(c, email);
		c.setAutoCommit(false);
			if(codigo.equals(p.getCodigoDeRecuperacion())) {
				cambio.setEmail(p.getEmail());
				cambio.setPsswd(psswd);
				cambio.setCodigoDeRecuperacion(0);
				
				estudianteDAO.update(c, cambio);
				commit = true;
			}else {
				if(logger.isDebugEnabled()) logger.debug("Error en el código");
				throw new DataException("El código introducido no coincide, compruebe el código");
			}
		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}
	

	@Override
	public void puntuarEstudiante(Profesor p, Estudiante e, double puntuacion) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("Profesor: {}; Estudiante: {}; Puntuacion: {}", p.getIdProfesor(), e.getIdEstudiante(), puntuacion); 
		Connection c = null;
		boolean commit = false;
		try {
			c = ConnectionManager.getConnection();
			Puntuacion punt = new Puntuacion();
			punt.setIdProfesor(p.getIdProfesor());
			punt.setIdEstudiante(e.getIdEstudiante());
			punt.setFechaPuntuacion(new Date());
			punt.setPuntuacion(puntuacion);
			c.setAutoCommit(false);
			puntuacionDAO.createPuntuacionEstudiante(c, punt);
			commit = true;
		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException (ex);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}
	
	public static boolean checkEmail (String email) {

		boolean result = true;
		
		String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
				"[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
		Pattern pattern = Pattern.compile(emailPattern);
		if (email != null) {
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				result = false;
			}
		}
		
		return result;

	}
	
}
