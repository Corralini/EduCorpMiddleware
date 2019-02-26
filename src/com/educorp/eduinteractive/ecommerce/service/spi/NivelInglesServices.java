package com.educorp.eduinteractive.ecommerce.service.spi;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.NivelIngles;

public interface NivelInglesServices {

	public List<NivelIngles> findAll() throws DataException;
	
}
