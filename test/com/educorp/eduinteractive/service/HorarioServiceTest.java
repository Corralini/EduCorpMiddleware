
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
		return horarioService.findById(id);
	}

	public static List<Horario> findByFecha (Integer idProfesor, Date fecha) throws DataException{
		HorarioService horarioService = new HorarioServicesImpl();
		
		return horarioService.findByFecha(idProfesor, fecha, 1, 1).getResultados();
	}

	public static List<Horario> findByProfesor (Integer idProfesor) throws DataException{
		HorarioService horarioService = new HorarioServicesImpl();
		
		return horarioService.findByProfesor(idProfesor);
	}
	
	public static void main(String[] args) throws DuplicateInstanceException, DataException {
//		Horario h = new Horario();
//
//				h.setIdProfesor(11);
//				h.setIdDia(5);
//				h.setIdHora(30);
//				
//				create(h);
				
		
//		findById(17);
		
		List<Horario> horarios = new ArrayList<Horario>();
		
		horarios = findByProfesor(1);
		
		for(Horario horar : horarios) {
			System.out.println("" + horar);
		}
	}

}
