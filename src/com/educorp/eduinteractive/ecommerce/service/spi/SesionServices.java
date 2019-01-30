package com.educorp.eduinteractive.ecommerce.service.spi;

import java.util.Date;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.MailException;
import com.educorp.eduinteractive.ecommerce.model.Horario;
import com.educorp.eduinteractive.ecommerce.model.Sesion;

public interface SesionServices {

	public void create (Horario h, Date fecha, Integer idEstudiante) 
		throws MailException, DuplicateInstanceException, DataException;
	
	public void cambiarEstado (Sesion s, String estado)
		throws DataException;
	
	public void empezarSesion (Sesion s)
		throws DataException;
	
	public void terminarSesion (Sesion s)
		throws DataException;
	
}
