package com.aaacpl.dto.lots;

public class LotStatusDTO {
    private Integer highestBid;
    private Integer higestBidUser;
    private String currentServerTime;
    private Boolean hasHigestBidChanged;

    public LotStatusDTO(Integer highestBid, Integer higestBidUser, String currentServerTime, Boolean hasHigestBidChanged) {
        this.highestBid = highestBid;
        this.higestBidUser = higestBidUser;
        this.currentServerTime = currentServerTime;
        this.hasHigestBidChanged = hasHigestBidChanged;

    }

    public Integer getHighestBid() {
        return highestBid;
    }

    public Integer getHigestBidUser() {
        return higestBidUser;
    }

    public String getCurrentServerTime() {
        return currentServerTime;
    }

    public Boolean getHasHigestBidChanged() {
        return hasHigestBidChanged;
    }

    @Override
    public String toString() {
        return "LotStatusDTO {'highestBid'=" + highestBid + ", 'higestBidUser'='" + higestBidUser + "', 'currentServerTime'='"
                + currentServerTime + "', 'hasHigestBidChanged'='" + hasHigestBidChanged + "'}";
    }
}
