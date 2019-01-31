package com.educorp.eduinteractive.ecommerce.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Pais;

public interface PaisDAO {
	
	public List<Pais> findAll (Connection connection, String idIdioma) 
			throws DataException;
}
