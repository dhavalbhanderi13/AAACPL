package com.aaacpl.api.services;

import com.aaacpl.bo.request.lots.BidRequestBO;
import com.aaacpl.exceptions.lotServiceException.DuplicateTenderBidException;
import com.aaacpl.requestHandlers.LotsRequestHandler;
import com.aaacpl.rest.request.lots.BidRequest;
import com.aaacpl.rest.response.lots.BidResponse;
import com.aaacpl.rest.util.ResponseGenerator;
import com.aaacpl.validation.RequestValidation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tender")
public class TenderService {
    @POST
    @Path("/bid")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response bidForTender(BidRequest bidRequest, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            BidResponse bidResponse = new BidResponse();
            try {
                BidRequestBO tenderBidRequestBO = new BidRequestBO(bidRequest.getLotId(),
                        bidRequest.getUserId(), bidRequest.getBidAmount(), bidRequest.getIpAddress(),
                        bidRequest.getLocalSystemTime());
                LotsRequestHandler lotsRequestHandler = new LotsRequestHandler();

                if(lotsRequestHandler.insertTenderBid(tenderBidRequestBO)) {
                    bidResponse.setFailureMessage("");
                    bidResponse.setSuccessMessage("Bid was Successful");
                }else{
                    bidResponse.setFailureMessage("");
                    bidResponse.setSuccessMessage("Bid failed");
                }
                return ResponseGenerator.generateResponse(bidResponse);
            }catch (DuplicateTenderBidException d){
                bidResponse.setFailureMessage(d.getMessage());
                bidResponse.setSuccessMessage("");
                return ResponseGenerator.generateResponse(bidResponse);
            }
        } else {
            return ResponseGenerator.generateResponse(RequestValidation.getUnautheticatedResponse());
        }
    }
}
