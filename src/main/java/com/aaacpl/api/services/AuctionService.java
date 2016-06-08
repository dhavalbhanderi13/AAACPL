package com.aaacpl.api.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aaacpl.bo.request.auction.CreateAuctionRequestBO;
import com.aaacpl.bo.request.auction.UpdateAuctionRequestBO;
import com.aaacpl.bo.response.AuctionResponseBO;
import com.aaacpl.bo.response.ResourceNotFoundResponse;
import com.aaacpl.requestHandlers.AuctionRequestHandler;
import com.aaacpl.rest.exceptions.ResourceNotFoundException;
import com.aaacpl.rest.request.auction.CreateAuctionRequest;
import com.aaacpl.rest.request.auction.UpdateAuctionRequest;
import com.aaacpl.rest.response.requestAuth.RequestAuthenticationResponse;
import com.aaacpl.rest.util.ResponseGenerator;
import com.aaacpl.validation.RequestValidation;
import com.aacpl.rest.response.auction.AuctionTypesResponse;
import com.aacpl.rest.response.auction.AuctionsListResponse;
import com.aacpl.rest.response.auction.CreateAuctionResponse;
import com.aacpl.rest.response.auction.UpdateAuctionResponse;

@Path("/auction")
public class AuctionService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response create(CreateAuctionRequest createAuctionRequest, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {

            CreateAuctionRequestBO createAuctionRequestBO = new CreateAuctionRequestBO(
                    createAuctionRequest.getName(),
                    createAuctionRequest.getAuctionTypeId(),
                    createAuctionRequest.getDescription(),
                    createAuctionRequest.getDeptId(),
                    createAuctionRequest.getStartDate(),
                    createAuctionRequest.getEndDate(),
                    createAuctionRequest.getCatalog(),
                    createAuctionRequest.getCreatedBy(),
                    createAuctionRequest.getUpdatedBy(),
                    createAuctionRequest.getIsAuctionTender(),
                    createAuctionRequest.getTenderStartDate(),
                    createAuctionRequest.getTenderEndDate());

            CreateAuctionResponse createDepartmentResponse = new CreateAuctionResponse();
            AuctionRequestHandler auctionRequestHandler = new AuctionRequestHandler();
            AuctionResponseBO auctionResponseBO = auctionRequestHandler
                    .createAuction(createAuctionRequestBO);
            if (auctionResponseBO.getId() != 0) {
                createDepartmentResponse.setFailureMessage("");
                createDepartmentResponse.setSuccessMessage(String
                        .valueOf(auctionResponseBO.getId()));
            } else {
                createDepartmentResponse.setFailureMessage("FAILURE");
                createDepartmentResponse.setSuccessMessage("");
            }
            return ResponseGenerator.generateResponse(createDepartmentResponse);
        } else {
            return ResponseGenerator.generateResponse(RequestValidation.getUnautheticatedResponse());
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response updateAuction(UpdateAuctionRequest updateAuctionRequest, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            UpdateAuctionRequestBO updateAuctionRequestBO = new UpdateAuctionRequestBO(
                    updateAuctionRequest.getId(), updateAuctionRequest.getStatus(),
                    updateAuctionRequest.getName(),
                    updateAuctionRequest.getAuctionTypeId(),
                    updateAuctionRequest.getDescription(),
                    updateAuctionRequest.getDeptId(),
                    updateAuctionRequest.getStartDate(),
                    updateAuctionRequest.getEndDate(),
                    updateAuctionRequest.getCatalog(),
                    updateAuctionRequest.getUpdatedBy(),
                    updateAuctionRequest.getIsAuctionTender() == 1,
                    updateAuctionRequest.getTenderStartDate(),
                    updateAuctionRequest.getTenderEndDate());

            UpdateAuctionResponse updateAuctionResponse = new UpdateAuctionResponse();
            AuctionRequestHandler auctionRequestHandler = new AuctionRequestHandler();
            AuctionResponseBO auctionResponseBO = auctionRequestHandler
                    .updateAuction(updateAuctionRequestBO);
            if (auctionResponseBO.getId() != 0) {
                updateAuctionResponse.setFailureMessage("");
                updateAuctionResponse.setSuccessMessage(String
                        .valueOf(auctionResponseBO.getId()));
            } else {
                updateAuctionResponse.setFailureMessage("FAILURE");
                updateAuctionResponse.setSuccessMessage("");
            }
            return ResponseGenerator.generateResponse(updateAuctionResponse);
        } else {
            return ResponseGenerator.generateResponse(RequestValidation.getUnautheticatedResponse());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list/{departmentId}")
    public Response getAllAuctions(@PathParam("departmentId") int departmentId, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            AuctionRequestHandler auctionRequestHandler = new AuctionRequestHandler();
            AuctionsListResponse auctionResponseList = new AuctionsListResponse();

            auctionResponseList.setAuctionResponseList(auctionRequestHandler
                    .getAllAuctions(departmentId));
            return ResponseGenerator.generateResponse(auctionResponseList);
        } else {
            return ResponseGenerator.generateResponse(RequestValidation.getUnautheticatedResponse());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/types")
    public Response getAuctionTypes(@HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            AuctionRequestHandler auctionRequestHandler = new AuctionRequestHandler();
            AuctionTypesResponse auctionTypesList = new AuctionTypesResponse();

            auctionTypesList.setAuctionTypeResponseList(auctionRequestHandler
                    .getAuctionTypes());
            return ResponseGenerator.generateResponse(auctionTypesList);
        } else {
            return ResponseGenerator.generateResponse(RequestValidation.getUnautheticatedResponse());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/auctionInfo/{id}")
    public Response getAuctionInfo(@PathParam("id") int id, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            Object response = null;
            try {
                AuctionRequestHandler auctionRequestHandler = new AuctionRequestHandler();
                response = auctionRequestHandler.getAuctionById(id);
            } catch (ResourceNotFoundException l) {
                ResourceNotFoundResponse resourceNotFoundResponse = new ResourceNotFoundResponse();
                resourceNotFoundResponse.setFailureMessage(l.getMessage());
                return ResponseGenerator.generateResponse(resourceNotFoundResponse);
            }
            return ResponseGenerator.generateResponse(response);
        } else {
            return ResponseGenerator.generateResponse(RequestValidation.getUnautheticatedResponse());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/upcoming/{isTender}")
    public Response getUpcomingAuctions(@HeaderParam("sessionId") String sessionId, @PathParam("isTender") Integer isTender) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            AuctionRequestHandler auctionRequestHandler = new AuctionRequestHandler();
            AuctionsListResponse auctionResponseList = new AuctionsListResponse();

            auctionResponseList.setAuctionResponseList(auctionRequestHandler
                    .getAllUpcomingAuctions(isTender == 1));
            return ResponseGenerator.generateResponse(auctionResponseList);
        } else {
            return ResponseGenerator.generateResponse(RequestValidation.getUnautheticatedResponse());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/live/{isTender}")
    public Response getLiveAuctions(@HeaderParam("sessionId") String sessionId, @PathParam("isTender") Integer isTender) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            AuctionRequestHandler auctionRequestHandler = new AuctionRequestHandler();
            AuctionsListResponse auctionResponseList = new AuctionsListResponse();

            auctionResponseList.setAuctionResponseList(auctionRequestHandler
                    .getLiveAuctions(isTender == 1));
            return ResponseGenerator.generateResponse(auctionResponseList);
        } else {
            return ResponseGenerator.generateResponse(RequestValidation.getUnautheticatedResponse());
        }
    }
}
