package com.aaacpl.dto.lotAuditLog;

/**
 * Created by Hp on 22-02-2016.
 */
public class LotAuditLogDTO {
    private int id;
    private int lot_id;
    private int user_id;
    private int bidAmount;
    private String ipAddress;
    private String localSystemTime;
    private Boolean isAccepted;

    public LotAuditLogDTO(int id, int lot_id, int user_id, int bidAmount, String ipAddress, String localSystemTime, Boolean isAccepted) {
        this.id = id;
        this.lot_id = lot_id;
        this.user_id = user_id;
        this.bidAmount = bidAmount;
        this.ipAddress = ipAddress;
        this.localSystemTime = localSystemTime;
        this.isAccepted = isAccepted;
    }

    public int getId() {
        return id;
    }

    public int getLot_id() {
        return lot_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getBidAmount() {
        return bidAmount;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getLocalSystemTime() {
        return localSystemTime;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    @Override
    public String toString() {
        return "LotAuditLogDTO{" +
                "id=" + id +
                ", lot_id=" + lot_id +
                ", user_id=" + user_id +
                ", bidAmount=" + bidAmount +
                ", ipAddress='" + ipAddress + '\'' +
                ", localSystemTime='" + localSystemTime + '\'' +
                ", isAccepted=" + isAccepted +
                '}';
    }
}
