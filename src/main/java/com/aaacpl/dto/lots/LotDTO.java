package com.aaacpl.dto.lots;

public class LotDTO {
    private int id;
    private int auctionId;
    private String name;
    private String description;
    private String startBid;
    private int differenceFactor;
    private String startDate;
    private String endDate;
    private int createdBy;
    private int updatedBy;

    public LotDTO(int id, int auctionId, String name, String description, String startBid, int differenceFactor, String startDate, String endDate, int createdBy, int updatedBy) {
        this.id = id;
        this.auctionId = auctionId;
        this.name = name;
        this.description = description;
        this.startBid = startBid;
        this.differenceFactor = differenceFactor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public int getId() {
        return id;
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

    public int getCreatedBy() {
        return createdBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public String toString() {
        return "LotDTO{" +
                "id=" + id +
                ", auctionId=" + auctionId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startBid='" + startBid + '\'' +
                ", differenceFactor=" + differenceFactor +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
