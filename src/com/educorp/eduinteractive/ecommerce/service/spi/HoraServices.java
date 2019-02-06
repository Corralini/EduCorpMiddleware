package com.educorp.eduinteractive.ecommerce.service.spi;

import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Hora;

public interface HoraServices {

	public Hora findById (Integer id)
			throws InstanceNotFoundException, DataException;
	
	public List <Hora> findAll ()
			throws DataException;
	
}
