package com.educorp.eduinteractive.dao;

import java.util.List;

import com.educorp.eduinteractive.model.CriteriaProfesor;
import com.educorp.eduinteractive.model.Estudiante;
import com.educorp.eduinteractive.model.Profesor;

public interface ProfesorDAO {

	public Estudiante findById (Integer id)
			throws Exception;
		
		public List<Profesor> findByNombre (String Nombre)
			throws Exception;
		
		public List<Profesor> findByGenero (Integer pais)
			throws Exception;
		
		public List<Profesor> findByCriteria (CriteriaProfesor criteria)
		throws Exception;
		
		public Estudiante create (Profesor p)
			throws Exception;
		
		public Estudiante update (Profesor p)
			throws Exception;
	
}
