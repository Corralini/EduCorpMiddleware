package com.educorp.eduinteractive.dao;

import java.util.List;

import com.educorp.eduinteractive.model.Estudiante;

public interface EstudianteDAO {

	public Estudiante findById (Integer id)
		throws Exception;

	public List<Estudiante> findByNombre (String nombre)
		throws Exception;
	
	public Estudiante create (Estudiante e)
		throws Exception;
	
	public Estudiante update (Estudiante e)
		throws Exception;
	
}
