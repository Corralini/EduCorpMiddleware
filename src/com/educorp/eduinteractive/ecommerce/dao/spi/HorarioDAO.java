package com.educorp.eduinteractive.ecommerce.dao.spi;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.service.Results;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Horario;

public interface HorarioDAO {

	public Horario findById (Connection connection, Integer id)
		throws DuplicateInstanceException, DataException;
	
	public Results <Horario> findBy (Connection connection,Integer idProfesor, Integer idDia, Date fecha,
			int startIndex, int count)
		throws DataException;
	
	public void update (Connection connection,Horario h)
		throws InstanceNotFoundException, DataException;
	
	public List <Horario> findByProfesor (Connection connection, Integer idProfesor)
		throws DataException;
	
	public Horario create (Connection connection,Horario h)
		throws DuplicateInstanceException, DataException;
	
	public Horario delete (Connection connection,Horario h)
		throws DataException;
}
