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
import com.aaacpl.util.DateUtil;
import com.aacpl.rest.response.auction.AuctionResponse;

public class AuctionRequestHandler {

	public AuctionResponseBO createAuction(
			CreateAuctionRequestBO createAuctionRequestBO) {
		AuctionDAO auctionDao = new AuctionDAO();
		AuctionResponseBO auctionResponseBO = new AuctionResponseBO();
		CreateAuctionDTO auctionDTO = new CreateAuctionDTO(
				createAuctionRequestBO.getName(),
				createAuctionRequestBO.getAuctionTypeId(),
				createAuctionRequestBO.getDescription(),
				createAuctionRequestBO.getDeptId(),
				DateUtil.getTimeStampFromString(createAuctionRequestBO
						.getStartDate()),
				DateUtil.getTimeStampFromString(createAuctionRequestBO
						.getEndDate()), createAuctionRequestBO.getCatalog(),
				createAuctionRequestBO.getCreatedBy(),
				createAuctionRequestBO.getUpdatedBy());

		try {

			auctionResponseBO.setId(auctionDao.insertAuction(auctionDTO)
					.getId());
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
			List<AuctionDTO> auctionsDTOs = auctionDAO
					.getAllAuctions(departmentId);
			departmentResponseList = buildListOfDepartmentResponseFromDTOs(auctionsDTOs);
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}
		return departmentResponseList;
	}

	public List<AuctionResponse> getAllUpcomingAuctions() {
		List<AuctionResponse> departmentResponseList = new ArrayList<AuctionResponse>();
		try {
			AuctionDAO auctionDAO = new AuctionDAO();
			List<AuctionDTO> auctionsDTOs = auctionDAO
					.getUpcomingAuctions();
			departmentResponseList = buildListOfDepartmentResponseFromDTOs(auctionsDTOs);
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}
		return departmentResponseList;
	}

    public List<AuctionResponse> getLiveAuctions() {
		List<AuctionResponse> departmentResponseList = new ArrayList<AuctionResponse>();
		try {
			AuctionDAO auctionDAO = new AuctionDAO();
			List<AuctionDTO> auctionsDTOs = auctionDAO
					.getLiveAuctions();
			departmentResponseList = buildListOfDepartmentResponseFromDTOs(auctionsDTOs);
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}
		return departmentResponseList;
	}

	public AuctionResponse getAuctionById(int id) {
		AuctionResponse auctionResponse = null;
		try {
			AuctionDAO auctionDAO = new AuctionDAO();
			AuctionDTO auctionDTO = auctionDAO.getLotById(id);
			auctionResponse = buildLotResponseFromDTOs(auctionDTO);
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}
		return auctionResponse;
	}

	private List<AuctionResponse> buildListOfDepartmentResponseFromDTOs(
			List<AuctionDTO> auctionDTOs) {
		List<AuctionResponse> auctionResponseList = new ArrayList<AuctionResponse>();
		Iterator<AuctionDTO> iterator = auctionDTOs.iterator();
		while (iterator.hasNext()) {
			AuctionDTO auctionDTO = iterator.next();
			AuctionResponse auctionResponse = new AuctionResponse(
					auctionDTO.getId(),
					auctionDTO.getName(),
					auctionDTO.getAuctionTypeId(),
					auctionDTO.getDescription(),
					auctionDTO.getDeptId(),
					DateUtil.getDateStringFromTimeStamp(auctionDTO
							.getStartDate()),
					DateUtil.getDateStringFromTimeStamp(auctionDTO.getEndDate()),
					auctionDTO.getCatalog(), auctionDTO.getCreatedBy(),
					auctionDTO.getUpdatedBy());
			auctionResponseList.add(auctionResponse);
		}
		return auctionResponseList;
	}

	private AuctionResponse buildLotResponseFromDTOs(AuctionDTO auctionDTO) {
		AuctionResponse auctionResponse = new AuctionResponse(
				auctionDTO.getId(), auctionDTO.getName(),
				auctionDTO.getAuctionTypeId(), auctionDTO.getDescription(),
				auctionDTO.getDeptId(),
				DateUtil.getDateStringFromTimeStamp(auctionDTO.getStartDate()),
				DateUtil.getDateStringFromTimeStamp(auctionDTO.getEndDate()),
				auctionDTO.getCatalog(), auctionDTO.getCreatedBy(),
				auctionDTO.getUpdatedBy());
		return auctionResponse;
	}

}
