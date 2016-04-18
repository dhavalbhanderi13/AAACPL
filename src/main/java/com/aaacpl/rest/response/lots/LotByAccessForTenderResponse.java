package com.aaacpl.rest.response.lots;

/**
 * Created by Sumedh on 17-04-2016.
 */
public class LotByAccessForTenderResponse implements LotsByAccessResponse {
    private int id;
    private int auctionId;
    private String name;
    private String description;
    private int createdBy;
    private int updatedBy;

    public LotByAccessForTenderResponse(int id, int auctionId, String name, String description, int createdBy, int updatedBy) {
        this.id = id;
        this.auctionId = auctionId;
        this.name = name;
        this.description = description;
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

    public int getCreatedBy() {
        return createdBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public String toString() {
        return "LotByAccessForTenderResponse{" +
                "id=" + id +
                ", auctionId=" + auctionId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                '}';
    }
}
