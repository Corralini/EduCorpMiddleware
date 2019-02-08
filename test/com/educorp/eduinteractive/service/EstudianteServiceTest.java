package com.educorp.eduinteractive.service;

import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.MailException;
import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.model.Profesor;
import com.educorp.eduinteractive.ecommerce.service.criteria.EstudianteCriteria;
import com.educorp.eduinteractive.ecommerce.service.impl.EstudianteServiceImpl;
import com.educorp.eduinteractive.ecommerce.service.spi.EstudianteService;

public class EstudianteServiceTest {

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
									findByIdTest(1);
//						loginTest("AmberReeves@jourrapide.com", "Me2eeT0aiBu");
//
//						Estudiante test = new Estudiante();
//						test.setEmail("JenniferMatthews@dayrep.com");
//						test.setIdPais("us");
//						test.setPsswd("lajdlkajsdla");
//						test.setNombre("Jennifer");
//						test.setApellido1("Matthews");
//						test.setAnoNacimiento(1995);
//						test.setIdGenero("M");
//						signUpTest(test, 7);
//
//			//			
//						Estudiante testNuevo = new Estudiante();
//						testNuevo.setEmail("acorralfdez@gmail.com");
//						testNuevo.setIdGenero("h");

//
//						updateTest(testNuevo);
//			//			cambiarContraTest(997414, "acorralfdez@gmail.com", "nueva");

			//			Profesor profesor = new Profesor();
			//			profesor.setIdProfesor(1);
			//			
			//			puntuarProfesorTest(profesor, test, 4.0d);
//
//						List<Estudiante> estudiantes =  new ArrayList<Estudiante>();
//						EstudianteCriteria criteria = new EstudianteCriteria();
////						
////						criteria.setPuntuacion(2.0d);
////			
////						
//						estudiantes = findByCriteriaTest(criteria);
//						
//						for (Estudiante e : estudiantes) {
//						}
//		
//		Integer aux = 9;
//		aux = (int) Math.round(aux - 0.01)/2;
		
	}

}
