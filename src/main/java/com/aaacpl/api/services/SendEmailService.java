package com.aaacpl.api.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aaacpl.rest.request.email.SendEmailRequest;
import com.aaacpl.rest.response.email.SendEmailResponse;
import com.aaacpl.rest.response.requestAuth.RequestAuthenticationResponse;
import com.aaacpl.rest.util.EmailService;
import com.aaacpl.rest.util.ResponseGenerator;
import com.aaacpl.validation.RequestValidation;

@Path("/email")
public class SendEmailService {

    @POST
    @Path("/bulkEmail")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendEmailList(SendEmailRequest emailRequest, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
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
        } else {
            RequestAuthenticationResponse requestAuthenticationResponse = new RequestAuthenticationResponse();
            requestAuthenticationResponse.setFailureMessage("Unauthorized access");
            return ResponseGenerator.generateResponse(requestAuthenticationResponse);
        }
    }
}
