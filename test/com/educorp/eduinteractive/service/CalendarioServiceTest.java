package com.educorp.eduinteractive.service;

import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Sesion;
import com.educorp.eduinteractive.ecommerce.service.impl.CalendarioServicesImpl;
import com.educorp.eduinteractive.ecommerce.service.spi.CalendarioServices;

public class CalendarioServiceTest {

	public static List<Sesion> findByUsuarioTest (Integer idEstudiante) throws DataException{
		CalendarioServices calendarioServices = new CalendarioServicesImpl();

		return calendarioServices.findByUsuario(idEstudiante);
	}
	
	public static void main(String[] args) throws DataException {
		List<Sesion> sesiones = findByUsuarioTest(4);
		
		for(Sesion s : sesiones) {

		}
	}
}
