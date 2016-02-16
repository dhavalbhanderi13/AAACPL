package com.aaacpl.rest.request.participator;

import java.util.List;

/**
 * Created by Hp on 17-02-2016.
 */
public class ParticipatorInfo {
    private int lotId;
    private List<Integer> userIdList;

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public List<Integer> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Integer> userIdList) {
        this.userIdList = userIdList;
    }

    @Override
    public String toString() {
        return "ParticipatorInfo{" +
                "lotId=" + lotId +
                ", userIdList=" + userIdList +
                '}';
    }
}
