package com.educorp.eduinteractive.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Estado extends AbstractValueObject{

	private String idEstado = null;
	private String estado = null;
	
	public Estado() {
		
	}
	
	public String getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}
}
