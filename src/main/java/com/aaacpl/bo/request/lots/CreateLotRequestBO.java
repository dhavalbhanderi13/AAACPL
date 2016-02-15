package com.aaacpl.bo.request.lots;

public class CreateLotRequestBO {
    private int auctionId;
    private String name;
    private String description;
    private String startBid;
    private int differenceFactor;
    private String startDate;
    private String endDate;
    private String createdBy;

    public CreateLotRequestBO(int auctionId, String name, String description, String startBid, int differenceFactor, String startDate, String endDate, String createdBy) {
        this.auctionId = auctionId;
        this.name = name;
        this.description = description;
        this.startBid = startBid;
        this.differenceFactor = differenceFactor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdBy = createdBy;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStartBid() {
        return startBid;
    }

    public int getDifferenceFactor() {
        return differenceFactor;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public String toString() {
        return "CreateLotRequesetDTO{" +
                "auctionId=" + auctionId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startBid='" + startBid + '\'' +
                ", differenceFactor=" + differenceFactor +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}


