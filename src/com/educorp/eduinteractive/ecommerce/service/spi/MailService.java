package com.educorp.eduinteractive.ecommerce.service.spi;

import com.educorp.eduinteractive.ecommerce.exceptions.MailException;

public interface MailService {

	public void sendEmail (String to,String subject, String plainText)
		throws MailException;
	
}
