package com.educorp.eduinteractive.ecommerce.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.educorp.eduinteractive.ecommerce.exceptions.MailException;
import com.educorp.eduinteractive.ecommerce.service.spi.MailService;

public class MailServiceImpl implements MailService{

	private static Logger logger = LogManager.getLogger(MailServiceImpl.class);

	private static final String MAIl_CONFIGURATION_FILE =
	        "mail-settings.properties";
	    
	    private static Map parameters;
	    private static Properties properties;
	
	static {
		try {
		 Class configurationParametersManagerClass = MailServiceImpl.class;
         ClassLoader classLoader = configurationParametersManagerClass.getClassLoader();
         InputStream inputStream = classLoader.getResourceAsStream(MAIl_CONFIGURATION_FILE);
         properties = new Properties();
         properties.load(inputStream);
         inputStream.close();
         
         parameters = Collections.synchronizedMap(properties);
		}catch(Throwable t) {
			logger.error(t.getMessage(), t);
		}
	}
	
	public MailServiceImpl() {		
	}

	@Override
	public void sendEmail(String to, String subject, String plainText) throws MailException {
		if(logger.isDebugEnabled()) logger.debug("to: {}; subject: {}; plainText: {}", to, subject, plainText);
		//cargamos el fichero de configuracion
		
		//autenticacion de la cuenta de envio de email
		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						((String) parameters.get("mail.user")), ((String) parameters.get("mail.passwd")));
			}
		});

		try {
			// Creamos el MimeMessage por defecto
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(((String) parameters.get("mail.user"))));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);

			BodyPart body = new MimeBodyPart();

			//inicializamos velocity
			VelocityEngine ve = new VelocityEngine();
			ve.init();
			//recuperamos la template
			Template t = ve.getTemplate("template/mail-template.vm");
			//creamos el context
			VelocityContext context = new VelocityContext();
			context.put("fbody", plainText);

			//Renderizamos la template en un StringWriter
			StringWriter out = new StringWriter();
			t.merge( context, out );

			// velocity stuff ends.

			body.setContent(out.toString(), "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(body);

			body = new MimeBodyPart();

			message.setContent(multipart, "text/html");

			// envio del email
			Transport.send(message);

			if(logger.isDebugEnabled()) logger.debug("Email enviado");

		}catch(MessagingException e) {
			logger.warn(e.getMessage(), e);
		}

	}
}
