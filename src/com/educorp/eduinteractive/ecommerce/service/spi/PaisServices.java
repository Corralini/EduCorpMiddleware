package com.educorp.eduinteractive.ecommerce.service.spi;

import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Pais;

public interface PaisServices {

	public List<Pais> findByIdioma (String idIdioma) throws DataException;
	
}
