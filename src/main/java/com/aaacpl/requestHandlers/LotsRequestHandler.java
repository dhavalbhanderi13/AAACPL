package com.aaacpl.requestHandlers;


import com.aaacpl.bo.request.lots.CreateLotRequestBO;
import com.aaacpl.bo.response.CreateLotResponseBO;
import com.aaacpl.dao.LotsDAO;
import com.aaacpl.dto.lots.CreateLotRequestDTO;
import com.aaacpl.rest.response.lots.CreateLotResponse;

import java.io.IOException;
import java.sql.SQLException;

public class LotsRequestHandler {
    public CreateLotResponseBO createLot(CreateLotRequestBO createLotRequestBO){
        CreateLotResponseBO createLotResponseBO = null;
        LotsDAO lotsDAO = new LotsDAO();
        try{
            createLotResponseBO = new CreateLotResponseBO(lotsDAO.createLot(buildCreateLotRequestDTOFromBO(createLotRequestBO)).getId());
        }catch (SQLException s){
            s.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return createLotResponseBO;
    }

    private CreateLotRequestDTO buildCreateLotRequestDTOFromBO(CreateLotRequestBO createLotRequestBO){
        CreateLotRequestDTO createLotRequestDTO = new CreateLotRequestDTO(
                createLotRequestBO.getAuctionId(),
                createLotRequestBO.getName(),
                createLotRequestBO.getDescription(),
                createLotRequestBO.getStartBid(),
                createLotRequestBO.getDifferenceFactor(),
                createLotRequestBO.getStartDate(),
                createLotRequestBO.getEndDate(),
                createLotRequestBO.getCreatedBy()
        );
        return createLotRequestDTO;
    }
}
