package com.educorp.eduinteractive.ecommerce.model.util;

import java.util.Comparator;

import com.educorp.eduinteractive.ecommerce.model.Profesor;

public class ProfesorComparatorByFechaSubscripcion implements Comparator<Profesor> {

	private static ProfesorComparatorByFechaSubscripcion instance = null;
	
	public static final ProfesorComparatorByFechaSubscripcion getInstance() {
		if (instance == null) {
			instance = new ProfesorComparatorByFechaSubscripcion();
		}
		return instance;
	}
	
	public ProfesorComparatorByFechaSubscripcion() {
	}
	
	@Override
	public int compare(Profesor p1, Profesor p2) {
		System.out.println("Comparo " + p1.getNombre() + " con " + p2.getNombre());
		return p1.getFechaSubscripcion().compareTo(p2.getFechaSubscripcion());
	}
}
