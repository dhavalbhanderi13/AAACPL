package com.aaacpl.api.services;

import com.aaacpl.bo.request.participator.CreateParticipatorRequestBO;
import com.aaacpl.requestHandlers.ParticipatorRequestHandler;
import com.aaacpl.rest.request.participator.CreateParticipatorRequest;
import com.aaacpl.rest.response.participator.CreateParticipatorResponse;
import com.aaacpl.rest.util.ResponseGenerator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Hp on 17-02-2016.
 */
@Path("/participator")
public class ParticipatorService {
    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createParticipatorForLots(CreateParticipatorRequest createParticipatorRequest){
        ParticipatorRequestHandler participatorRequestHandler = new ParticipatorRequestHandler();
        CreateParticipatorRequestBO createParticipatorRequestBO = new CreateParticipatorRequestBO();
        createParticipatorRequestBO.setParticipatorInfoList(createParticipatorRequest.getParticipatorInfoList());
        Boolean isCreated = participatorRequestHandler.createParticipator(createParticipatorRequestBO);
        CreateParticipatorResponse createParticipatorResponse= new CreateParticipatorResponse();
        if(isCreated){
            createParticipatorResponse.setSuccessMessage("SUCCESS");
            createParticipatorResponse.setFailureMessage("");
        }else{
            createParticipatorResponse.setSuccessMessage("");
            createParticipatorResponse.setFailureMessage("FAILURE");
        }

        return ResponseGenerator.generateResponse(createParticipatorResponse);
    }
}
