package com.aaacpl.api.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aaacpl.rest.request.email.SendEmailRequest;
import com.aaacpl.rest.util.EmailService;
import com.aaacpl.rest.util.ResponseGenerator;

@Path("/email")
public class SendEmailService {
	
	@POST
	@Path("/bulkEmail")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendEmailList(SendEmailRequest emailRequest) {
		if (EmailService.sendEmails(emailRequest)) {
			return ResponseGenerator
					.generateResponse("Email Sending Successful");
		} else {
			return ResponseGenerator
					.generateResponse("Email Sending Unsuccessful");
		}
	}
}
