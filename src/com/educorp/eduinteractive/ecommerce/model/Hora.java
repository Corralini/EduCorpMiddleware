package com.educorp.eduinteractive.ecommerce.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Hora extends AbstractValueObject{

	private Integer idHora = null;
	private String Hora = null;
	
	public Hora() {
		
	}
	
	public Integer getIdHora() {
		return idHora;
	}

	public void setIdHora(Integer idHora) {
		this.idHora = idHora;
	}

	public String getHora() {
		return Hora;
	}

	public void setHora(String hora) {
		Hora = hora;
	}

	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}
}
