package com.aaacpl.dto.participator;

import java.util.List;

/**
 * Created by Hp on 17-02-2016.
 */
public class CreateParticipatorDTO {
    private int lotId;
    private List<Integer> userIds;

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "CreateParticipatorDTO{" +
                "lotId=" + lotId +
                ", userIds=" + userIds +
                '}';
    }
}
