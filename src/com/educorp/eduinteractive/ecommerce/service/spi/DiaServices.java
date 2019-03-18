package com.educorp.eduinteractive.ecommerce.service.spi;

import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Dia;

public interface DiaServices {

	public Dia findById (Integer id)
			throws InstanceNotFoundException, DataException;
	
	public List <Dia> findAll ()
			throws DataException; 
	
}
