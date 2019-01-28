package com.educorp.eduinteractive.ecommerce.dao.spi;

import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Horario;

public interface HorarioDAO {

	public Horario findById (Integer id)
		throws DataException;
	
	public List <Horario> findBy (Integer idProfesor, Integer idDia, Integer idHorario)
		throws DataException;
	
	public Horario update (Horario h)
		throws DataException;
	
	public Horario create (Horario h)
		throws DataException;
	
	public Horario delete (Horario h)
		throws DataException;
}
