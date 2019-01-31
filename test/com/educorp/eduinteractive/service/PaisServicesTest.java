package com.educorp.eduinteractive.service;

import java.util.List;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Pais;
import com.educorp.eduinteractive.ecommerce.service.impl.PaisServicesImpl;
import com.educorp.eduinteractive.ecommerce.service.spi.PaisServices;

public class PaisServicesTest {

	public static List<Pais> findByIdioma (String idIdioma) throws DataException{
		PaisServices paisServices = new PaisServicesImpl();
		
		return paisServices.findByIdioma(idIdioma);
	}
	
	public static void main(String[] args) throws DataException {

		List<Pais> paises =	findByIdioma("");
		
		for(Pais p: paises) {
			System.out.println("" + p);
		}
			
	}
	
}
