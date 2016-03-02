package com.aaacpl.dto.lots;

import java.sql.Timestamp;

public class LotStatusDTO {
    private Integer highestBid;
    private Integer higestBidUser;
    private String currentServerTime;
    private Boolean hasHigestBidChanged;
    private Timestamp startDate;
    private Timestamp endDate;

    public LotStatusDTO(Integer highestBid, Integer higestBidUser, String currentServerTime, Boolean hasHigestBidChanged, Timestamp startDate, Timestamp endDate) {
        this.highestBid = highestBid;
        this.higestBidUser = higestBidUser;
        this.currentServerTime = currentServerTime;
        this.hasHigestBidChanged = hasHigestBidChanged;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "LotStatusDTO{" +
                "highestBid=" + highestBid +
                ", higestBidUser=" + higestBidUser +
                ", currentServerTime='" + currentServerTime + '\'' +
                ", hasHigestBidChanged=" + hasHigestBidChanged +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
