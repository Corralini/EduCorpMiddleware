package com.educorp.eduinteractive.ecommerce.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Puntuacion extends AbstractValueObject{

	private Integer idProfesor = null;
	private Integer idEstudiante = null;
	private Double puntuacion = null;
	private Date fechaPuntuacion = null;
	
	public Puntuacion() {
		
	}
	

	public Integer getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(Integer idProfesor) {
		this.idProfesor = idProfesor;
	}

	public Integer getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(Integer idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public Date getFechaPuntuacion() {
		return fechaPuntuacion;
	}

	public void setFechaPuntuacion(Date fechaPuntuacion) {
		this.fechaPuntuacion = fechaPuntuacion;
	}

	public Double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}
}
