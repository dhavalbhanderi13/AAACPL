package com.aaacpl.rest.request.participator;

import java.util.List;

public class CreateParticipatorRequest {
    private List<ParticipatorInfo> participatorInfoList;

    public List<ParticipatorInfo> getParticipatorInfoList() {
        return participatorInfoList;
    }

    public void setParticipatorInfoList(List<ParticipatorInfo> participatorInfoList) {
        this.participatorInfoList = participatorInfoList;
    }

    @Override
    public String toString() {
        return "CreateParticipatorRequest{" +
                "participatorInfoList=" + participatorInfoList +
                '}';
    }
}
