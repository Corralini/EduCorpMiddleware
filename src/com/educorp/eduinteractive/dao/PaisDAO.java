package com.educorp.eduinteractive.dao;

import java.util.List;

import com.educorp.eduinteractive.model.Pais;

public interface PaisDAO {
	
	public List<Pais> findAll () 
			throws Exception;
}
