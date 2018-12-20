package com.educorp.eduinteractive.dao;

import java.util.List;

import com.educorp.eduinteractive.model.Dia;

public interface DiaDAO {
	
	public List<Dia> findAll ()
		throws Exception;
}
