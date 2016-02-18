package com.aaacpl.api.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aaacpl.bo.request.lots.BidRequestBO;
import com.aaacpl.bo.request.lots.CreateLotRequestBO;
import com.aaacpl.bo.response.CreateLotResponseBO;
import com.aaacpl.exceptions.lotServiceException.LotNotFoundException;
import com.aaacpl.requestHandlers.LotsRequestHandler;
import com.aaacpl.rest.request.lots.BidRequest;
import com.aaacpl.rest.request.lots.CreateLotRequest;
import com.aaacpl.rest.response.lots.BidResponse;
import com.aaacpl.rest.response.lots.CreateLotResponse;
import com.aaacpl.rest.response.lots.LotNotFoundResponse;
import com.aaacpl.rest.response.lots.LotStatusResponse;
import com.aaacpl.rest.response.lots.LotsListResponse;
import com.aaacpl.rest.util.ResponseGenerator;

@Path("/lots")
public class LotsService {

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createLot(CreateLotRequest createLotRequest) {
		CreateLotRequestBO createLotRequestBO = new CreateLotRequestBO(
				createLotRequest.getAuctionId(), createLotRequest.getName(),
				createLotRequest.getDescription(),
				createLotRequest.getStartBid(),
				createLotRequest.getDifferenceFactor(),
				createLotRequest.getStartDate(), createLotRequest.getEndDate(),
				createLotRequest.getCreatedBy(),
				createLotRequest.getUpdatedBy());

		CreateLotResponse createLotResponse = new CreateLotResponse();
		LotsRequestHandler lotsRequestHandler = new LotsRequestHandler();
		CreateLotResponseBO createLotResponseBO = lotsRequestHandler
				.createLot(createLotRequestBO);
		if (createLotResponseBO.getId() != 0) {
			createLotResponse.setSuccessMessage(String
					.valueOf(createLotResponseBO.getId()));
			createLotResponse.setFailureMessage("");
		} else {
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

		lotsListResponse.setLotsResponseList(lotsRequestHandler
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
		} catch (LotNotFoundException l) {
			LotNotFoundResponse lotNotFoundResponse = new LotNotFoundResponse();
			lotNotFoundResponse.setFailureMessage(l.getMessage());
			return ResponseGenerator.generateResponse(lotNotFoundResponse);
		}
		return ResponseGenerator.generateResponse(response);
	}

	@GET
	@Path("/byAccess/auction/{auctionId}/user/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLotsByUserAccess(@PathParam("userId") int userId, @PathParam("auctionId") int auctionId) {
		LotsRequestHandler lotsRequestHandler = new LotsRequestHandler();
		LotsListResponse lotsListResponse = new LotsListResponse();

		lotsListResponse.setLotsResponseList(lotsRequestHandler
				.getLotsByAccess(userId, auctionId));
		return ResponseGenerator.generateResponse(lotsListResponse);
	}

	@POST
	@Path("/bid")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertBid(BidRequest bidRequest) {
		BidRequestBO bidRequestBO = new BidRequestBO(bidRequest.getLotId(),
				bidRequest.getUserId(), bidRequest.getBidAmount(),
				bidRequest.getIpAddress(), bidRequest.getLocalSystemTime());

		BidResponse bidResponse = new BidResponse();
		LotsRequestHandler lotsRequestHandler = new LotsRequestHandler();
		if (lotsRequestHandler.insertBid(bidRequestBO)) {
			bidResponse.setFailureMessage("");
			bidResponse.setSuccessMessage("Bid was Successful");
		} else {
			bidResponse.setFailureMessage("Bid Request Failed");
			bidResponse.setSuccessMessage("");
		}
		return ResponseGenerator.generateResponse(bidResponse);
	}

	@POST
	@Path("/status/{lotId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatus(@PathParam("lotId") int lotId) {

		LotsRequestHandler lotsRequestHandler = new LotsRequestHandler();
		LotStatusResponse lotStatusresponse = lotsRequestHandler
				.getLotStatus(lotId);
		return ResponseGenerator.generateResponse(lotStatusresponse);
	}
}
