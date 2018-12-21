package com.educorp.eduinteractive.model;

public class CriteriaProfesor implements ValueObject {

	private String nombre;
	private String apellido1;
	private String apellido2;
	private Integer idNivel;
	private String idGenero;
	private Double puntuacion;
	private Double precioSesionMinimo;
	private Double precioSesionMaximo;
	private Integer idDia;
	
	public CriteriaProfesor() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Integer getIdNivel() {
		return idNivel;
	}

	public void setIdNivel(Integer idNivel) {
		this.idNivel = idNivel;
	}

	public String getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(String idGenero) {
		this.idGenero = idGenero;
	}

	public Double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Double getPrecioSesionMinimo() {
		return precioSesionMinimo;
	}

	public void setPrecioSesionMinimo(Double precioSesionMinimo) {
		this.precioSesionMinimo = precioSesionMinimo;
	}

	public Double getPrecioSesionMaximo() {
		return precioSesionMaximo;
	}

	public void setPrecioSesionMaximo(Double precioSesionMaximo) {
		this.precioSesionMaximo = precioSesionMaximo;
	}

	public Integer getIdDia() {
		return idDia;
	}

	public void setIdDia(Integer idDia) {
		this.idDia = idDia;
	}
	
	
	
}
