package com.educorp.eduinteractive.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.model.Pais;

public interface PaisDAO {
	
	public List<Pais> findAll (Connection connection) 
			throws Exception;
}
