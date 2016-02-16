package com.aaacpl.requestHandlers;

import com.aaacpl.bo.request.participator.CreateParticipatorRequestBO;
import com.aaacpl.dao.UserLotMapDAO;
import com.aaacpl.dto.participator.CreateParticipatorDTO;
import com.aaacpl.rest.request.participator.ParticipatorInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class ParticipatorRequestHandler {
    public Boolean createParticipator(CreateParticipatorRequestBO createParticipatorRequestBO) {
        Boolean isCreated = Boolean.FALSE;
        List<ParticipatorInfo> participatorInfoList = createParticipatorRequestBO.getParticipatorInfoList();
        Iterator<ParticipatorInfo> participatorInfoIterator = participatorInfoList.iterator();
        UserLotMapDAO userLotMapDAO = new UserLotMapDAO();
        int counter = 0;
        try {
            while (participatorInfoIterator.hasNext()) {
                ParticipatorInfo participatorInfo = participatorInfoIterator.next();
                CreateParticipatorDTO createParticipatorDTO = new CreateParticipatorDTO();
                createParticipatorDTO.setLotId(participatorInfo.getLotId());
                createParticipatorDTO.setUserIds(participatorInfo.getUserIdList());
                Boolean isInserted = userLotMapDAO.insertUserLotMapping(createParticipatorDTO);
                if (isInserted) {
                    counter++;
                }
            }
            if (counter == participatorInfoList.size()) {
                isCreated = Boolean.TRUE;
            }
        } catch (SQLException s) {
            s.printStackTrace();
            isCreated = Boolean.FALSE;
        } catch (IOException e) {
            e.printStackTrace();
            isCreated = Boolean.FALSE;
        }
        return isCreated;
    }
}
