package com.educorp.eduinteractive.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Dia;

public interface DiaDAO {
	
	public List<Dia> findAll (Connection connection)
		throws DataException;
}
