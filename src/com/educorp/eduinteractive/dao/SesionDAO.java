package com.educorp.eduinteractive.dao;

import java.util.List;

import com.educorp.eduinteractive.model.Sesion;

public interface SesionDAO {

	public Sesion findById (Integer id)
		throws Exception;
	
	// a hora de ordenalos ordenar por dia e hora
	public List <Sesion> findByCalendario (Integer idUsuario, Integer mes, Integer ano)
		throws Exception;
	
	public List<Sesion> findByEstado (Integer id)
		throws Exception;
	
	public 
}
