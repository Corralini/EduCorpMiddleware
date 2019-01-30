package com.educorp.eduinteractive.ecommerce.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Dia;

public interface DiaDAO {
	
	public Dia findById (Connection connection, Integer id) throws InstanceNotFoundException, DataException;
	
	public List<Dia> findAll (Connection connection)
		throws DataException;
}
