package com.educorp.eduinteractive.ecommerce.service;

public interface MailService {

	public void sendEmail (String to,String subject, String plainText)
		throws Exception;
	
}
