package com.aaacpl.api.services;

import com.aaacpl.bo.request.lots.CreateLotRequestBO;
import com.aaacpl.bo.response.CreateLotResponseBO;
import com.aaacpl.exceptions.lotServiceException.LotNotFoundException;
import com.aaacpl.requestHandlers.LotsRequestHandler;
import com.aaacpl.rest.request.lots.CreateLotRequest;
import com.aaacpl.rest.request.lots.GetAllLotsRequest;
import com.aaacpl.rest.response.lots.CreateLotResponse;
import com.aaacpl.rest.response.lots.LotNotFoundResponse;
import com.aaacpl.rest.response.lots.LotsListResponse;
import com.aaacpl.rest.response.lots.LotsResponse;
import com.aaacpl.rest.response.user.LoginResponse;
import com.aaacpl.rest.util.ResponseGenerator;

import javax.ws.rs.*;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list/{auctionId}")
    public Response getAllLots(@PathParam("auctionId") int auctionId) {
        LotsRequestHandler lotsRequestHandler = new LotsRequestHandler();
        LotsListResponse lotsListResponse = new LotsListResponse();

        lotsListResponse
                .setLotsResponseList(lotsRequestHandler
                        .getAllLots(auctionId));
        return ResponseGenerator.generateResponse(lotsListResponse);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lotInfo/{id}")
    public Response getLotInfo(@PathParam("id") int id) {
        Object response = null;
        try {
            LotsRequestHandler lotsRequestHandler = new LotsRequestHandler();
            response = lotsRequestHandler.getLotById(id);
        }catch (LotNotFoundException l){
            LotNotFoundResponse lotNotFoundResponse = new LotNotFoundResponse();
            lotNotFoundResponse.setFailureMessage(l.getMessage());
            return ResponseGenerator.generateResponse(lotNotFoundResponse);
        }
        return ResponseGenerator.generateResponse(response);
    }
}
