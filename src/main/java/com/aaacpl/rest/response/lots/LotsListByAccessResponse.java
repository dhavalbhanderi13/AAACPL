package com.aaacpl.rest.response.lots;

import java.util.List;

/**
 * Created by Hp on 18-02-2016.
 */
public class LotsListByAccessResponse {
    private List<LotsByAccessResponse> lotsResponseList;

    public List<LotsByAccessResponse> getLotsResponseList() {
        return lotsResponseList;
    }

    public void setLotsResponseList(List<LotsByAccessResponse> lotsResponseList) {
        this.lotsResponseList = lotsResponseList;
    }

    @Override
    public String toString() {
        return "LotsListByAccessResponse{" +
                "lotsResponseList=" + lotsResponseList +
                '}';
    }
}
