package com.aaacpl.api.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
import com.aaacpl.rest.util.ResponseGenerator;
import com.aacpl.rest.response.auction.AuctionsListResponse;
import com.aacpl.rest.response.auction.CreateAuctionResponse;
import com.aacpl.rest.response.auction.UpdateAuctionResponse;

@Path("/auction")
public class AuctionService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response create(CreateAuctionRequest createAuctionRequest) {

		CreateAuctionRequestBO createAuctionRequestBO = new CreateAuctionRequestBO(
				createAuctionRequest.getName(),
				createAuctionRequest.getAuctionTypeId(),
				createAuctionRequest.getDescription(),
				createAuctionRequest.getDeptId(),
				createAuctionRequest.getStartDate(),
				createAuctionRequest.getEndDate(),
				createAuctionRequest.getCatalog(),
				createAuctionRequest.getCreatedBy(),
				createAuctionRequest.getUpdatedBy());

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
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateAuction(UpdateAuctionRequest updateAuctionRequest) {

		UpdateAuctionRequestBO updateAuctionRequestBO = new UpdateAuctionRequestBO(
				updateAuctionRequest.getId(), updateAuctionRequest.getStatus(),
				updateAuctionRequest.getName(),
				updateAuctionRequest.getAuctionTypeId(),
				updateAuctionRequest.getDescription(),
				updateAuctionRequest.getDeptId(),
				updateAuctionRequest.getStartDate(),
				updateAuctionRequest.getEndDate(),
				updateAuctionRequest.getCatalog(),
				updateAuctionRequest.getCreatedBy(),
				updateAuctionRequest.getUpdatedBy());

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
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list/{departmentId}")
	public Response getAllAuctions(@PathParam("departmentId") int departmentId) {
		AuctionRequestHandler auctionRequestHandler = new AuctionRequestHandler();
		AuctionsListResponse auctionResponseList = new AuctionsListResponse();

		auctionResponseList.setAuctionResponseList(auctionRequestHandler
				.getAllAuctions(departmentId));
		return ResponseGenerator.generateResponse(auctionResponseList);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/auctionInfo/{id}")
	public Response getAuctionInfo(@PathParam("id") int id) {
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
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/upcoming")
	public Response getUpcomingAuctions() {
		AuctionRequestHandler auctionRequestHandler = new AuctionRequestHandler();
		AuctionsListResponse auctionResponseList = new AuctionsListResponse();

		auctionResponseList.setAuctionResponseList(auctionRequestHandler
				.getAllUpcomingAuctions());
		return ResponseGenerator.generateResponse(auctionResponseList);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/live")
	public Response getLiveAuctions() {
		AuctionRequestHandler auctionRequestHandler = new AuctionRequestHandler();
		AuctionsListResponse auctionResponseList = new AuctionsListResponse();

		auctionResponseList.setAuctionResponseList(auctionRequestHandler
				.getLiveAuctions());
		return ResponseGenerator.generateResponse(auctionResponseList);
	}

}
