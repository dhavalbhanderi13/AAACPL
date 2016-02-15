package com.aaacpl.api.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aaacpl.bo.request.auction.CreateAuctionRequestBO;
import com.aaacpl.bo.response.AuctionResponseBO;
import com.aaacpl.requestHandlers.AuctionRequestHandler;
import com.aaacpl.rest.request.auction.CreateAuctionRequest;
import com.aaacpl.rest.util.ResponseGenerator;
import com.aacpl.rest.response.auction.AuctionsListResponse;
import com.aacpl.rest.response.auction.CreateAuctionResponse;

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
				createAuctionRequest.getInitialBid(),
				createAuctionRequest.getStartDate(),
				createAuctionRequest.getEndDate(),
				createAuctionRequest.getCatalog(),
				createAuctionRequest.getCreatedBy());
		CreateAuctionResponse createDepartmentResponse = new CreateAuctionResponse();
		AuctionRequestHandler auctionRequestHandler = new AuctionRequestHandler();
		AuctionResponseBO auctionResponseBO = auctionRequestHandler.createAuction(createAuctionRequestBO);
		if (auctionResponseBO.getId() != 0) {
			createDepartmentResponse.setFailureMessage("");
			createDepartmentResponse.setSuccessMessage(String.valueOf(auctionResponseBO.getId()));
		} else {
			createDepartmentResponse.setFailureMessage("FAILURE");
			createDepartmentResponse.setSuccessMessage("");
		}
		return ResponseGenerator.generateResponse(createDepartmentResponse);
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getAllAuctions() {
		AuctionRequestHandler auctionRequestHandler = new AuctionRequestHandler();
		AuctionsListResponse auctionResponseList = new AuctionsListResponse();
		
		auctionResponseList
				.setAuctionResponseList(auctionRequestHandler
						.getAllAuctions());
		return ResponseGenerator.generateResponse(auctionResponseList);
	}


}
