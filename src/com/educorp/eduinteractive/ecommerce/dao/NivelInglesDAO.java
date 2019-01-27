package com.educorp.eduinteractive.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.NivelIngles;

public interface NivelInglesDAO {

	public List<NivelIngles> findAll (Connection connection)
		throws DataException;
	
}
