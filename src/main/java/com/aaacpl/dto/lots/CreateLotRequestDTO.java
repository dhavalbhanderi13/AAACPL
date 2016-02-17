package com.aaacpl.dto.lots;

import java.sql.Timestamp;

/**
 * Created by Hp on 15-02-2016.
 */
public class CreateLotRequestDTO {
    private int auctionId;
    private String name;
    private String description;
    private String startBid;
    private int differenceFactor;
    private Timestamp startDate;
    private Timestamp endDate;
    private int createdBy;

    public CreateLotRequestDTO(int auctionId, String name, String description, String startBid, int differenceFactor, Timestamp startDate, Timestamp endDate, int createdBy) {
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    @Override
    public String toString() {
        return "CreateLotRequestDTO{" +
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
