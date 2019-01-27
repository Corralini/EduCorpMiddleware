package com.educorp.eduinteractive.ecommerce.service;

import com.educorp.eduinteractive.ecommerce.model.Estudiante;

public interface EstudianteService {
	
//	public List<Estudiante> findByCriteria(EstudianteCriteria criteria)
//		throws Exception;
	
	public Estudiante findById(Integer id)
		throws Exception;
	
	public Estudiante login(String email, String psswd)
		throws Exception;
	
	public Estudiante signUp(Estudiante e)
		throws Exception;
	
	public Estudiante update(Estudiante e)
		throws Exception;
}
