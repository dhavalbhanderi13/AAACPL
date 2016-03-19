package com.aaacpl.rest.request.email;

import java.util.List;

public class SendEmailRequest {

	private List<String> emailTo;
	private String subject;
	private String body;
	private String acknowledgementEmail;

	public SendEmailRequest(List<String> emailTo, String subject, String body,
			String acknowledgementEmail) {
		this.emailTo = emailTo;
		this.subject = subject;
		this.body = body;
		this.acknowledgementEmail = acknowledgementEmail;

	}

	public List<String> getEmailTo() {
		return emailTo;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public String getAcknowledgementEmail() {
		return acknowledgementEmail;
	}

	@Override
	public String toString() {
		return "SendEmailRequest [emailTo=" + emailTo + ", subject=" + subject
				+ ", body=" + body + ", acknowledgementEmail="
				+ acknowledgementEmail + "]";
	}
}
