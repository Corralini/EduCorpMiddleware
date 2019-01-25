package com.educorp.eduinteractive.service;

import com.educorp.eduinteractive.model.Profesor;

public class ProfesorCriteria extends Profesor{

	private Double precioSesionHasta = null;
	
	public ProfesorCriteria() {
		
	}

	public Double getPrecioSesionHasta() {
		return precioSesionHasta;
	}

	public void setPrecioSesionHasta(Double precioSesionHasta) {
		this.precioSesionHasta = precioSesionHasta;
	}
	
}
