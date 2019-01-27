package com.educorp.eduinteractive.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Puntuacion;

public interface PuntuacionDAO {

	public List<Puntuacion> findByIdProfesorPuntua (Connection connection, Integer id)
			throws DataException;
	
	public List<Puntuacion> findByIdProfesorPuntuado (Connection connection, Integer id)
			throws DataException;
	
	public Puntuacion createPuntuacionByProfesor (Connection connection, Puntuacion punt)
			throws  Exception;
	
	public Puntuacion createPuntuacionByEstudiante (Connection connection, Puntuacion punt)
			throws Exception;
}
