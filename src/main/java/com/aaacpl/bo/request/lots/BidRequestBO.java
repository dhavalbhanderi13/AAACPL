package com.aaacpl.bo.request.lots;

public class BidRequestBO {

	private Integer lotId;

	private Integer userId;
	private Long bidAmount;
	private String ipAddress;
	private String created;

	public BidRequestBO(Integer lotId, Integer userId, Long bidAmount,
			String ipAddress, String created) {
		this.lotId = lotId;
		this.userId = userId;
		this.bidAmount = bidAmount;
		this.ipAddress = ipAddress;
		this.created = created;

	}

	public Integer getLotId() {
		return lotId;
	}

	public Integer getUserId() {
		return userId;
	}

	public Long getBidAmount() {
		return bidAmount;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getCreated() {
		return created;
	}

	@Override
	public String toString() {
		return "BidRequestBO {'lotId'=" + lotId + ", 'userId'=" + userId
				+ ", 'bidAmount'=" + bidAmount + ", 'ipAddress'='" + ipAddress
				+ "', 'created'='" + created + "'}";
	}

}
