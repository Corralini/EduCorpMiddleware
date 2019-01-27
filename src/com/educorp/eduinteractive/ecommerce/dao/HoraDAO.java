package com.educorp.eduinteractive.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Hora;

public interface HoraDAO {
	
	public List <Hora> findAll (Connection connection)
		throws DataException;
	
}
