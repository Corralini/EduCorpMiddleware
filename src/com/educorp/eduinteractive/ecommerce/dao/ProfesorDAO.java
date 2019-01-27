package com.educorp.eduinteractive.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Profesor;
import com.educorp.eduinteractive.ecommerce.service.ProfesorCriteria;

public interface ProfesorDAO {

	public Profesor findById (Connection c, Integer id)
			throws  DataException;

	public Profesor findByEmail (Connection c, String email)
			throws DataException;
	
	public List<Profesor> findByCriteria (Connection c, ProfesorCriteria criteria)
			throws Exception;

	public Profesor create (Connection c, Profesor p)
			throws Exception;

	public Profesor update (Connection c, Profesor p)
			throws Exception;

}