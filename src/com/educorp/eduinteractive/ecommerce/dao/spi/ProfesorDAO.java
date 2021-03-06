package com.educorp.eduinteractive.ecommerce.dao.spi;

import java.sql.Connection;

import com.educorp.eduinteractive.ecommerce.dao.service.Results;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Profesor;
import com.educorp.eduinteractive.ecommerce.service.criteria.ProfesorCriteria;

public interface ProfesorDAO{

	public Profesor findById (Connection c, Integer id)
			throws  InstanceNotFoundException, DataException;

	public Profesor findByEmail (Connection c, String email)
			throws DataException;
	
	public Results<Profesor> findByCriteria (Connection c, ProfesorCriteria criteria, 
					int startIndex, int count)
			throws DataException;

	public Profesor create (Connection c, Profesor p)
			throws DuplicateInstanceException, DataException;

	public void update (Connection c, Profesor p)
			throws InstanceNotFoundException, DataException;

}
