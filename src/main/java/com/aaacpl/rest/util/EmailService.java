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

	private static final String USERNAME = "eauction@aaacpl.com";
	private static final String PASSWORD = "e@ucTion$123";
	private static final String HOST = "bh-in-5.webhostbox.net";
	private static final String FROM = "eauction@aaacpl.com";
	private static final Session session = getSession();

	public static boolean sendNewUserEmail(String to) {
		Boolean isProcessed = Boolean.FALSE;
		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(FROM));

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			message.setSubject("New User Registered to AACPL");

			message.setText("New User Registered to AACPL");

			Transport.send(message);

			isProcessed = Boolean.TRUE;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return isProcessed;
	}

	public static Boolean sendForgotPasswordEmail(String to, String UserPassword) {
		Boolean isProcessed = Boolean.FALSE;

		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(FROM));

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			message.setSubject("Forgor Password");

			message.setText("Your Password is : \"" + UserPassword + "\"");

			Transport.send(message);

			isProcessed = Boolean.TRUE;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return isProcessed;
	}

	private static Session getSession() {
		if (session == null) {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", HOST);
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(USERNAME,
									PASSWORD);
						}
					});
			return session;
		} else {
			return session;
		}
	}
}
