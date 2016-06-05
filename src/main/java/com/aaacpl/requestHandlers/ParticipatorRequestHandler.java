package com.aaacpl.requestHandlers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.aaacpl.bo.request.participator.CreateParticipatorRequestBO;
import com.aaacpl.dao.UserLotMapDAO;
import com.aaacpl.rest.request.participator.ParticipatorInfo;

public class ParticipatorRequestHandler {
    public Boolean createParticipator(CreateParticipatorRequestBO createParticipatorRequestBO) {
        Boolean isCreated = Boolean.FALSE;
        List<ParticipatorInfo> participatorInfoList = createParticipatorRequestBO.getParticipatorInfoList();
        Iterator<ParticipatorInfo> participatorInfoIterator = participatorInfoList.iterator();
        UserLotMapDAO userLotMapDAO = new UserLotMapDAO();
        try {
            while (participatorInfoIterator.hasNext()) {
                ParticipatorInfo participatorInfo = participatorInfoIterator.next();
                Integer lotId = participatorInfo.getLotId();
                List<Integer> oldParticipatedUsers = userLotMapDAO.getListOfUsers(lotId, null);
                List<Integer> newParticipatorList = participatorInfo.getUserIdList();
                Iterator<Integer> newParticipatorListIterator = newParticipatorList.iterator();
                Iterator<Integer> oldParticipatorListIterator = oldParticipatedUsers.iterator();
                while (newParticipatorListIterator.hasNext()){
                    Integer userId = newParticipatorListIterator.next();
                    if(oldParticipatedUsers.contains(userId)){
                        userLotMapDAO.updateUserLotMapping(userId, lotId, "A");
                    }else{
                        userLotMapDAO.insertUserLotMapping(userId, lotId);
                    }
                }

                while (oldParticipatorListIterator.hasNext()){
                    Integer userId = oldParticipatorListIterator.next();
                    if(!newParticipatorList.contains(userId)){
                        userLotMapDAO.updateUserLotMapping(userId, lotId, "I");
                    }
                }
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
