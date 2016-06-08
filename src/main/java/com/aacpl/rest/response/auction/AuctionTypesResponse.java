package com.aacpl.rest.response.auction;

import java.util.List;

public class AuctionTypesResponse {
    List<AuctionTypeResponse> auctionTypeResponseList;

    public List<AuctionTypeResponse> getAuctionTypeResponseList() {
        return auctionTypeResponseList;
    }

    public void setAuctionTypeResponseList(List<AuctionTypeResponse> auctionTypeResponseList) {
        this.auctionTypeResponseList = auctionTypeResponseList;
    }

    @Override
    public String toString() {
        return "AuctionTypesResponse{" +
                "auctionTypeResponseList=" + auctionTypeResponseList +
                '}';
    }
}
