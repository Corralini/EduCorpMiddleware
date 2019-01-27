package com.educorp.eduinteractive.ecommerce.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Idioma extends AbstractValueObject{

	private String idIdioma = null;
	private String idioma = null;
	
	public Idioma() {
		
	}
	
	public String getIdIdioma() {
		return idIdioma;
	}

	public void setIdIdioma(String idIdioma) {
		this.idIdioma = idIdioma;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}
}
