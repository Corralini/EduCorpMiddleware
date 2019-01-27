package com.educorp.eduinteractive.ecommerce.service;

import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * Utilidad para la encriptación y validación de contraseñas.
 * (Se podría implementar también como servicio...)
 * 
 * Desacoplar siempre que sea posible de APIs de terceros.
 * 
 * @author https://www.linkedin.com/in/joseantoniolopezperez
 * @version 0.2
 */
public class PasswordEncryptionUtil {
	
	// Stataless, asi que no tiene sentido instanciar uno por cada uso, en principio...
	private static final StrongPasswordEncryptor PASSWORD_ENCRYPTOR = new StrongPasswordEncryptor();	
	
	public static final String encryptPassword(String password) {
		return PASSWORD_ENCRYPTOR.encryptPassword(password);
	}

	public static final boolean checkPassword(String plainPassword, String encryptedPassword) {
		if (PASSWORD_ENCRYPTOR.checkPassword(plainPassword, encryptedPassword)) {
			return true;
			// correct!
		} else {
			// bad login!
			return false;
		}
	}


}
