package com.aaacpl.bo.request.lots;

public class BidRequestBO {

	private Integer lotId;

	private Integer userId;
	private Long bidAmount;
	private String ipAddress;
	private String localSystemTime;

	public BidRequestBO(Integer lotId, Integer userId, Long bidAmount,
			String ipAddress, String localSystemTime) {
		this.lotId = lotId;
		this.userId = userId;
		this.bidAmount = bidAmount;
		this.ipAddress = ipAddress;
		this.localSystemTime = localSystemTime;

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

	public String getLocalSystemTime() {
		return localSystemTime;
	}

	@Override
	public String toString() {
		return "BidRequestBO {'lotId'=" + lotId + ", 'userId'=" + userId
				+ ", 'bidAmount'=" + bidAmount + ", 'ipAddress'='" + ipAddress
				+ "', 'localSystemTime'='" + localSystemTime + "'}";
	}

}
