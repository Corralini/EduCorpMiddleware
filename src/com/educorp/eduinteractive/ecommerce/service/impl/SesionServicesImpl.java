package com.educorp.eduinteractive.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.dao.impl.HoraDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.impl.HorarioDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.impl.ProfesorDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.impl.SesionDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.HoraDAO;
import com.educorp.eduinteractive.ecommerce.dao.spi.HorarioDAO;
import com.educorp.eduinteractive.ecommerce.dao.spi.ProfesorDAO;
import com.educorp.eduinteractive.ecommerce.dao.spi.SesionDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.MailException;
import com.educorp.eduinteractive.ecommerce.model.Horario;
import com.educorp.eduinteractive.ecommerce.model.Profesor;
import com.educorp.eduinteractive.ecommerce.model.Sesion;
import com.educorp.eduinteractive.ecommerce.service.spi.MailService;
import com.educorp.eduinteractive.ecommerce.service.spi.SesionServices;

public class SesionServicesImpl implements SesionServices{

	private Logger logger = LogManager.getLogger(SesionServicesImpl.class);
	
	private SesionDAO sesionDAO = null;
	private ProfesorDAO profesorDAO = null;
	private MailService mailService = null;

	public SesionServicesImpl() {
		sesionDAO = new SesionDAOImpl();
		profesorDAO = new ProfesorDAOImpl();
		mailService = new MailServiceImpl();
	}

	@Override
	public void create(Horario h, Date fecha, Integer idEstudiante) throws MailException, DuplicateInstanceException, DataException {
		if(logger.isDebugEnabled()) logger.debug("Horario: {}; fecha: {}; idEstudiante: {}", h, fecha, idEstudiante);
		Connection c = null;
		Sesion s = new Sesion();
		boolean commit = false;
		Horario mostrarHora = new Horario();
		HorarioDAO horarioDAO = new HorarioDAOImpl();
		HoraDAO horaDAO = new HoraDAOImpl();
		String hora = "";
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			
			s.setIdProfesor(h.getIdProfesor());
			s.setIdEstudiante(idEstudiante);
			s.setFechaSesion(fecha);
			s.setIdHorario(h.getIdHorario());
			s.setPrecio(profesorDAO.findById(c, h.getIdProfesor()).getPrecioSesion());
			s.setIdEstado("S");
			s.setFechaCambioEstado(new Date());
			
			
			
			sesionDAO.create(c, s);
			
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(fecha);
			
			mostrarHora = horarioDAO.findById(c, h.getIdHorario());
			hora = horaDAO.findById(c, mostrarHora.getIdHora()).getHora();
			
			Profesor profesor = profesorDAO.findById(c, h.getIdProfesor());

			String mssg = "Hola " +profesor.getNombre()
					+ " " + profesor.getApellido1()
					+ " hay un estudiante que desea realizar una sesion en la siguiente fecha: "  + "\n" 
					+ calendario.get(Calendar.DAY_OF_MONTH) + "/" + calendario.get(Calendar.MONTH) + "/" + calendario.get(Calendar.YEAR) + " " + hora  + "\n"                          
					+ " Por favor responda SI en caso de aceptar la sesion y en caso contrario responda NO.";


			mailService.sendEmail(profesorDAO.findById(c, h.getIdProfesor()).getEmail(), "Petición de sesion", mssg);

			commit = true;
			
		}catch(SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}

	}

	@Override
	public void cambiarEstado(Sesion s, String idEstado) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("Sesion: {}; idEstado: {}", s, idEstado);
		Connection c = null;
		boolean commit = false;
		try {
			c = ConnectionManager.getConnection();
			s.setIdEstado(idEstado.toUpperCase());
			s.setFechaCambioEstado(new Date());
			c.setAutoCommit(false);
			
			sesionDAO.update(c, s);
			
			commit = true;
		}catch(SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}

	}

	@Override
	public void empezarSesion(Sesion s) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("Sesion: {}", s);
		Connection c = null;
		boolean commit = false;
		try {
			if (s.getIdEstado().toUpperCase() == "A") {
				c = ConnectionManager.getConnection();
				s.setFechaInicio(new Date());
				
				c.setAutoCommit(false);
				
				sesionDAO.update(c, s);
				
				commit = true;
				// Simulacion de vidiollamada
				// TimeUnit.SECONDS.sleep(10);
			}else {
				cambiarEstado(s, "R");
			}
		}catch(SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}// catch (InterruptedException ie) {
			//     Thread.currentThread().interrupt();
		//}
			finally {
			JDBCUtils.closeConnection(c, commit);
		}

	}

	@Override
	public void terminarSesion(Sesion s) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("Sesion: {}", s);
		Connection c = null;
		boolean commit = false;
		try {
			c = ConnectionManager.getConnection();
			s.setFechaFin(new Date());
			s.setIdEstado("T");
			c.setAutoCommit(false);
			sesionDAO.update(c, s);
			commit = true;
		}catch(SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}

	}

	@Override
	public Sesion findById(Integer id) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("id Sesion: {}", id);
		Connection c = null;
		boolean commit = false;
		try {
			c = ConnectionManager.getConnection();
			commit = true;
			return sesionDAO.findById(c, id);
		}catch(SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		
	}

	@Override
	public List<Sesion> findByCalendario(Integer idEstudiante) throws DataException {
		if(logger.isDebugEnabled()) logger.debug("id Estudiante: {}", idEstudiante);
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			return sesionDAO.findByCalendario(c, idEstudiante);
		}catch(SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c);
		}
	}

}
