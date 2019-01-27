package com.educorp.eduinteractive.ecommerce.model;

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
	private Integer anoNacimiento;
	private Date fechaSubscripcion;
	private Double precioSesion;
	private String idIdioma;
	private String idGenero;
	private Integer idNivel;
	private Integer Aceptado;
	private Double puntuacion;
	private String Descripcion;
	private Integer codigoDeRecuperacion;
	
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

	public Integer getAnoNacimiento() {
		return anoNacimiento;
	}

	public void setAnoNacimiento(Integer anoNacimiento) {
		this.anoNacimiento = anoNacimiento;
	}

	public Date getFechaSubscripcion() {
		return fechaSubscripcion;
	}

	public void setFechaSubscripcion(Date fechaSubscripcion) {
		this.fechaSubscripcion = fechaSubscripcion;
	}

	public Double getPrecioSesion() {
		return precioSesion;
	}

	public void setPrecioSesion(Double precioSesión) {
		this.precioSesion = precioSesión;
	}

	public String getIdIdioma() {
		return idIdioma;
	}

	public void setIdIdioma(String idIdioma) {
		this.idIdioma = idIdioma;
	}

	public String getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(String idGenero) {
		this.idGenero = idGenero;
	}

	public Integer getIdNivel() {
		return idNivel;
	}

	public void setIdNivel(Integer idNivel) {
		this.idNivel = idNivel;
	}

	public Integer getAceptado() {
		return Aceptado;
	}

	public void setAceptado(Integer aceptado) {
		Aceptado = aceptado;
	}

	public Double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Double puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public Integer getCodigoDeRecuperacion() {
		return codigoDeRecuperacion;
	}

	public void setCodigoDeRecuperacion(Integer codigoDeRecuperacion) {
		this.codigoDeRecuperacion = codigoDeRecuperacion;
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
