package com.educorp.eduinteractive.ecommerce.dao.spi;

import java.sql.Connection;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Estado;

public interface EstadoDAO {

	
	public Estado findById(Connection connection, String estado)
			throws DataException;
	
}
