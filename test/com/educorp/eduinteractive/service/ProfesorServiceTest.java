package com.educorp.eduinteractive.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.dao.service.Results;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.MailException;
import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.model.Profesor;
import com.educorp.eduinteractive.ecommerce.service.criteria.ProfesorCriteria;
import com.educorp.eduinteractive.ecommerce.service.impl.ProfesorServicesImpl;
import com.educorp.eduinteractive.ecommerce.service.spi.ProfesorService;

public class ProfesorServiceTest {
	
	private static Logger logger = LogManager.getLogger(ProfesorServiceTest.class);

	public static void findByCriteriaTest()
			throws DataException{
		ProfesorService profesorService = new ProfesorServicesImpl();
		int pageSize = 2;
		ProfesorCriteria criteria = new ProfesorCriteria();
		criteria.setNombre("i");
		
		try {

			Results<Profesor> results = null;
			int startIndex = 1; 
			int i = 1;
			do {
				results = profesorService.findByCriteria(criteria, startIndex, pageSize);
				logger.info("Found "+results.getResultadosTotales()+" results.");				
				if (results.getResultados().size()>0) {
					logger.info("Page ["+startIndex+" - "+(startIndex+results.getResultados().size()-1)+"] : ");				
					for (Profesor p: results.getResultados()) {
						logger.info("Result "+i+": "+ p);
						i++;
					}
					startIndex = startIndex + pageSize;
				}
				
			} while (!(results.getResultados().size()<pageSize)); 		
						
		} catch (Throwable t) {
			logger.error(t.getMessage(), t);
		}
	}

	public static Profesor findByIdTest(Integer id)
			throws DataException{
		ProfesorService profesorService = new ProfesorServicesImpl();
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
		
		Profesor p = new Profesor();
		p.setNombre("Alejandro");
		p.setApellido1("Corral");
		p.setApellido2("Corral");
		p.setPsswd("Alejandro12.");
		p.setDescripcion("Soy el fucking boss del barrio");
		p.setIdGenero("H");
		p.setAnoNacimiento(2000);
		p.setIdIdioma("en");
		p.setIdNivel(3);
		p.setIdPais("ES");
		p.setPrecioSesion(7.0d);
		p.setEmail("acorralfdez@gmail.com");
		// signUp(p);
		
		findByCriteriaTest();
	}
	
}
