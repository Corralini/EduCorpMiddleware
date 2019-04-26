package com.educorp.eduinteractive.ecommerce.service.spi;

import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Sesion;

public interface CalendarioServices {

	public List<Sesion> findByUsuario (Integer idEstudiante, boolean isTeacher) throws DataException;
	
}
