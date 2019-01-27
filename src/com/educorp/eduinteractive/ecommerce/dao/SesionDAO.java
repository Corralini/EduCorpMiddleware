package com.educorp.eduinteractive.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Sesion;

public interface SesionDAO {

	public Sesion findById (Connection connection, Integer id)
		throws DataException;
	
	// a hora de ordenalos ordenar por dia e hora
	public List <Sesion> findByCalendario (Connection connection, Integer idUsuario)
		throws DataException;
	
	public List<Sesion> findByEstado (Connection connection, String idEstado)
		throws DataException;
	
	public Sesion create (Connection connection, Sesion s)
		throws DataException;
	
	public void update (Connection connection, Sesion s)
		throws DataException;
}
