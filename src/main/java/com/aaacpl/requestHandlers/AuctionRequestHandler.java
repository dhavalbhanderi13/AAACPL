package com.aaacpl.requestHandlers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.aaacpl.bo.request.auction.CreateAuctionRequestBO;
import com.aaacpl.bo.response.AuctionResponseBO;
import com.aaacpl.dao.AuctionDAO;
import com.aaacpl.dto.auction.AuctionDTO;
import com.aaacpl.dto.auction.CreateAuctionDTO;
import com.aacpl.rest.response.auction.AuctionResponse;

public class AuctionRequestHandler {

	public AuctionResponseBO createAuction(CreateAuctionRequestBO createAuctionRequestBO) {
		Boolean isProcessed = Boolean.FALSE;
		AuctionDAO auctionDao = new AuctionDAO();
        AuctionResponseBO auctionResponseBO = new AuctionResponseBO();
		CreateAuctionDTO auctionDTO = new CreateAuctionDTO(
				createAuctionRequestBO.getName(),
				createAuctionRequestBO.getAuctionTypeId(),
				createAuctionRequestBO.getDescription(),
				createAuctionRequestBO.getDeptId(),
				createAuctionRequestBO.getInitialBid(),
				createAuctionRequestBO.getStartDate(),
				createAuctionRequestBO.getEndDate(),
				createAuctionRequestBO.getCatalog(),
				createAuctionRequestBO.getCreatedBy());

		try {

			auctionResponseBO.setId(auctionDao.insertAuction(auctionDTO).getId());
		} catch (SQLException sq) {
            sq.printStackTrace();
		} catch (IOException sqlException) {
            sqlException.printStackTrace();
		}

		return auctionResponseBO;
	}

	public List<AuctionResponse> getAllAuctions(int departmentId) {
		List<AuctionResponse> departmentResponseList = new ArrayList<AuctionResponse>();
		try {
			AuctionDAO auctionDAO = new AuctionDAO();
			List<AuctionDTO> auctionsDTOs = auctionDAO.getAllAuctions(departmentId);
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
                    auctionDTO.getId(),	auctionDTO.getName(),
                    auctionDTO.getAuctionTypeId(), auctionDTO.getDescription(),
					auctionDTO.getDeptId(), auctionDTO.getInitialBid(),
					auctionDTO.getStartDate(), auctionDTO.getEndDate(),
					auctionDTO.getCatalog(), auctionDTO.getCreatedBy());
			auctionResponseList.add(auctionResponse);
		}
		return auctionResponseList;
	}

}
