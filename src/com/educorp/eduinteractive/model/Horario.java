package com.educorp.eduinteractive.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Horario extends ValueObject{

	private Integer idProfesor = null;
	private Integer idDia = null;
	private Integer idHora = null;

	public Horario() {

	}

	public Integer getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(Integer idProfesor) {
		this.idProfesor = idProfesor;
	}

	public Integer getIdDia() {
		return idDia;
	}

	public void setIdDia(Integer idDia) {
		this.idDia = idDia;
	}

	public Integer getIdHora() {
		return idHora;
	}

	public void setIdHora(Integer idHora) {
		this.idHora = idHora;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
