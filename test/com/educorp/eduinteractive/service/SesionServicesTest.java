package com.educorp.eduinteractive.service;

import java.util.Date;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.MailException;
import com.educorp.eduinteractive.ecommerce.model.Horario;
import com.educorp.eduinteractive.ecommerce.model.Sesion;
import com.educorp.eduinteractive.ecommerce.service.impl.SesionServicesImpl;
import com.educorp.eduinteractive.ecommerce.service.spi.SesionServices;

public class SesionServicesTest {

	public static void createTest (Horario h, Date fecha, Integer idEstudiante) 
			throws MailException, DuplicateInstanceException, DataException{
		SesionServices sesionServices = new SesionServicesImpl(); 
		
		sesionServices.create(h, fecha, idEstudiante);
	}

	public static void cambiarEstadoTest (Sesion s, String estado)
			throws DataException{
		SesionServices sesionServices = new SesionServicesImpl(); 
		
		sesionServices.cambiarEstado(s, estado);
	}

	public static void empezarSesionTest (Sesion s)
			throws DataException{
		SesionServices sesionServices = new SesionServicesImpl(); 
		
		sesionServices.empezarSesion(s);
	}
	
	public static void terminarSesionTest (Sesion s)
			throws DataException{
		SesionServices sesionServices = new SesionServicesImpl(); 
		
		sesionServices.terminarSesion(s);
	}
	

	public static void main(String[] args) throws MailException, DuplicateInstanceException, DataException {
		
		
		Horario h = new Horario();
		h.setIdHorario(19);
		h.setIdProfesor(11);
		
		createTest(h, new Date(), 4);
		
//		Sesion s = new Sesion();
//		s.setIdSesion(7);
//		s.setIdEstado("A");
//		
//		empezarSesionTest(s);
//		terminarSesionTest(s);
//		cambiarEstadoTest(s, "a");
		
	}
	
}
