package com.educorp.eduinteractive.dao;

import java.util.List;

import com.educorp.eduinteractive.model.Estado;

public interface EstadoDAO {

	
	public List<Estado> findByAll()
			throws Exception;
	
}
