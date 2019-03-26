package com.educorp.eduinteractive.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	public static void findByIdTest() {
		Sesion sesion = new Sesion();
		SesionServices sesionServices = new SesionServicesImpl(); 
		try {
			sesion = sesionServices.findById(1);
		} catch (DataException e) {
			e.printStackTrace();
		}
		
			System.out.println(sesion);
	}
	
	public static void findByCalendarioTest() {
		List<Sesion> sesiones = new ArrayList<Sesion>();
		SesionServices sesionServices = new SesionServicesImpl(); 
		try {
			sesiones = sesionServices.findByCalendario(1);
		} catch (DataException e) {
			e.printStackTrace();
		}
		
		for(Sesion s: sesiones) {
			System.out.println(s);
		}
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
		
		
		findByIdTest();
		
	}
	
}
