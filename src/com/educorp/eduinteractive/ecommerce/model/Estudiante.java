package com.educorp.eduinteractive.ecommerce.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Estudiante extends AbstractValueObject{

	private Integer id;
	private String email;
	private String idPais;
	private String psswd;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private Date anoNacimiento;
	private Date fechaSubscripcion;
	private String idNivel;
	private String idGenero;
	private Integer codigoDeRecuperacion;
	
	public Estudiante() {
		
	}


	public Integer getIdEstudiante() {
		return id;
	}


	public void setIdEstudiante(Integer idEstudiante) {
		this.id = idEstudiante;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getIdPais() {
		return idPais;
	}


	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}


	public String getPsswd() {
		return psswd;
	}


	public void setPsswd(String psswd) {
		this.psswd = psswd;
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


	public Date getAnoNacimiento() {
		return anoNacimiento;
	}


	public void setAnoNacimiento(Date anoNacimiento) {
		this.anoNacimiento = anoNacimiento;
	}


	public Date getFechaSubscripcion() {
		return fechaSubscripcion;
	}


	public void setFechaSubscripcion(Date fechaSubscripcion) {
		this.fechaSubscripcion = fechaSubscripcion;
	}


	public String getIdNivel() {
		return idNivel;
	}


	public void setIdNivel(String idNivel) {
		this.idNivel = idNivel;
	}


	public String getIdGenero() {
		return idGenero;
	}


	public void setIdGenero(String idGenero) {
		this.idGenero = idGenero;
	}
	
	public Integer getCodigoDeRecuperacion() {
		return codigoDeRecuperacion;
	}


	public void setCodigoDeRecuperacion(Integer codigoDeRecuperacion) {
		this.codigoDeRecuperacion = codigoDeRecuperacion;
	}


	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}

	
}
