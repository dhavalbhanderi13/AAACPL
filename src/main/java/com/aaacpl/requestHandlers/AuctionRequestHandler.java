package com.aaacpl.requestHandlers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.aaacpl.bo.request.auction.CreateAuctionRequestBO;
import com.aaacpl.bo.request.auction.UpdateAuctionRequestBO;
import com.aaacpl.bo.response.AuctionResponseBO;
import com.aaacpl.dao.AuctionDAO;
import com.aaacpl.dto.auction.AuctionDTO;
import com.aaacpl.dto.auction.CreateAuctionDTO;
import com.aaacpl.dto.auction.UpdateAuctionDTO;
import com.aaacpl.util.DateUtil;
import com.aacpl.rest.response.auction.AuctionResponse;

public class AuctionRequestHandler {

	public AuctionResponseBO createAuction(
			CreateAuctionRequestBO createAuctionRequestBO) {
		AuctionDAO auctionDao = new AuctionDAO();
		AuctionResponseBO auctionResponseBO = new AuctionResponseBO();
        Boolean isTenderStartDate = createAuctionRequestBO.getTenderStartDate() != null && !createAuctionRequestBO.getTenderStartDate().equals("");
        Boolean isTenderEndDate = createAuctionRequestBO.getTenderEndDate() != null && !createAuctionRequestBO.getTenderEndDate().equals("");
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
				createAuctionRequestBO.getUpdatedBy(), createAuctionRequestBO.getIsTender() == 1,
                isTenderStartDate ? DateUtil.getTimeStampFromString(createAuctionRequestBO.getTenderStartDate()):null,
                isTenderEndDate ? DateUtil.getTimeStampFromString(createAuctionRequestBO.getTenderEndDate()):null);

		try {

			auctionResponseBO.setId(auctionDao.insertAuction(auctionDTO)
					.getId());
		} catch (SQLException sq) {
			sq.printStackTrace();
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
		}
		return departmentResponseList;
	}

	public List<AuctionResponse> getAllUpcomingAuctions(Boolean isTender) {
		List<AuctionResponse> departmentResponseList = new ArrayList<AuctionResponse>();
		try {
			AuctionDAO auctionDAO = new AuctionDAO();
			List<AuctionDTO> auctionsDTOs = auctionDAO.getUpcomingAuctions(isTender);
			departmentResponseList = buildListOfDepartmentResponseFromDTOs(auctionsDTOs);
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return departmentResponseList;
	}

	public List<AuctionResponse> getLiveAuctions(Boolean isTender) {
		List<AuctionResponse> departmentResponseList = new ArrayList<AuctionResponse>();
		try {
			AuctionDAO auctionDAO = new AuctionDAO();
			List<AuctionDTO> auctionsDTOs = auctionDAO.getLiveAuctions(isTender);
			departmentResponseList = buildListOfDepartmentResponseFromDTOs(auctionsDTOs);
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return departmentResponseList;
	}

	public AuctionResponse getAuctionById(int id) {
		AuctionResponse auctionResponse = null;
		try {
			AuctionDAO auctionDAO = new AuctionDAO();
			AuctionDTO auctionDTO = auctionDAO.getAuctionById(id);
			auctionResponse = buildLotResponseFromDTOs(auctionDTO);
		} catch (SQLException s) {
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
					auctionDTO.getUpdatedBy(),
					auctionDTO.getStatus(),
					auctionDTO.getIsTender(),
                    auctionDTO.getIsTender() == 1 ?
                            DateUtil.getDateStringFromTimeStamp(auctionDTO.getTenderStartDate()):"",
                    auctionDTO.getIsTender() == 1 ?DateUtil.getDateStringFromTimeStamp(auctionDTO.getTenderEndDate()):"");
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
				auctionDTO.getUpdatedBy(),
				auctionDTO.getStatus(),auctionDTO.getIsTender(),
				DateUtil.getDateStringFromTimeStamp(auctionDTO.getTenderStartDate()),
				DateUtil.getDateStringFromTimeStamp(auctionDTO.getTenderEndDate()));
		return auctionResponse;
	}

	public AuctionResponseBO updateAuction(
			UpdateAuctionRequestBO updateAuctionRequestBO) {

		AuctionDAO auctionDao = new AuctionDAO();

		AuctionResponseBO auctionResponseBO = new AuctionResponseBO();
        Boolean isTenderStartDate = updateAuctionRequestBO.getTenderStartDate() != null && !updateAuctionRequestBO.getTenderStartDate().equals("");
        Boolean isTenderEndDate = updateAuctionRequestBO.getTenderEndDate() != null && !updateAuctionRequestBO.getTenderEndDate().equals("");
		UpdateAuctionDTO auctionDTO = new UpdateAuctionDTO(
				updateAuctionRequestBO.getId(),
				updateAuctionRequestBO.getStatus(),
				updateAuctionRequestBO.getName(),
				updateAuctionRequestBO.getAuctionTypeId(),
				updateAuctionRequestBO.getDescription(),
				updateAuctionRequestBO.getDeptId(),
				DateUtil.getTimeStampFromString(updateAuctionRequestBO
						.getStartDate()),
				DateUtil.getTimeStampFromString(updateAuctionRequestBO
						.getEndDate()), updateAuctionRequestBO.getCatalog(),
				updateAuctionRequestBO.getUpdatedBy(),
                updateAuctionRequestBO.getTender(),
                isTenderStartDate ? DateUtil.getTimeStampFromString(updateAuctionRequestBO.getTenderStartDate()): null,
                isTenderEndDate ? DateUtil.getTimeStampFromString(updateAuctionRequestBO.getTenderEndDate()): null);

		try {

			auctionResponseBO.setId(auctionDao.updateAuction(auctionDTO)
					.getId());
		} catch (SQLException sq) {
			sq.printStackTrace();
		}

		return auctionResponseBO;
	}

}
