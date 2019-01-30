package com.educorp.eduinteractive.ecommerce.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Horario;
import com.educorp.eduinteractive.ecommerce.service.criteria.HorarioCriteria;

public interface HorarioDAO {

	public Horario findById (Connection connection, Integer id)
		throws DuplicateInstanceException, DataException;
	
	public List<Horario> findByCriteria (Connection connection , HorarioCriteria horario)
		throws DataException;
	
	public List <Horario> findBy (Connection connection,Integer idProfesor, Integer idDia)
		throws DataException;
	
	public void update (Connection connection,Horario h)
		throws InstanceNotFoundException, DataException;
	
	public Horario create (Connection connection,Horario h)
		throws DuplicateInstanceException, DataException;
	
	public Horario delete (Connection connection,Horario h)
		throws DataException;
}
