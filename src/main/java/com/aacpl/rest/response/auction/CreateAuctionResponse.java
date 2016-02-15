package com.aacpl.rest.response.auction;

public class CreateAuctionResponse {
	private int auctionId;
	private String successMessage;
	private String failureMessage;

	public int getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}

	@Override
	public String toString() {
		return "CreateAuctionResponse{" +
				"auctionId=" + auctionId +
				", successMessage='" + successMessage + '\'' +
				", failureMessage='" + failureMessage + '\'' +
				'}';
	}
}
