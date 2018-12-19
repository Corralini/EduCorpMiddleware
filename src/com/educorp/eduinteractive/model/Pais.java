package com.educorp.eduinteractive.model;

public class Pais extends AbstractValueObject{

	private Integer idPais = null;
	private String pais = null;
	
	public Pais() {
		
	}

	public Integer getIdPais() {
		return idPais;
	}

	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

	public String getNombrePais() {
		return pais;
	}

	public void setNombrePais(String nombrePais) {
		this.pais = nombrePais;
	}
}
