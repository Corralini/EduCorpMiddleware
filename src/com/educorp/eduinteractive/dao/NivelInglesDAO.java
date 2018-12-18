package com.educorp.eduinteractive.model.test;

import java.util.List;

import com.educorp.eduinteractive.model.NivelIngles;

public interface NivelInglesDAO {

	public NivelIngles findById (Integer id)
		throws Exception;
	
	public List<NivelIngles> findAll ()
		throws Exception;
	
}
