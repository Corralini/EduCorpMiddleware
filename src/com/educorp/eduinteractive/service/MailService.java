package com.educorp.eduinteractive.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

public class MailService {
	
	public MailService() {		
	}
	
	public boolean sendMail (String subject, String message, String...to) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("cc6097142@gmail.com", "faker123456789"));
			email.setSSLOnConnect(true);
			email.setFrom("cc6097142@gmail.com");
			email.setSubject(subject);
			email.setHtmlMsg(message);

			email.addTo(to);
			email.send();
			return true;
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}
