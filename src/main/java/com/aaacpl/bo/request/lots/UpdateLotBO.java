package com.aaacpl.bo.request.lots;

import java.sql.Timestamp;

public class UpdateLotBO {

	private int id;
	private String status;
	private int auctionId;
	private String name;
	private String description;
	private String startBid;
	private int differenceFactor;
	private Timestamp startDate;
	private Timestamp endDate;
	private Integer updatedBy;

	public UpdateLotBO(int id, String status, int auctionId, String name,
			String description, String startBid, int differenceFactor,
			Timestamp startDate, Timestamp endDate,	Integer updatedBy) {

		this.id = id;
		this.status = status;
		this.auctionId = auctionId;
		this.name = name;
		this.description = description;
		this.startBid = startBid;
		this.differenceFactor = differenceFactor;
		this.startDate = startDate;
		this.endDate = endDate;
		this.updatedBy = updatedBy;
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

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public String getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "UpdateLotBO{" + "auctionId=" + auctionId + ", name='" + name
				+ '\'' + ", description='" + description + '\''
				+ ", startBid='" + startBid + '\'' + ", differenceFactor="
				+ differenceFactor + ", startDate='" + startDate + '\''
				+ ", endDate='" + endDate + '\'' + ", updatedBy='" + updatedBy + '\'' + '}';
	}
}
