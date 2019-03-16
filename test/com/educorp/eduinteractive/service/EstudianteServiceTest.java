package com.educorp.eduinteractive.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.MailException;
import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.model.Profesor;
import com.educorp.eduinteractive.ecommerce.service.criteria.EstudianteCriteria;
import com.educorp.eduinteractive.ecommerce.service.impl.EstudianteServiceImpl;
import com.educorp.eduinteractive.ecommerce.service.spi.EstudianteService;

public class EstudianteServiceTest {

	private Logger logger = LogManager.getLogger(EstudianteServiceTest.class);
	
	public static List<Estudiante> findByCriteriaTest(EstudianteCriteria criteria)
			throws DataException{
		EstudianteService estudianteService = new EstudianteServiceImpl();
		return estudianteService.findByCriteria(criteria);
	}

	public static Estudiante findByIdTest (Integer id)
			throws DataException{
		EstudianteService estudianteService = new EstudianteServiceImpl();
		return estudianteService.findById(id);

	}

	public static Estudiante loginTest (String email, String psswd)
			throws DataException{
		EstudianteService estudianteService = new EstudianteServiceImpl();
		return estudianteService.login(email, psswd);
	}

	public static Estudiante signUpTest (Estudiante e, Integer acertadas)
			throws DuplicateInstanceException, MailException, DataException{
		EstudianteService estudianteService = new EstudianteServiceImpl();
		return estudianteService.signUp(e, acertadas);
	}

	public static void updateTest(Estudiante e)
			throws DataException{
		EstudianteService estudianteService = new EstudianteServiceImpl();

		estudianteService.update(e);
	}

	public static Estudiante findByEmailToRecoveryTest (String email) 
			throws MailException, DataException{
		EstudianteService estudianteService = new EstudianteServiceImpl();

		return estudianteService.findByEmailToRecovery(email);
	}


	public static void cambiarContraTest (int codigo, String email, String psswd) 
			throws DataException{
		EstudianteService estudianteService = new EstudianteServiceImpl();

		estudianteService.cambiarContra(codigo, email, psswd);
	}


	public static void puntuarProfesorTest (Profesor p, Estudiante e, double puntuacion) 
			throws DataException{

		EstudianteService estudianteService = new EstudianteServiceImpl();

		estudianteService.puntuarProfesor(p, e, puntuacion);
	}

	public static void main(String[] args) throws MailException, DuplicateInstanceException, DataException {

		Estudiante e = loginTest("acorralfdez@gmail.com", "Alejandro22.");
	
		
	}

}
