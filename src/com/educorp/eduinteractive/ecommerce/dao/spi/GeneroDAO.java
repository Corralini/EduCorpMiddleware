package com.educorp.eduinteractive.ecommerce.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Genero;

public interface GeneroDAO {


	public List <Genero> findAll(Connection connection)
		throws DataException;
	
}
