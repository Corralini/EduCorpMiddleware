package com.educorp.eduinteractive.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.MailException;
import com.educorp.eduinteractive.ecommerce.model.Horario;
import com.educorp.eduinteractive.ecommerce.model.Sesion;
import com.educorp.eduinteractive.ecommerce.service.impl.SesionServicesImpl;
import com.educorp.eduinteractive.ecommerce.service.spi.SesionServices;
import com.mysql.cj.util.StringUtils;

public class SesionServicesTest {

	public static final DateFormat SHORT_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void createTest (Horario h, Date fecha, Integer idEstudiante) 
			throws MailException, DuplicateInstanceException, DataException{
		SesionServices sesionServices = new SesionServicesImpl(); 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		sesionServices.create(h, fecha, idEstudiante);
	}
	
	public static Sesion findByIdTest() {
		Sesion sesion = new Sesion();
		SesionServices sesionServices = new SesionServicesImpl(); 
		try {
			sesion = sesionServices.findById(80);
		} catch (DataException e) {
			e.printStackTrace();
		}
		
		return sesion;
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
	

	public static Date dateValidator (String parameter) {
		try {
			if(!StringUtils.isEmptyOrWhitespaceOnly(parameter)) {
				return SesionServicesTest.SHORT_FORMAT_DATE.parse(parameter);
			}else {
				return null;
			}
		}catch (ParseException ex) {
			return null;
		}
	}
	
	public static void main(String[] args) throws MailException, DuplicateInstanceException, DataException {
		
		Horario h = new Horario();
		h.setIdHorario(21);
		h.setIdProfesor(15);
		h.setIdDia(4);
		h.setIdHora(78);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		createTest(h, calendar.getTime(), 53);
		

		
	}
	
}
