package com.educorp.eduinteractive.model.test;

import java.util.List;

import com.educorp.eduinteractive.model.Mes;

public interface MesDAO {

	public Mes findById (Integer id)
		throws Exception;
	
	public List <Mes> findAll()
		throws Exception;
	
}
