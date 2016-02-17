package com.aaacpl.requestHandlers;


import com.aaacpl.bo.request.lots.CreateLotRequestBO;
import com.aaacpl.bo.response.CreateLotResponseBO;
import com.aaacpl.dao.AuctionDAO;
import com.aaacpl.dao.LotsDAO;
import com.aaacpl.dao.UserLotMapDAO;
import com.aaacpl.dto.auction.AuctionDTO;
import com.aaacpl.dto.lots.CreateLotRequestDTO;
import com.aaacpl.dto.lots.LotDTO;
import com.aaacpl.rest.response.lots.CreateLotResponse;
import com.aaacpl.rest.response.lots.LotsResponse;
import com.aaacpl.util.DateUtil;
import com.aacpl.rest.response.auction.AuctionResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LotsRequestHandler {
    public CreateLotResponseBO createLot(CreateLotRequestBO createLotRequestBO) {
        CreateLotResponseBO createLotResponseBO = null;
        LotsDAO lotsDAO = new LotsDAO();
        try {
            createLotResponseBO = new CreateLotResponseBO(lotsDAO.createLot(buildCreateLotRequestDTOFromBO(createLotRequestBO)).getId());
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return createLotResponseBO;
    }

    private CreateLotRequestDTO buildCreateLotRequestDTOFromBO(CreateLotRequestBO createLotRequestBO) {
        CreateLotRequestDTO createLotRequestDTO = new CreateLotRequestDTO(
                createLotRequestBO.getAuctionId(),
                createLotRequestBO.getName(),
                createLotRequestBO.getDescription(),
                createLotRequestBO.getStartBid(),
                createLotRequestBO.getDifferenceFactor(),
                DateUtil.getTimeStampFromString(createLotRequestBO.getStartDate()),
                DateUtil.getTimeStampFromString(createLotRequestBO.getEndDate()),
                createLotRequestBO.getCreatedBy()
        );
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

    private List<LotsResponse> buildListOfLotsFromDTOs(
            List<LotDTO> lotDTOs) {
        List<LotsResponse> lotsResponseList = new ArrayList<LotsResponse>();
        Iterator<LotDTO> iterator = lotDTOs.iterator();
        while (iterator.hasNext()) {
            LotDTO lotDTO = iterator.next();
            LotsResponse auctionResponse = new LotsResponse(
                    lotDTO.getId(),
                    lotDTO.getAuctionId(),
                    lotDTO.getName(),
                    lotDTO.getDescription(),
                    lotDTO.getStartBid(),
                    lotDTO.getDifferenceFactor(),
                    DateUtil.getDateStringFromTimeStamp(lotDTO.getStartDate()),
                    DateUtil.getDateStringFromTimeStamp(lotDTO.getEndDate()),
                    lotDTO.getCreatedBy(),
                    lotDTO.getUpdatedBy());
            lotsResponseList.add(auctionResponse);
        }
        return lotsResponseList;
    }

    private LotsResponse buildLotResponseFromDTOs(LotDTO lotDTO) {
        LotsResponse lotsResponse = new LotsResponse(
                lotDTO.getId(),
                lotDTO.getAuctionId(),
                lotDTO.getName(),
                lotDTO.getDescription(),
                lotDTO.getStartBid(),
                lotDTO.getDifferenceFactor(),
                DateUtil.getDateStringFromTimeStamp(lotDTO.getStartDate()),
                DateUtil.getDateStringFromTimeStamp(lotDTO.getEndDate()),
                lotDTO.getCreatedBy(),
                lotDTO.getUpdatedBy());
        return lotsResponse;
    }

    public List<LotsResponse> getLotsByAccess(int userId) {
        List<LotsResponse> lotsResponseList = new ArrayList<LotsResponse>();
        try {
            LotsDAO lotsDAO = new LotsDAO();
            lotsResponseList = buildListOfLotsFromDTOs(lotsDAO.getLotsByUser(userId));
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (IOException s) {
            s.printStackTrace();
        }
        return lotsResponseList;
    }
}
