package com.educorp.eduinteractive.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Sesion extends ValueObject{

	private Integer idSesion = null;
	private Integer idEstudiante = null;
	private Integer idProfesor = null;
	private Integer idMes = null;
	private Integer idDia = null;
	private Integer idHora = null;
	private Date fechaInicio = null;
	private Date fechaFIn = null;
	private Double precio = null;
	private Integer ano = null;
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


	public Integer getIdMes() {
		return idMes;
	}


	public void setIdMes(Integer idMes) {
		this.idMes = idMes;
	}


	public Integer getIdDia() {
		return idDia;
	}


	public void setIdDia(Integer idDia) {
		this.idDia = idDia;
	}


	public Integer getIdHora() {
		return idHora;
	}


	public void setIdHora(Integer idHora) {
		this.idHora = idHora;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFIn() {
		return fechaFIn;
	}


	public void setFechaFIn(Date fechaFIn) {
		this.fechaFIn = fechaFIn;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}


	public Integer getAno() {
		return ano;
	}


	public void setAno(Integer ano) {
		this.ano = ano;
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
