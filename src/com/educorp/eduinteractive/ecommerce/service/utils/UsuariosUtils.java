package com.educorp.eduinteractive.ecommerce.service.utils;


public class UsuariosUtils {

	public static int codRecuperacion () {
		
		return (int) Math.floor(Math.random()*(100000-999999+1)+999999);
		
	}
	
}
