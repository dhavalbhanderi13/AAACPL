package com.aaacpl.rest.request.lots;

public class StatusRequest {

    private Integer lotid;
    private Integer currentBidMax;

    public Integer getCurrentBidMax() {
        return currentBidMax;
    }

    public void setCurrentBidMax(Integer currentBidMax) {
        this.currentBidMax = currentBidMax;
    }

    public Integer getLotid() {
        return lotid;
    }

    public void setLotid(Integer lotid) {
        this.lotid = lotid;
    }

}
