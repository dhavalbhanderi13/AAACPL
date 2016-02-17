package com.aaacpl.rest.request.lots;

public class BidRequest {

	private Integer lotId;
	private Integer userId;
	private Long bidAmount;
	private String ipAddress;
	private String created;

	public Integer getLotId() {
		return lotId;
	}

	public void setLotId(Integer lotId) {
		this.lotId = lotId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Long getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(Long bidAmount) {
		this.bidAmount = bidAmount;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "BidRequest {'lotId'=" + lotId + ", 'userId'=" + userId
				+ ", 'bidAmount'=" + bidAmount + ", 'ipAddress'='" + ipAddress
				+ "', 'created'='" + created + "'}";
	}

}
