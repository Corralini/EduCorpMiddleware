package com.educorp.eduinteractive.ecommerce.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Idioma;

public interface IdiomaDAO {

	
	public List <Idioma> findAll(Connection connection)
		throws DataException;
	 
}
