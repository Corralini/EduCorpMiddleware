package com.educorp.eduinteractive.ecommerce.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Estudiante extends AbstractValueObject{

	private Integer id = null;
	private String email = null;
	private String idPais = null;
	private String psswd = null;
	private String nombre = null;
	private String apellido1 = null;
	private String apellido2 = null;
	private Integer anoNacimiento = null;
	private Date fechaSubscripcion = null;
	private Integer idNivel = null;
	private String idGenero = null;
	private Integer codigoDeRecuperacion = null;
	private Double puntuacion = null;
	
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
	
	public Integer getCodigoDeRecuperacion() {
		return codigoDeRecuperacion;
	}


	public void setCodigoDeRecuperacion(Integer codigoDeRecuperacion) {
		this.codigoDeRecuperacion = codigoDeRecuperacion;
	}


	public Double getPuntuacion() {
		return puntuacion;
	}


	public void setPuntuacion(Double puntuacion) {
		this.puntuacion = puntuacion;
	}


	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Estudiante)) {
			return false;
		}
		Estudiante e = (Estudiante) o;
		if(this.getIdEstudiante() == null && e.getIdEstudiante() == null) {
			return true;
		}else {
			return this.getIdEstudiante().equals(e.getIdEstudiante());
		} 
	}
	
	@Override
	public int hashCode() {
		return id==null?Integer.MAX_VALUE:id.hashCode();
	}
	
}
