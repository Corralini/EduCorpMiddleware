package com.educorp.eduinteractive.ecommerce.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
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

	private Logger logger = LogManager.getLogger(MailServiceImpl.class);

	public MailServiceImpl() {		
	}

	@Override
	public void sendEmail(String to, String subject, String plainText) throws MailException {
		if(logger.isDebugEnabled()) logger.debug("to: {}; subject: {}; plainText: {}", to, subject, plainText);
		final Properties prop = System.getProperties();
		//cargamos el fichero de configuracion
		try {
			prop.load(new FileInputStream(new File("config/mail-settings.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//autenticacion de la cuenta de envio de email
		Session session = Session.getDefaultInstance(prop,
				new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						prop.getProperty("mail.user"), prop.getProperty("mail.passwd"));
			}
		});

		try {
			// Creamos el MimeMessage por defecto
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(prop.getProperty("mail.user")));
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
