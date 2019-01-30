package com.educorp.eduinteractive.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.impl.HorarioDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.HorarioDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.model.Horario;
import com.educorp.eduinteractive.ecommerce.service.spi.HorarioService;

public class HorarioServicesImpl implements HorarioService{

	private HorarioDAO horarioDAO = null;
	
	public HorarioServicesImpl() {
		horarioDAO = new HorarioDAOImpl();
	}
	
	@Override
	public void create(Horario h) throws DuplicateInstanceException, DataException {
		Connection c = null;
		boolean commit = false;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			horarioDAO.create(c, h);
			commit = true;
		}catch(SQLException ex) {
			throw new DataException(ex);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		
	}

	@Override
	public Horario findById(Integer id) throws DataException {
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			return horarioDAO.findById(c, id);

		}catch (SQLException ex) {
			throw new DataException(ex);
		} finally {   
			JDBCUtils.closeConnection(c);
		} 
	}

	@Override
	public List<Horario> findByFecha(Integer idProfesor, Date fecha) throws DataException {
		Connection connection = null;

		try {
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(fecha);
			int dia = calendario.get(Calendar.DAY_OF_WEEK);
			
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);
			
			return horarioDAO.findBy(connection, idProfesor, dia);

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	
	
}
