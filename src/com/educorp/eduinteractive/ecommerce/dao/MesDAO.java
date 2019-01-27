package com.educorp.eduinteractive.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Mes;

public interface MesDAO {
	
	public List <Mes> findAll(Connection connection)
		throws DataException;
	
}