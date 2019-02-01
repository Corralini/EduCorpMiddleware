package com.educorp.eduinteractive.service;

import java.util.ArrayList;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.MailException;
import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.model.Profesor;
import com.educorp.eduinteractive.ecommerce.service.criteria.ProfesorCriteria;
import com.educorp.eduinteractive.ecommerce.service.impl.ProfesorServicesImpl;
import com.educorp.eduinteractive.ecommerce.service.spi.ProfesorService;

public class ProfesorServiceTest {

	public static List<Profesor> findByCriteriaTest(ProfesorCriteria criteria)
			throws DataException{
		ProfesorService profesorService = new ProfesorServicesImpl();

		return profesorService.findByCriteria(criteria);
	}

	public static Profesor findByIdTest(Integer id)
			throws DataException{
		ProfesorService profesorService = new ProfesorServicesImpl();
		System.out.println("" + profesorService.findById(id));
		return profesorService.findById(id);
	}

	public static Profesor login(String email, String psswd)
			throws DataException{
		ProfesorService profesorService = new ProfesorServicesImpl();

		return profesorService.login(email, psswd);
	}

	public static Profesor signUp(Profesor e)
			throws DuplicateInstanceException, MailException, DataException{
		ProfesorService profesorService = new ProfesorServicesImpl();

		return profesorService.signUp(e);
	}

	public static Profesor update(Profesor e)
			throws DataException{
		ProfesorService profesorService = new ProfesorServicesImpl();

		return profesorService.update(e);
	}

	public static Profesor findByEmailToRecovery (String email) 
			throws MailException, DataException{
		ProfesorService profesorService = new ProfesorServicesImpl();

		return profesorService.findByEmailToRecovery(email);
	}

	public static void cambiarContraTest (Integer codigo, String email, String psswd) 
			throws DataException{
		ProfesorService profesorService = new ProfesorServicesImpl();

		profesorService.cambiarContra(codigo, email, psswd);
	}

	public static void puntuarEstudianteTest (Profesor p, Estudiante e, double puntuacion) 
			throws DataException{
		ProfesorService profesorService = new ProfesorServicesImpl();

		profesorService.puntuarEstudiante(p, e, puntuacion);
	}
	
	public static void main(String[] args) throws DataException, MailException {
		
		List<Profesor> profesores = new ArrayList<Profesor>();
		ProfesorCriteria criteria = new ProfesorCriteria();
		
		criteria.setPrecioSesion(1.0d);
		criteria.setPuntuacion(3.25d);
		
		profesores = findByCriteriaTest(criteria);
		
		for (Profesor p: profesores) {
			System.out.println("" + p);
		}
		
//		findByIdTest(6);
		
		Profesor test = new Profesor();
		test.setIdProfesor(11);
		test.setEmail("acorralfdez@gmail.com");
		test.setPsswd("corralito");
		test.setIdPais("es");
		test.setNombre("Alejandro Ricardo");
		test.setApellido1("Corral");
		test.setApellido2("Fernández");
		test.setAnoNacimiento(2000);
		test.setPrecioSesion(5.5d);
		test.setIdIdioma("en");
		test.setIdNivel(2);
		test.setIdGenero("H");
		test.setDescripcion("You love me and you know it");
		
		signUp(test);
		
//		login("acorralfdez@gmail.com", "corralito");
		
//		Profesor test1 = new Profesor();
//		
//		test1.setEmail("acorralfdez@gmail.com");
//		test1.setPrecioSesion(7.5d);
//		
//		update(test1);
		
//		findByEmailToRecovery("acorralfdez@gmail.com");
//		cambiarContraTest(379067, "acorralfdez@gmail.com", "pericodelospalotes123.");
		
//		Estudiante e = new Estudiante ();
//		e.setIdEstudiante(3);
//		
//		puntuarEstudianteTest(test, e, 8.0d);
	}
	
}
