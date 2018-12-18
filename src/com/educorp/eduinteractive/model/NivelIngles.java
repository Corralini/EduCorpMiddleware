package com.educorp.eduinteractive.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class NivelIngles extends ValueObject {

	private Integer idNivel = null;
	private String nivel = null;
	
	public NivelIngles() {
		
	}
	
	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}

	public Integer getIdNivel() {
		return idNivel;
	}

	public void setIdNivel(Integer idNivel) {
		this.idNivel = idNivel;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
}
