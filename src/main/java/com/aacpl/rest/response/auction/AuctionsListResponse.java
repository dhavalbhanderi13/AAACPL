package com.aacpl.rest.response.auction;

import java.util.List;

public class AuctionsListResponse {
	List<AuctionResponse> auctionResponseList;

	public List<AuctionResponse> getAuctionResponseList() {
		return auctionResponseList;
	}

	public void setAuctionResponseList(
			List<AuctionResponse> auctionResponseList) {
		this.auctionResponseList = auctionResponseList;
	}

	@Override
	public String toString() {
		return "AuctionsListResponse{" + "auctionResponseList="
				+ auctionResponseList + '}';
	}
}
