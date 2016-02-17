package com.aaacpl.rest.request.lots;

public class CreateLotRequest {
	private int auctionId;
	private String name;
	private String description;
	private String startBid;
	private int differenceFactor;
	private String startDate;
	private String endDate;
	private int createdBy;
	private Integer updatedBy;

	public int getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartBid() {
		return startBid;
	}

	public void setStartBid(String startBid) {
		this.startBid = startBid;
	}

	public int getDifferenceFactor() {
		return differenceFactor;
	}

	public void setDifferenceFactor(int differenceFactor) {
		this.differenceFactor = differenceFactor;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "CreateLotRequest{" + "auctionId=" + auctionId + ", name='"
				+ name + '\'' + ", description='" + description + '\''
				+ ", startBid='" + startBid + '\'' + ", differenceFactor="
				+ differenceFactor + ", startDate='" + startDate + '\''
				+ ", endDate='" + endDate + '\'' + ", createdBy='" + createdBy
				+ '\'' + ", updatedBy='" + updatedBy + '\'' + '}';
	}
}
