package com.educorp.eduinteractive.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.dao.impl.EstudianteDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.impl.PuntuacionDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.EstudianteDAO;
import com.educorp.eduinteractive.ecommerce.dao.spi.PuntuacionDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.exceptions.MailException;
import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.model.Profesor;
import com.educorp.eduinteractive.ecommerce.model.Puntuacion;
import com.educorp.eduinteractive.ecommerce.service.criteria.EstudianteCriteria;
import com.educorp.eduinteractive.ecommerce.service.spi.EstudianteService;
import com.educorp.eduinteractive.ecommerce.service.spi.MailService;
import com.educorp.eduinteractive.ecommerce.service.spi.UsuariosUtils;
import com.educorp.eduinteractive.exceptions.PasswordEncryptionUtil;

public class EstudianteServiceImpl implements EstudianteService{

	private Logger logger = LogManager.getLogger(EstudianteServiceImpl.class);
	
	private EstudianteDAO estudianteDAO = null;
	private PuntuacionDAO puntuacionDAO = null;
	private MailService mailService = new MailServiceImpl();

	public EstudianteServiceImpl () {
		estudianteDAO = new EstudianteDAOImpl();
		puntuacionDAO = new PuntuacionDAOImpl();
	}

	@Override
	public Estudiante findById(Integer id) 
			throws DataException {
		if(logger.isDebugEnabled()) logger.debug("id: {}", id);
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			return estudianteDAO.findById(c, id);

		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {   
			JDBCUtils.closeConnection(c);
		}  	

	}
	
	

	@Override
	public Estudiante login(String email, String psswd)
			throws DataException{
		if(logger.isDebugEnabled()) logger.debug("email: {}; psswd: {}", email, psswd=null);
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			if (email == null || psswd == null) {
				return null;
			}

			Estudiante e = estudianteDAO.findByEmail(c, email);

			if(e==null) {
				return e;
			}

			if(PasswordEncryptionUtil.checkPassword(psswd, e.getPsswd())) {
				return e;
			}else {
				if(logger.isDebugEnabled()) logger.debug("Psswd inorrecta");
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
	public Estudiante signUp(Estudiante e, Integer acertadas)
			throws DuplicateInstanceException, MailException, DataException {
		if(logger.isDebugEnabled()) logger.debug("Estudiante = email: {}; id_pais: {}; pssw: {}; nombre: {}; apellido1: {}; apellido2: {}; ano_nacimiento: {}; id_genero: {}; acertadas= {}",
				e.getEmail(), e.getIdPais(), e.getPsswd()==null, e.getNombre(), e.getApellido1(), e.getApellido2(), e.getAnoNacimiento(), e.getIdGenero(), acertadas);
		boolean commit = false;
		Connection c = null;
		Calendar calendario = Calendar.getInstance();
		Integer ano = calendario.get(Calendar.YEAR);


		if (e.getAnoNacimiento() < 1900 || e.getAnoNacimiento() > ano) {
			if(logger.isDebugEnabled()) logger.debug("Ano introducido incorrecto");
			throw new DataException("O ano de nacemento non e v�lido, introduce un ano maior que 1900");
		}

		if (acertadas > 0 || acertadas < 11) {

			acertadas = (int) Math.round(acertadas - 0.01)/2;
			if (acertadas == 0) {
				acertadas = 1;
			}
			e.setIdNivel(acertadas);
		}else {
			if(logger.isDebugEnabled()) logger.debug("Error en el test");
			throw new DataException("Hemos tenido alg�n problema con el test");
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

			Estudiante result = estudianteDAO.create(c, e);


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
	public void update(Estudiante e)
			throws InstanceNotFoundException, DataException {
		if(logger.isDebugEnabled()) logger.debug("Estudiante = email: {}; id_pais: {}; pssw: {}; nombre: {}; apellido1: {}; apellido2: {}; ano_nacimiento: {}; id_genero: {}",
				e.getEmail(), e.getIdPais(), e.getPsswd()==null, e.getNombre(), e.getApellido1(), e.getApellido2(), e.getAnoNacimiento(), e.getIdGenero());
		Connection connection = null;
		boolean commit = false;

		try {

			connection = ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			connection.setAutoCommit(false);

			estudianteDAO.update(connection, e);
			commit = true;

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);

		} finally {
			JDBCUtils.closeConnection(connection, commit);
		}
	}

	@Override
	public List<Estudiante> findByCriteria(EstudianteCriteria criteria)
			throws DataException {
		if(logger.isDebugEnabled()) logger.debug("Criteria: {}" , criteria);
		Connection connection = null;

		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return estudianteDAO.findByCriteria(connection, criteria);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public Estudiante findByEmailToRecovery(String email) 
			throws MailException, DataException {
		if(logger.isDebugEnabled()) logger.debug("Email: {}", email);
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			Estudiante e = new Estudiante();
			e = estudianteDAO.findByEmail(c, email);
			Estudiante estudianteCodigo = new Estudiante();
			estudianteCodigo.setEmail(e.getEmail());
			setCodigo(estudianteCodigo);
			return e;

		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException("Hemos encontrado alg�n problema, por favor comprueba los datos");
		} finally {   
			JDBCUtils.closeConnection(c);
		} 
	}

	public void setCodigo (Estudiante e) 
			throws MailException, DataException{
		if(logger.isDebugEnabled()) logger.debug("Codigo: {}", e.getCodigoDeRecuperacion());
		Connection c = null;
		boolean commit = false;
		try {
			c = ConnectionManager.getConnection();
			e.setCodigoDeRecuperacion(UsuariosUtils.codRecuperacion());

			String mssg = "Hola "
					+ " Introduce este c�digo para poder cambiar tu contrase�a: " + e.getCodigoDeRecuperacion();

			c.setAutoCommit(false);
			estudianteDAO.update(c, e);
			mailService.sendEmail(e.getEmail(), "Restablecer contrase�a", mssg);
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
		if(logger.isDebugEnabled()) logger.debug("Codigo: {}; Email: {}; psswd: {}", codigo, email, psswd == null);
		Connection c = null;
		boolean commit = false;
		try {
			c = ConnectionManager.getConnection();
			Estudiante e = new Estudiante();
			Estudiante cambio = new Estudiante();

			EstudianteDAO estudianteDAO = new EstudianteDAOImpl();

			e = estudianteDAO.findByEmail(c, email);
			c.setAutoCommit(false);
			if(codigo.equals(e.getCodigoDeRecuperacion())) {
				cambio.setEmail(e.getEmail());
				cambio.setPsswd(psswd);
				cambio.setCodigoDeRecuperacion(0);

				estudianteDAO.update(c, cambio);
				commit = true;
			}else {
				if(logger.isDebugEnabled()) logger.debug("El codigo no coincide");
				throw new DataException("El c�digo introducido no coincide, compruebe el c�digo");
			}
		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}


	@Override
	public void puntuarProfesor(Profesor p, Estudiante e, double puntuacion) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("idProfesor: {}; idEstudiante: {}; puntuacion: {}", p.getIdProfesor(), e.getIdEstudiante(), puntuacion); 
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
			puntuacionDAO.createPuntuacionProfesor(c, punt);
			commit = true;
		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException (ex);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
