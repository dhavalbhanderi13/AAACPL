package com.aaacpl.bo.request.participator;

import com.aaacpl.rest.request.participator.ParticipatorInfo;

import java.util.List;

/**
 * Created by Hp on 17-02-2016.
 */
public class CreateParticipatorRequestBO {
    private List<ParticipatorInfo> participatorInfoList;

    public List<ParticipatorInfo> getParticipatorInfoList() {
        return participatorInfoList;
    }

    public void setParticipatorInfoList(List<ParticipatorInfo> participatorInfoList) {
        this.participatorInfoList = participatorInfoList;
    }

    @Override
    public String toString() {
        return "CreateParticipatorRequestBO{" +
                "participatorInfoList=" + participatorInfoList +
                '}';
    }
}
