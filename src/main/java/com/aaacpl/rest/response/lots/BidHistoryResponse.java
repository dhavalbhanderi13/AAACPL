package com.aaacpl.rest.response.lots;

public class BidHistoryResponse {

	private int auctionId;
	private int userId;
	private String userName;
	private String companyName;
	private int bidAmt;
	private String logTime;
	private String isAccetped;

	public BidHistoryResponse(int auctionId, int userId, String userName,
			String companyName, int bidAmt, String logTime, String isAccetped) {
		this.auctionId = auctionId;
		this.userId = userId;
		this.userName = userName;
		this.companyName = companyName;
		this.bidAmt = bidAmt;
		this.logTime = logTime;
		this.isAccetped = isAccetped;
	}

	public int getAuctionId() {
		return auctionId;
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public int getBidAmt() {
		return bidAmt;
	}

	public String getLogTime() {
		return logTime;
	}

	public String isAccetped() {
		return isAccetped;
	}

	@Override
	public String toString() {
		return "BidHistoryResponse [auctionId=" + auctionId + ", userId="
				+ userId + ", userName=" + userName + ", companyName="
				+ companyName + ", bidAmt=" + bidAmt + ", isAccetped"
				+ isAccetped + ", logTime=" + logTime + "]";
	}

}
