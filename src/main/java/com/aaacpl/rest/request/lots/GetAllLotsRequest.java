package com.aaacpl.rest.request.lots;

public class GetAllLotsRequest {
    private int auctionId;

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    @Override
    public String toString() {
        return "GetAllLotsRequest{" +
                "auctionId=" + auctionId +
                '}';
    }
}
