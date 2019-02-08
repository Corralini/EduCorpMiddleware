package com.educorp.eduinteractive.ecommerce.model.util;
	
import java.util.Comparator;

import com.educorp.eduinteractive.ecommerce.model.Profesor;

public class ProfesorComparatorByNombre implements Comparator<Profesor> {

		public ProfesorComparatorByNombre () {
		}
	
		@Override
		public int compare(Profesor p1, Profesor p2) {
			return p1.getPuntuacion().compareTo(p2.getPuntuacion());
		}
		
}
