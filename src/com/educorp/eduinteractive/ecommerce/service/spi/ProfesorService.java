package com.educorp.eduinteractive.ecommerce.service.spi;

import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.service.criteria.EstudianteCriteria;

public interface ProfesorService {

	public List<Estudiante> findByCriteria(EstudianteCriteria criteria)
			throws DataException;
		
		public Estudiante findById(Integer id)
			throws DataException;
		
		public Estudiante login(String email, String psswd)
			throws DataException;
		
		public Estudiante signUp(Estudiante e)
			throws DataException;
		
		public Estudiante update(Estudiante e)
			throws DataException;
	
}
