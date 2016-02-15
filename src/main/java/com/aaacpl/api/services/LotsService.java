package com.aaacpl.api.services;

import com.aaacpl.bo.request.lots.CreateLotRequestBO;
import com.aaacpl.bo.response.CreateLotResponseBO;
import com.aaacpl.requestHandlers.LotsRequestHandler;
import com.aaacpl.rest.request.lots.CreateLotRequest;
import com.aaacpl.rest.response.lots.CreateLotResponse;
import com.aaacpl.rest.util.ResponseGenerator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/lots")
public class LotsService {

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLot(CreateLotRequest createLotRequest){
        CreateLotRequestBO createLotRequestBO = new CreateLotRequestBO(
                createLotRequest.getAuctionId(),
                createLotRequest.getName(),
                createLotRequest.getDescription(),
                createLotRequest.getStartBid(),
                createLotRequest.getDifferenceFactor(),
                createLotRequest.getStartDate(),
                createLotRequest.getEndDate(),
                createLotRequest.getCreatedBy());

        CreateLotResponse createLotResponse = new CreateLotResponse();
        LotsRequestHandler lotsRequestHandler = new LotsRequestHandler();
        CreateLotResponseBO createLotResponseBO = lotsRequestHandler.createLot(createLotRequestBO);
        if(createLotResponseBO.getId() != 0){
            createLotResponse.setSuccessMessage(String.valueOf(createLotResponseBO.getId()));
            createLotResponse.setFailureMessage("");
        }else{
            createLotResponse.setSuccessMessage("");
            createLotResponse.setFailureMessage("FAILURE");
        }
        return ResponseGenerator.generateResponse(createLotResponse);
    }
}
