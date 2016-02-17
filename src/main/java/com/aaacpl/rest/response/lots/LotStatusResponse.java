package com.aaacpl.rest.response.lots;

public class LotStatusResponse {

	private Integer highestBid;
	private String higestBidUser;
	private String currentServerTime;
	private Boolean hasHigestBidChanged;

	public LotStatusResponse(Integer highestBid, String higestBiduser,
			String currentServerTime, Boolean hasHigestBidChanged) {
	}

	public Integer getHighestBid() {
		return highestBid;
	}

	public String getHigestBidUser() {
		return higestBidUser;
	}

	public String getCurrentServerTime() {
		return currentServerTime;
	}

	public Boolean getHasHigestBidChanged() {
		return hasHigestBidChanged;
	}

	@Override
	public String toString() {
		return "LotStatusResponse {'highestBid'=" + highestBid
				+ ", 'higestBidUser'='" + higestBidUser
				+ "', 'currentServerTime'='" + currentServerTime
				+ "', 'hasHigestBidChanged'='" + hasHigestBidChanged + "'}";
	}

}
