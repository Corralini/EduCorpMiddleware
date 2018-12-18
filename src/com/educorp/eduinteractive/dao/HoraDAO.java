package com.educorp.eduinteractive.dao;

import java.util.List;

import com.educorp.eduinteractive.model.Hora;

public interface HoraDAO {
	
	public List <Hora> findAll ()
		throws Exception;
	
}
