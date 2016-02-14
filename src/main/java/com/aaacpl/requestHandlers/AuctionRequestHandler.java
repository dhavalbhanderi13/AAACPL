package com.aaacpl.requestHandlers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.aaacpl.bo.request.auction.CreateAuctionRequestBO;
import com.aaacpl.dao.AuctionDAO;
import com.aaacpl.dto.auction.AuctionDTO;
import com.aaacpl.dto.auction.CreateAuctionDTO;
import com.aacpl.rest.response.auction.AuctionResponse;

public class AuctionRequestHandler {

	public boolean createAuction(CreateAuctionRequestBO createAuctionRequestBO) {
		Boolean isProcessed = Boolean.FALSE;
		AuctionDAO auctionDao = new AuctionDAO();
		CreateAuctionDTO auctionDTO = new CreateAuctionDTO(
				createAuctionRequestBO.getName(),
				createAuctionRequestBO.getDescription(),
				createAuctionRequestBO.getDeptId(),
				createAuctionRequestBO.getInitialBid(),
				createAuctionRequestBO.getStartDate(),
				createAuctionRequestBO.getEndDate(),
				createAuctionRequestBO.getCatalog(),
				createAuctionRequestBO.getCreatedBy());

		try {
			isProcessed = auctionDao.insertAuction(auctionDTO);
		} catch (SQLException sq) {
			isProcessed = false;
		} catch (IOException sqlException) {
			isProcessed = false;
		}

		return isProcessed;
	}

	public List<AuctionResponse> getAllAuctions() {
		List<AuctionResponse> departmentResponseList = new ArrayList<AuctionResponse>();
		try {
			AuctionDAO auctionDAO = new AuctionDAO();
			List<AuctionDTO> auctionsDTOs = auctionDAO.getAllAuctions();
			departmentResponseList = buildListOfDepartmentResponseFromDTOs(auctionsDTOs);
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}
		return departmentResponseList;
	}

	private List<AuctionResponse> buildListOfDepartmentResponseFromDTOs(
			List<AuctionDTO> auctionDTOs) {
		List<AuctionResponse> auctionResponseList = new ArrayList<AuctionResponse>();
		Iterator<AuctionDTO> iterator = auctionDTOs.iterator();
		while (iterator.hasNext()) {
			AuctionDTO auctionDTO = iterator.next();
			AuctionResponse auctionResponse = new AuctionResponse(
					auctionDTO.getName(), auctionDTO.getDescription(),
					auctionDTO.getDeptId(), auctionDTO.getInitialBid(),
					auctionDTO.getStartDate(), auctionDTO.getEndDate(),
					auctionDTO.getCatalog(), auctionDTO.getCreatedBy());
			auctionResponseList.add(auctionResponse);
		}
		return auctionResponseList;
	}

}
