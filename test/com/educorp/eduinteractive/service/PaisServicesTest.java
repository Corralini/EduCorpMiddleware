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
	
	public static void findById () {
		PaisServices paisServices = new PaisServicesImpl();
		try {
			Pais p = paisServices.findById("es", "ES");
			System.out.println("" + p);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws DataException {

		findById();
			
	}
	
}
