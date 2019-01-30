package com.educorp.eduinteractive.ecommerce.dao.spi;

import java.sql.Connection;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Puntuacion;

public interface PuntuacionDAO {

	public Puntuacion findEstudiantePuntuacion (Connection connection, Integer id)
			throws InstanceNotFoundException, DataException;
	
	public Puntuacion findProfesorPuntuacion (Connection connection, Integer id)
			throws InstanceNotFoundException, DataException;
	
	public Puntuacion createPuntuacionProfesor (Connection connection, Puntuacion punt)
			throws  DuplicateInstanceException, DataException;
	
	public Puntuacion createPuntuacionEstudiante (Connection connection, Puntuacion punt)
			throws DuplicateInstanceException, DataException;
}
