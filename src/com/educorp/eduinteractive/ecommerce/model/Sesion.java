package com.educorp.eduinteractive.ecommerce.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Sesion extends AbstractValueObject{

	private Integer idSesion = null;
	private Integer idEstudiante = null;
	private Integer idProfesor = null;
	private Date fechaSesion = null;
	private Integer idHorario = null;
	private Date fechaInicio = null;
	private Date fechaFin = null;
	private Double precio = null;
	private String idEstado = null;
	private Date fechaCambioEstado = null;
	

	public Sesion() {

	}

	
	public Integer getIdSesion() {
		return idSesion;
	}


	public void setIdSesion(Integer idSesion) {
		this.idSesion = idSesion;
	}


	public Integer getIdEstudiante() {
		return idEstudiante;
	}


	public void setIdEstudiante(Integer idEstudiante) {
		this.idEstudiante = idEstudiante;
	}


	public Integer getIdProfesor() {
		return idProfesor;
	}


	public void setIdProfesor(Integer idProfesor) {
		this.idProfesor = idProfesor;
	}



	public Date getFechaSesion() {
		return fechaSesion;
	}


	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}


	public Integer getIdHorario() {
		return idHorario;
	}


	public void setIdHorario(Integer idHorario) {
		this.idHorario = idHorario;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}



	public String getIdEstado() {
		return idEstado;
	}


	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}


	public Date getFechaCambioEstado() {
		return fechaCambioEstado;
	}


	public void setFechaCambioEstado(Date fechaCambioEstado) {
		this.fechaCambioEstado = fechaCambioEstado;
	}


	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}
}
