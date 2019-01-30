package com.educorp.eduinteractive.ecommerce.service.spi;

public interface MailService {

	public void sendEmail (String to,String subject, String plainText)
		throws Exception;
	
}
