package com.educorp.eduinteractive.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Genero extends AbstractValueObject {
	
	private String idGenero = null;
	private String genero = null;
	
	public Genero() {
		
	}
	
	public String getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(String idGenero) {
		this.idGenero = idGenero;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}
}
