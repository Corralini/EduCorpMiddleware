package com.educorp.eduinteractive.dao;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.model.Estudiante;
import com.educorp.eduinteractive.service.EstudianteCriteria;

public interface EstudianteDAO {

	public Estudiante findById (Connection c, Integer id)
		throws Exception;

	public List<Estudiante> findByNombre (Connection c, String nombre)
			throws Exception;
	
	public List<Estudiante> findByCriteria (Connection c, EstudianteCriteria criteria)
		throws Exception;
	
	public Estudiante findByEmail (Connection c, String email)
		throws Exception;
	
	public Estudiante create (Connection c, Estudiante e)
		throws Exception;
	
	public void update (Connection c, Estudiante e)
		throws Exception;
	
}
