package com.educorp.eduinteractive.model.test;

import java.util.List;

import com.educorp.eduinteractive.model.Idioma;

public interface IdiomaDAO {

	public Idioma findById (Integer id)
		throws Exception;
	
	public List <Idioma> findAll ()
		throws Exception;
	
}
