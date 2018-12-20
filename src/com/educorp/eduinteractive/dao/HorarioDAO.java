package com.educorp.eduinteractive.dao;

import java.util.List;

import com.educorp.eduinteractive.model.Horario;

public interface HorarioDAO {

	public Horario findById (Integer id)
		throws Exception;
	
	public List <Horario> findBy (Integer idProfesor, Integer idDia, Integer idHorario)
		throws Exception;
	
	public Horario create (Horario h)
		throws Exception;
	
	public Horario delete (Horario h)
		throws Exception;
}
