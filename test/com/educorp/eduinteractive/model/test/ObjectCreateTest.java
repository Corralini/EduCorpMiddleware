package com.educorp.eduinteractive.model.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.educorp.eduinteractive.model.Profesor;
import com.educorp.eduinteractive.model.util.ProfesorComparatorByFechaSubscripcion;
import com.educorp.eduinteractive.model.util.ProfesorComparatorByNombre;

public class ObjectCreateTest {

	public static void main (String args[]) {
		
//		Sesion[] sesiones = new Sesion[2];
//		
//		Estudiante estudiante1 = new Estudiante();
//		Profesor profesor1 = new Profesor();
//		estudiante1.setNombre("Rudolfo");
//		profesor1.setNombre("Juse");
//		Sesion sesion1 = new Sesion();
////		sesion1.setIdSesion("456");
//		sesion1.setIdEstudiante(528);
//		sesion1.setIdProfesor(789);
//		sesion1.setFechaInicio(new Date());
//		sesion1.setFechaFin(new Date());
//		sesion1.setPrecio(8);
//		sesion1.setFechaEstimadaInicio(new Date());
//		sesion1.setRecordable(false);
//		System.out.println("La sesion empieza a las " + sesion1.getFechaInicio());
//		System.out.println("La sesi�n ha terminado a las " + sesion1.getFechaFin());
//		System.out.println("El profesor es: " + sesion1.getIdProfesor() + " y el estudiante es: " + sesion1.getIdEstudiante());
//		System.out.println("El precio de la clase: " + sesion1.getPrecio());
//		if(sesion1.getRecordable() == false){
//			System.out.println("La clase " + sesion1.getIdSesion() + " no fue grabada");
//		}else {
//			System.out.println("La clase " + sesion1.getIdSesion() + " fue grabada");
//			sesion1.setTimeRecorded(45);
//			System.out.println("La grabaci�n dur�: " + sesion1.getTimeRecorded() + " minutos") ;
//		}
//		System.out.println("------------------------------");
		
//		Sesion sesion2 = new Sesion("13");
//		sesion2.setFechaInicio(new Date());
//		sesion2.setFechaFin(new Date());
//		sesion2.setIdEstudiante(2);
//		sesion2.setIdProfesor(3);
//		sesion2.setPrecio(17);
		
//		System.out.println(sesion2);
//		System.out.println(estudiante1);
//		System.out.println(profesor1);
		
		
//	System.out.println("La sesion empieza a las " + sesion2.getFechaInicio());
//		System.out.println("La sesi�n ha terminado a las " + sesion2.getFechaFin());
//		System.out.println("El profesor es: " + sesion2.getIdProfesor() + " y el estudiante es: " + sesion2.getIdEstudiante());
//		System.out.println("El precio de la clase: " + sesion2.getPrecio());
//		if(sesion2.getRecordable() == false){
//			System.out.println("La clase " + sesion2.getIdSesion() + " no fue grabada");
//		}else {
//			System.out.println("La clase " + sesion2.getIdSesion() + " fue grabada");
//			sesion2.setTimeRecorded(60);
//			System.out.println("La grabaci�n dur�: " + sesion2.getTimeRecorded() + " minutos") ;
//		}
		
		
		
		
		
		
		
		
		
		
		
		
//		sesiones[0] = sesion1;
//		sesiones[1] = sesion2;
//		
//		for(Sesion sesion: sesiones) {
//			
//			System.out.println("Sesion: " + ToStringBuilder.reflectionToString(sesion));
//		}
		
		//Visualizacion de una lista de un profesor p
		List<Profesor> profesores = new ArrayList<Profesor>();
		
		Profesor p1 = new Profesor();
		
		p1.setNombre("El Boss");
		p1.setApellido1("Del barrio");
		p1.setPuntuacion(6.0);
		profesores.add(p1);
		
		p1 = new Profesor();
		
		p1.setNombre("Manolo");
		p1.setApellido1("La ventana");
		p1.setPuntuacion(3.0);
		profesores.add(p1);
		
		p1 = new Profesor();
		
		p1.setNombre("Juse");
		p1.setApellido1("Ordenador");
		p1.setPuntuacion(5.5);
//		p1.setFechaSubscripcion(new);
		profesores.add(p1);
		
		
		Collections.sort(profesores, ProfesorComparatorByFechaSubscripcion.getInstance());
		
		
		//Los siguientes for son equivalentes, se utilizan para mostrar elementos de una lista
		for(Profesor p: profesores) {
			System.out.println(p.getNombre() + " " + p.getApellido1());
			System.out.println("Puntuaci�n: " + p.getPuntuacion());
		}
//		for (int i = 0; i < profesores.size(); i++) {
//			System.out.println(profesores.get(i).getNombre());
//			System.out.println(profesores.get(i).getApellido1());
//		}
	}
}
