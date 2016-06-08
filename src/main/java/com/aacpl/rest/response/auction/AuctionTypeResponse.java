package com.aacpl.rest.response.auction;

public class AuctionTypeResponse {
    private int id;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuctionTypeResponse that = (AuctionTypeResponse) o;

        if (id != that.id) return false;
        return type != null ? type.equals(that.type) : that.type == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "AuctionTypeResponse{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
