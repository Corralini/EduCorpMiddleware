package com.educorp.eduinteractive.ecommerce.service.spi;

import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.MailException;
import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.model.Profesor;
import com.educorp.eduinteractive.ecommerce.service.criteria.EstudianteCriteria;

public interface EstudianteService {
	
	public List<Estudiante> findByCriteria(EstudianteCriteria criteria)
		throws DataException;
	
	public Estudiante findById(Integer id)
		throws DataException;
	
	public Estudiante login(String email, String psswd)
		throws DataException;
	
	public Estudiante signUp(Estudiante e, Integer acertadas)
		throws DuplicateInstanceException, MailException, DataException;
	
	public void update(Estudiante e)
		throws DataException;
	
	public Estudiante findByEmailToRecovery (String email) 
		throws MailException, DataException;
	
	public void setCodigo (Estudiante e) 
		throws MailException, DataException;
	
	public void cambiarContra (Integer codigo, String email, String psswd) 
			throws DataException;
	
	
	public void puntuarProfesor (Profesor p, Estudiante e, double puntuacion) 
			throws DataException;
}
