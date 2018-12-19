package com.educorp.eduinteractive.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Mes extends AbstractValueObject{

	private Integer idMes = null;
	private String mes = null;
	
	public Mes() {
		
	}
	 
	public Integer getIdMes() {
		return idMes;
	}

	public void setIdMes(Integer idMes) {
		this.idMes = idMes;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}
}
