package com.aaacpl.rest.util;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.aaacpl.rest.request.email.SendEmailRequest;

public class EmailService {

	private static final String USERNAME = "eauction@aaacpl.com";
	private static final String PASSWORD = "e@ucTion$123";
	private static final String HOST = "bh-in-5.webhostbox.net";
	private static final String FROM = "eauction@aaacpl.com";
	private static final Session session = getSession();

	public static boolean sendNewUserEmail(String to, int userId) {
		Boolean isProcessed = Boolean.FALSE;
		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(FROM));

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			message.setSubject("Please confirm Your Email to AACPL");

			message.setText("Welcome! Thanks for signing up.Please follow this link to activate your Account\n\nhttp://eauction.aaacpl.com/rest/user/confirm/"+userId+"\n\nRegards,\nAACPL");

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

			message.setSubject("AAACPL Password");

			message.setText("Your Password is : \"" + UserPassword + "\"");

			Transport.send(message);

			isProcessed = Boolean.TRUE;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return isProcessed;
	}

	public static boolean sendEmails(SendEmailRequest request) {
		Boolean isProcessed = Boolean.FALSE;
		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(FROM));

			message.setSubject(request.getSubject());

			message.setText(request.getBody());
			for (String to : request.getEmailTo()) {
				send(to, message);
			}

			sendAcknowledgementEmail(request.getAcknowledgementEmail(),
					request.getEmailTo());
			isProcessed = Boolean.TRUE;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return isProcessed;
	}

	private static Boolean sendAcknowledgementEmail(String to,
			List<String> recipents) {
		Boolean isProcessed = Boolean.FALSE;

		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(FROM));

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("kohinoor@aaacpl.com"));

			message.setSubject("Notification Email is send");

			message.setText("Notification Email is send to following Recipents : \n"
					+ recipents.toString());

			Transport.send(message);

			isProcessed = Boolean.TRUE;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return isProcessed;
	}

	private static void send(String to, Message message)
			throws MessagingException {
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
		Transport.send(message);
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
