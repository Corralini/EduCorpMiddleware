package com.educorp.eduinteractive.model.util;

import java.util.Comparator;

import com.educorp.eduinteractive.model.Profesor;

public class ProfesorComparatorByNombre implements Comparator<Profesor> {

		public ProfesorComparatorByNombre () {
		}
	
		@Override
		public int compare(Profesor p1, Profesor p2) {
			System.out.println("Comparo " + p1.getNombre() + " con " + p2.getNombre());
			return p1.getPuntuacion().compareTo(p2.getPuntuacion());
		}
		
}
