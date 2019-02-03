package com.educorp.eduinteractive.ecommerce.service.spi;

import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Idioma;

public interface IdiomaServices {

	public List<Idioma> findAll () throws DataException;
	
}
