package com.educorp.eduinteractive.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class EstudiantePuntuaProfesor extends ValueObject {
	
	private Integer idPuntuacion = null;
	private Integer idEstudiante = null;
	private Integer idProfesor = null;
	private Date fechaPuntuacion = null;
	private Double puntuacion = null;
	
	public EstudiantePuntuaProfesor() {
		
	}
	
	public Integer getIdPuntuacion() {
		return idPuntuacion;
	}

	public void setIdPuntuacion(Integer idPuntuacion) {
		this.idPuntuacion = idPuntuacion;
	}

	public Integer getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(Integer idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public Integer getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(Integer idProfesor) {
		this.idProfesor = idProfesor;
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
