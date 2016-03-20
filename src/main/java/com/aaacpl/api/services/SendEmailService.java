package com.aaacpl.api.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aaacpl.rest.request.email.SendEmailRequest;
import com.aaacpl.rest.response.email.SendEmailResponse;
import com.aaacpl.rest.util.EmailService;
import com.aaacpl.rest.util.ResponseGenerator;

@Path("/email")
public class SendEmailService {
	
	@POST
	@Path("/bulkEmail")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendEmailList(SendEmailRequest emailRequest) {
		SendEmailResponse emailResponse = new SendEmailResponse();
		if (EmailService.sendEmails(emailRequest)) {

			emailResponse.setSuccessMessage("Email Sending Successful");
			emailResponse.setFailureMessage("");

		} else {
			emailResponse.setSuccessMessage("");
			emailResponse.setFailureMessage("Email Sending Unsuccessful");
		}
		return ResponseGenerator
				.generateResponse(emailResponse);
	}
}
