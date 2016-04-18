package com.aaacpl.dto.lots;

import java.sql.Timestamp;

/**
 * Created by Sumedh on 17-04-2016.
 */
public class LotByAccessInTenderDTO {
    private int id;
    private int auctionId;
    private String name;
    private String description;
    private int createdBy;
    private int updatedBy;
    private String status;

    public LotByAccessInTenderDTO(int id, int auctionId, String name, String description, int createdBy, int updatedBy, String status) {
        this.id = id;
        this.auctionId = auctionId;
        this.name = name;
        this.description = description;
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
        return "LotByAccessInTenderDTO{" +
                "id=" + id +
                ", auctionId=" + auctionId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", status='" + status + '\'' +
                '}';
    }
}
