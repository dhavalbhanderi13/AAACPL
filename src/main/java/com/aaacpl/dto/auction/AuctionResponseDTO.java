package com.aaacpl.dto.auction;

public class AuctionResponseDTO {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AuctionResponseDTO{" +
                "id=" + id +
                '}';
    }
}
