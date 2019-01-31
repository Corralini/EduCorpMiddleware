package com.educorp.eduinteractive.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.model.Horario;
import com.educorp.eduinteractive.ecommerce.service.impl.HorarioServicesImpl;
import com.educorp.eduinteractive.ecommerce.service.spi.HorarioService;

public class HorarioServiceTest {

	public static void create (Horario h) throws DuplicateInstanceException, DataException{
		HorarioService horarioService = new HorarioServicesImpl();

		horarioService.create(h);
	}

	public static Horario findById (Integer id) throws DataException{
		HorarioService horarioService = new HorarioServicesImpl();
		System.out.println("" + horarioService.findById(id));
		return horarioService.findById(id);
	}

	public static List<Horario> findByFecha (Integer idProfesor, Date fecha) throws DataException{
		HorarioService horarioService = new HorarioServicesImpl();
		
		return horarioService.findByFecha(idProfesor, fecha);
	}

	public static void main(String[] args) throws DuplicateInstanceException, DataException {
		Horario h = new Horario();

		//		h.setIdProfesor(4);
		//		h.setIdDia(5);
		//		h.setIdHora(25);
		//		
		//		create(h);
		
//		findById(17);
		
		List<Horario> horarios = new ArrayList<Horario>();
		
		horarios = findByFecha(4, new Date());
		
		for(Horario horar : horarios) {
			System.out.println("" + horar);
		}
	}

}
