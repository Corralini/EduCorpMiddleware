package com.educorp.eduinteractive.dao;

import java.util.List;

import com.educorp.eduinteractive.model.Mes;

public interface MesDAO {
	
	public List <Mes> findAll()
		throws Exception;
	
}
