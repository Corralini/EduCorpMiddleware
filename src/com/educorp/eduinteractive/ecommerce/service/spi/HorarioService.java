package com.educorp.eduinteractive.ecommerce.service.spi;

import java.util.Date;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.model.Horario;

public interface HorarioService {

	public void create (Horario h) throws DuplicateInstanceException, DataException;
	
	public Horario findById (Integer id) throws DataException;
	
	public List<Horario> findByFecha (Integer idProfesor, Date fecha) throws DataException;
	
}
