package com.educorp.eduinteractive.ecommerce.service.spi;

import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.MailException;
import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.model.Profesor;
import com.educorp.eduinteractive.ecommerce.service.criteria.ProfesorCriteria;

public interface ProfesorService {

	public List<Profesor> findByCriteria(ProfesorCriteria criteria)
			throws DataException;

	public Profesor findById(Integer id)
			throws DataException;

	public Profesor login(String email, String psswd)
			throws DataException;

	public Profesor signUp(Profesor e)
			throws DuplicateInstanceException, MailException, DataException;

	public Profesor update(Profesor e)
			throws DataException;

	public Profesor findByEmailToRecovery (String email) 
			throws MailException, DataException;

	public void setCodigo (Profesor e) 
			throws MailException, DataException;

	public void cambiarContra (Integer codigo, String email, String psswd) 
			throws DataException;

	public void puntuarEstudiante (Profesor p, Estudiante e, double puntuacion) 
			throws DataException;

}
