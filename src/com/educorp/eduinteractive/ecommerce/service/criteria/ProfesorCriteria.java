package com.educorp.eduinteractive.ecommerce.service.criteria;

import com.educorp.eduinteractive.ecommerce.model.Profesor;

public class ProfesorCriteria extends Profesor{

	private Double precioSesionHasta = null;
	private Integer diaSesion = null;
	
	public ProfesorCriteria() {
		
	}

	public Double getPrecioSesionHasta() {
		return precioSesionHasta;
	}

	public void setPrecioSesionHasta(Double precioSesionHasta) {
		this.precioSesionHasta = precioSesionHasta;
	}

	public Integer getDiaSesion() {
		return diaSesion;
	}

	public void setDiaSesion(Integer diaSesion) {
		this.diaSesion = diaSesion;
	}
	
}
