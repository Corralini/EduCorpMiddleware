package com.educorp.eduinteractive.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.impl.SesionDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.spi.PuntuacionDAO;
import com.educorp.eduinteractive.ecommerce.dao.spi.SesionDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Sesion;
import com.educorp.eduinteractive.ecommerce.service.spi.CalendarioServices;

public class CalendarioServicesImpl implements CalendarioServices{

	private SesionDAO sesionDAO = null;
	private PuntuacionDAO puntuacionDAO = null;

	public CalendarioServicesImpl () {
		sesionDAO = new SesionDAOImpl();

	}
	
	@Override
	public List<Sesion> findByUsuario(Integer idEstudiante) throws DataException {
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			return sesionDAO.findByCalendario(c, idEstudiante);
		}catch(SQLException ex) {
			throw new DataException(ex);
		}
		
	}

}