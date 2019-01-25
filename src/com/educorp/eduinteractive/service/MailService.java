package com.educorp.eduinteractive.service;

public interface MailService {

	public void sendEmail (String to,String subject, String plainText)
		throws Exception;
	
}
