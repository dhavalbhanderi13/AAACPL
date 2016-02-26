package com.aaacpl.dto.liveBidLog;

public class LiveBidLogDTO {
    private int id;
    private int userId;
    private int lotId;
    private int maxValue;
    private String ipAddress;
    private String localSystemTime;

    public LiveBidLogDTO(int id, int userId, int lotId, int maxValue, String ipAddress, String localSystemTime) {
        this.id = id;
        this.userId = userId;
        this.lotId = lotId;
        this.maxValue = maxValue;
        this.ipAddress = ipAddress;
        this.localSystemTime = localSystemTime;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getLotId() {
        return lotId;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getLocalSystemTime() {
        return localSystemTime;
    }

    @Override
    public String toString() {
        return "LiveBidLogDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", lotId=" + lotId +
                ", maxValue=" + maxValue +
                ", ipAddress='" + ipAddress + '\'' +
                ", localSystemTime='" + localSystemTime + '\'' +
                '}';
    }
}
