package com.educorp.eduinteractive.dao;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.model.Profesor;
import com.educorp.eduinteractive.service.ProfesorCriteria;

public interface ProfesorDAO {

	public Profesor findById (Connection c, Integer id)
			throws Exception;
		
		public List<Profesor> findByNombre (Connection c, String Nombre)
			throws Exception;
		
		public List<Profesor> findByCriteria (Connection c, ProfesorCriteria criteria)
			throws Exception;
		
		public Profesor create (Connection c, Profesor p)
			throws Exception;
		
		public Profesor update (Connection c, Profesor p)
			throws Exception;
	
}
