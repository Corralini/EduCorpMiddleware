package com.educorp.eduinteractive.ecommerce.model;

public class Pais extends AbstractValueObject{

	private String idPais = null;
	private String pais = null;
	
	public Pais() {
		
	}

	public String getIdPais() {
		return idPais;
	}

	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}

	public String getNombrePais() {
		return pais;
	}

	public void setNombrePais(String nombrePais) {
		this.pais = nombrePais;
	}
}
