package com.educorp.eduinteractive.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Dia extends ValueObject{

	private Integer idDia = null;
	private String dia = null;
	
	public Dia() {
		
	}
	
	public Integer getIdDia() {
		return idDia;
	}

	public void setIdDia(Integer idDia) {
		this.idDia = idDia;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}
	
}
