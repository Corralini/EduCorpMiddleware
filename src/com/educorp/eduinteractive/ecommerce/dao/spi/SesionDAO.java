package com.educorp.eduinteractive.ecommerce.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Sesion;

public interface SesionDAO {

	public Sesion findById (Connection connection, Integer id)
		throws InstanceNotFoundException, DataException;
	
	public List <Sesion> findByCalendario (Connection connection, Integer idUsuario, boolean isProfesor)
		throws DataException;
	
	public List<Sesion> findByEstado (Connection connection, String idEstado)
		throws DataException;
	
	public Sesion create (Connection connection, Sesion s)
		throws DuplicateInstanceException, DataException;
	
	public void update (Connection connection, Sesion s)
		throws InstanceNotFoundException, DataException;
}
