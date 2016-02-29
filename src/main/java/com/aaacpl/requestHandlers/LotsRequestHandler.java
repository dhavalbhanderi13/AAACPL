package com.aaacpl.requestHandlers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.aaacpl.bo.request.lots.BidRequestBO;
import com.aaacpl.bo.request.lots.CreateLotRequestBO;
import com.aaacpl.bo.request.lots.UpdateLotBO;
import com.aaacpl.bo.response.CreateLotResponseBO;
import com.aaacpl.bo.response.UpdateLotResponseBO;
import com.aaacpl.dao.LotsDAO;
import com.aaacpl.dao.UserLotMapDAO;
import com.aaacpl.dto.lots.CreateLotRequestDTO;
import com.aaacpl.dto.lots.LotDTO;
import com.aaacpl.dto.lots.LotStatusDTO;
import com.aaacpl.rest.request.lots.StatusRequest;
import com.aaacpl.rest.response.lots.BidHistoryResponse;
import com.aaacpl.rest.response.lots.LotStatusResponse;
import com.aaacpl.rest.response.lots.LotsByAccessResponse;
import com.aaacpl.rest.response.lots.LotsResponse;
import com.aaacpl.util.DateUtil;

public class LotsRequestHandler {
	public CreateLotResponseBO createLot(CreateLotRequestBO createLotRequestBO) {
		CreateLotResponseBO createLotResponseBO = null;
		LotsDAO lotsDAO = new LotsDAO();
		try {
			createLotResponseBO = new CreateLotResponseBO(lotsDAO.createLot(
					buildCreateLotRequestDTOFromBO(createLotRequestBO)).getId());
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return createLotResponseBO;
	}

	private CreateLotRequestDTO buildCreateLotRequestDTOFromBO(
			CreateLotRequestBO createLotRequestBO) {
		CreateLotRequestDTO createLotRequestDTO = new CreateLotRequestDTO(
				createLotRequestBO.getAuctionId(),
				createLotRequestBO.getName(),
				createLotRequestBO.getDescription(),
				createLotRequestBO.getStartBid(),
				createLotRequestBO.getDifferenceFactor(),
				DateUtil.getTimeStampFromString(createLotRequestBO
						.getStartDate()),
				DateUtil.getTimeStampFromString(createLotRequestBO.getEndDate()),
				createLotRequestBO.getCreatedBy(), createLotRequestBO
						.getUpdatedBy());
		return createLotRequestDTO;
	}

	public List<LotsResponse> getAllLots(int auctionId) {
		List<LotsResponse> departmentResponseList = new ArrayList<LotsResponse>();
		try {
			LotsDAO lotsDAO = new LotsDAO();
			List<LotDTO> lotDTOs = lotsDAO.getAllLots(auctionId);
			departmentResponseList = buildListOfLotsFromDTOs(lotDTOs);
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}
		return departmentResponseList;
	}

	public LotsResponse getLotById(int id) {
		LotsResponse lotsResponse = null;
		try {
			LotsDAO lotsDAO = new LotsDAO();
			LotDTO lotDTO = lotsDAO.getLotById(id);
			lotsResponse = buildLotResponseFromDTOs(lotDTO);
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}
		return lotsResponse;
	}

	private List<LotsResponse> buildListOfLotsFromDTOs(List<LotDTO> lotDTOs)
			throws SQLException, IOException {
		List<LotsResponse> lotsResponseList = new ArrayList<LotsResponse>();
		Iterator<LotDTO> iterator = lotDTOs.iterator();
		while (iterator.hasNext()) {
			LotDTO lotDTO = iterator.next();
			LotsResponse lotsResponse = new LotsResponse(lotDTO.getId(),
					lotDTO.getAuctionId(), lotDTO.getName(),
					lotDTO.getDescription(), lotDTO.getStartBid(),
					lotDTO.getDifferenceFactor(),
					DateUtil.getDateStringFromTimeStamp(lotDTO.getStartDate()),
					DateUtil.getDateStringFromTimeStamp(lotDTO.getEndDate()),
					lotDTO.getCreatedBy(), lotDTO.getUpdatedBy(),
					new UserLotMapDAO().getListOfUsers(lotDTO.getId()));
			lotsResponseList.add(lotsResponse);
		}
		return lotsResponseList;
	}

	private List<LotsByAccessResponse> buildListOfLotsByAccessFromDTOs(
			List<LotDTO> lotDTOs) throws SQLException, IOException {
		List<LotsByAccessResponse> lotsResponseList = new ArrayList<LotsByAccessResponse>();
		Iterator<LotDTO> iterator = lotDTOs.iterator();
		while (iterator.hasNext()) {
			LotDTO lotDTO = iterator.next();
			LotsByAccessResponse lotsResponse = new LotsByAccessResponse(
					lotDTO.getId(), lotDTO.getAuctionId(), lotDTO.getName(),
					lotDTO.getDescription(), lotDTO.getStartBid(),
					lotDTO.getDifferenceFactor(),
					DateUtil.getDateStringFromTimeStamp(lotDTO.getStartDate()),
					DateUtil.getDateStringFromTimeStamp(lotDTO.getEndDate()),
					lotDTO.getCreatedBy(), lotDTO.getUpdatedBy());
			lotsResponseList.add(lotsResponse);
		}
		return lotsResponseList;
	}

	private LotsResponse buildLotResponseFromDTOs(LotDTO lotDTO)
			throws SQLException, IOException {
		LotsResponse lotsResponse = new LotsResponse(lotDTO.getId(),
				lotDTO.getAuctionId(), lotDTO.getName(),
				lotDTO.getDescription(), lotDTO.getStartBid(),
				lotDTO.getDifferenceFactor(),
				DateUtil.getDateStringFromTimeStamp(lotDTO.getStartDate()),
				DateUtil.getDateStringFromTimeStamp(lotDTO.getEndDate()),
				lotDTO.getCreatedBy(), lotDTO.getUpdatedBy(),
				new UserLotMapDAO().getListOfUsers(lotDTO.getId()));
		return lotsResponse;
	}

	public List<LotsByAccessResponse> getLotsByAccess(int userId, int auctionId) {
		List<LotsByAccessResponse> lotsResponseList = new ArrayList<LotsByAccessResponse>();
		try {
			LotsDAO lotsDAO = new LotsDAO();
			lotsResponseList = buildListOfLotsByAccessFromDTOs(lotsDAO
					.getLotsByUser(userId, auctionId));
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}
		return lotsResponseList;
	}

	public synchronized Boolean insertBid(BidRequestBO bidRequestBO) {
		LotsDAO lotsDAO = new LotsDAO();
		boolean isProcessed = false;
		try {
			if (lotsDAO.insertBid(bidRequestBO)) {
				isProcessed = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isProcessed;
	}

	public LotStatusResponse getLotStatus(StatusRequest statusRequest) {
		LotStatusResponse lotResponse = null;
		try {
			LotsDAO lotsDAO = new LotsDAO();
			LotStatusDTO lotStatusDto = lotsDAO.getLotStatus(statusRequest);
			if(lotStatusDto != null) {
				lotResponse = new LotStatusResponse(lotStatusDto.getHighestBid(),
						lotStatusDto.getHigestBidUser(),
						lotStatusDto.getCurrentServerTime(),
						lotStatusDto.getHasHigestBidChanged());
			}else{
				lotResponse = new LotStatusResponse(0,
						0,
						"",
						false);
			}

		} catch (SQLException s) {
			s.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}
		return lotResponse;
	}

	public UpdateLotResponseBO updateLot(UpdateLotBO updateLotRequestBO) {
		UpdateLotResponseBO updateLotResponse = null;
		try {
			LotsDAO lotsDAO = new LotsDAO();
			updateLotResponse = lotsDAO.updateLot(updateLotRequestBO);

		} catch (SQLException s) {
			s.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}
		return updateLotResponse;
	}

	public List<BidHistoryResponse> getBidHistory(int lotId) {
		List<BidHistoryResponse> bidHistoryList = null;
		try {
			LotsDAO lotsDAO = new LotsDAO();
			bidHistoryList = lotsDAO.getBidHistoryList(lotId);

		} catch (SQLException s) {
			s.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}
		return bidHistoryList;
	}
}
