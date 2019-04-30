package com.educorp.eduinteractive.service;

import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Dia;
import com.educorp.eduinteractive.ecommerce.service.impl.DiaServicesImpl;
import com.educorp.eduinteractive.ecommerce.service.spi.DiaServices;

public class DiaServicesTest {

	private static Logger logger = LogManager.getLogger(DiaServicesTest.class);

	public static DiaServices diaServices = new DiaServicesImpl();

	public static final void findById() {
		
		Integer id = 3;
		try {
			logger.debug("Finding by id: {}", id);
			Dia dia = diaServices.findById(id);
			if (dia != null) {
				logger.debug("Dia encontrado: {}", dia);
			}else {
				logger.debug("Dia {} no encontrado", id);
			}
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static final void findAll() {
		logger.debug("Finding all...");
		try {
			List<Dia> dias = diaServices.findAll();
			logger.debug("Encontrados {} dias [ ", dias.size());
			for(Dia d: dias) {
				logger.debug(d);
			}
			logger.debug("]");
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Properties systemProperties = System.getProperties();
		String key = null;
		for(Enumeration keys = systemProperties.keys(); keys.hasMoreElements(); ) {
			key = (String) keys.nextElement();
			System.out.println(key.concat("=").concat(System.getProperty(key)));
		}
		
	}

}
