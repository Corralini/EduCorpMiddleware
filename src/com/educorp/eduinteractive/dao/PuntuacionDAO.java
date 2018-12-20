package com.educorp.eduinteractive.dao;

import java.util.List;

import com.educorp.eduinteractive.model.Puntuacion;

public interface PuntuacionDAO {

	public List<Puntuacion> findByIdProfesor (Integer id)
			throws Exception;
	
	public Puntuacion create (Puntuacion punt)
			throws Exception;
}
