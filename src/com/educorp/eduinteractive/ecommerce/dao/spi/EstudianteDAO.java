package com.educorp.eduinteractive.ecommerce.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.service.EstudianteCriteria;

public interface EstudianteDAO {

	public Estudiante findById (Connection c, Integer id)
		throws  DataException;

	public List<Estudiante> findByNombre (Connection c, String nombre)
			throws DataException;
	
	public List<Estudiante> findByCriteria (Connection c, EstudianteCriteria criteria)
		throws DataException;
	
	public Estudiante findByEmail (Connection c, String email)
		throws  DataException;
	
	public Boolean exists(Connection connection, String email) 
    		throws DataException;
	
	public Estudiante create (Connection c, Estudiante e)
		throws  DataException;
	
	public void update (Connection c, Estudiante e)
		throws  DataException;
	
}
