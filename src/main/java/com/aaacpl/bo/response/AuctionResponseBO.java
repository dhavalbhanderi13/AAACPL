package com.aaacpl.bo.response;

public class AuctionResponseBO {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AuctionResponseBO{" +
                "id=" + id +
                '}';
    }
}
