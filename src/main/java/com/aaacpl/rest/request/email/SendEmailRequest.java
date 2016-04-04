package com.aaacpl.rest.request.email;

import java.util.List;

public class SendEmailRequest {

	private List<String> emailTo;
	private String subject;
	private String body;

	public void setEmailTo(List<String> emailTo) {
		this.emailTo = emailTo;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	@Override
	public String toString() {
		return "SendEmailRequest [emailTo=" + emailTo + ", subject=" + subject
				+ ", body=" + body + "]";
	}
}
