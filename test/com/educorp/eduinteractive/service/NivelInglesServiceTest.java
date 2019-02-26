package com.educorp.eduinteractive.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.NivelIngles;
import com.educorp.eduinteractive.ecommerce.service.impl.NivelInglesServicesImpl;
import com.educorp.eduinteractive.ecommerce.service.spi.NivelInglesServices;

public class NivelInglesServiceTest {

	private static Logger logger = LogManager.getLogger(NivelInglesServiceTest.class);
	
	public static void findAll() throws DataException {
		NivelInglesServices nivel = new NivelInglesServicesImpl();
		List<NivelIngles> niveles = nivel.findAll();
		for(NivelIngles n: niveles) {
			logger.debug(n);
		}
	}
	
	public static void main(String[] args) {
		try {
			findAll();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
