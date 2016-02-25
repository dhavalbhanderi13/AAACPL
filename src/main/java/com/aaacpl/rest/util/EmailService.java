package com.aaacpl.rest.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

	private String username;
	private String password;
	private String host;
	private String from;

	public EmailService(String from, String username, String password,
			String host) {
		this.from = from;
		this.username = username;
		this.password = password;
		this.host = host;
	}

	public boolean sendNewUserEmail(String to) {
		Boolean isProcessed = Boolean.FALSE;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("New User Registered to AACPL");

			// Now set the actual message
			message.setText("New User Registered to AACPL");

			// Send message
			Transport.send(message);
			isProcessed = Boolean.TRUE;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return isProcessed;
	}

	public Boolean sendForgotPasswordEmail(String to, String UserPassword) {
		Boolean isProcessed = Boolean.FALSE;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Forgor Password");

			// Now set the actual message
			message.setText("Your Password is : \"" + UserPassword + "\"");

			// Send message
			Transport.send(message);
			isProcessed = Boolean.TRUE;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return isProcessed;
	}
}
