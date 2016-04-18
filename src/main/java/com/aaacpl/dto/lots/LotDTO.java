package com.aaacpl.dto.lots;

import java.sql.Timestamp;

public class LotDTO {
       private int id;
    private int auctionId;
    private String name;
    private String description;
    private String startBid;
    private int differenceFactor;
    private Timestamp startDate;
    private Timestamp endDate;
    private int createdBy;
    private int updatedBy;
    private String status;

    public LotDTO(int id, int auctionId, String name, String description, String startBid, int differenceFactor, Timestamp startDate, Timestamp endDate, int createdBy, int updatedBy, String status) {
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
        this.status = status;
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public String getStatus() {
        return status;
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
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", status='" + status + '\'' +
                '}';
    }
}
