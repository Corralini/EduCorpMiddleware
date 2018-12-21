package com.educorp.eduinteractive.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Profesor extends AbstractValueObject  implements Comparable<Profesor>  {
	
	private Integer id;
	private String email;
	private String idPais;
	private String psswd;
	private String psswdEncriptada;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private Date anoNacimiento;
	private Date fechaSubscripcion;
	private Double precioSesi�n;
	private Integer idIdioma;
	private String idGenero;
	private Boolean Aceptado;
	private Double puntuacion;
	
	public Profesor() {

	}

	public Integer getIdProfesor() {
		return id;
	}

	public void setIdProfesor(Integer idProfesor) {
		this.id = idProfesor;
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
	
	public String getPsswdEncriptada() {
		return psswdEncriptada;
	}

	public void setPsswdEncriptada(String psswdEncriptada) {
		this.psswdEncriptada = psswdEncriptada;
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

	public Double getPrecioSesi�n() {
		return precioSesi�n;
	}

	public void setPrecioSesi�n(Double precioSesi�n) {
		this.precioSesi�n = precioSesi�n;
	}

	public Integer getIdIdioma() {
		return idIdioma;
	}

	public void setIdIdioma(Integer idIdioma) {
		this.idIdioma = idIdioma;
	}

	public String getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(String idGenero) {
		this.idGenero = idGenero;
	}

	public Boolean getAceptado() {
		return Aceptado;
	}

	public void setAceptado(Boolean aceptado) {
		Aceptado = aceptado;
	}

	public Double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Double puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	@Override
	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int compareTo(Profesor p) {
		System.out.println("Comparando " + this.nombre + " con " + p.getNombre() + "...");
		return this.getPuntuacion().compareTo(p.getPuntuacion());
	}
}
