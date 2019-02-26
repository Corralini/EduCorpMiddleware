package com.educorp.eduinteractive.ecommerce.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.educorp.eduinteractive.ecommerce.exceptions.MailException;
import com.educorp.eduinteractive.ecommerce.service.spi.MailService;

public class MailServiceImpl implements MailService{

	public MailServiceImpl() {		
	}


	@Override
	public void sendEmail(String to, String subject, String plainText) throws MailException {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("educorpinteractive@gmail.com", "FiwTV4c5"));
			email.setSSLOnConnect(true);
			email.setFrom("educorpinteractive@gmail.com");
			email.setSubject(subject);
			email.setMsg(plainText);
			email.addTo(to);
			email.send();
		}catch (EmailException e) {
			e.printStackTrace();
		}

	}
}
