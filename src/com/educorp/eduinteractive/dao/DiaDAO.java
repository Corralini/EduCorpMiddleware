package com.educorp.eduinteractive.dao;

import java.util.List;

import com.educorp.eduinteractive.model.Dia;

public interface DiaDAO {

	public Dia findById (Integer id)
		throws Exception;
	
	public List<Dia> findAll ()
		throws Exception;
}
