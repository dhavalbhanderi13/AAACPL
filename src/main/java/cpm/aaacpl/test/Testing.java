package cpm.aaacpl.test;

import javax.ws.rs.core.Response;

import com.aaacpl.api.services.AuctionService;
import com.aaacpl.rest.request.auction.CreateAuctionRequest;

public class Testing {
	static AuctionService as = new AuctionService();

	public static void main(String[] args) {

		// Test for get auctions

		System.out.println(as.getAllAuctions(1));

		// test for create Auction

		/*
		 * CreateAuctionRequest createAuctionRequest = new
		 * CreateAuctionRequest(); createAuctionRequest.setName("AIRPORT");
		 * createAuctionRequest.setCatalog("Test Catalog");
		 * createAuctionRequest.setDeptId(11);
		 * createAuctionRequest.setDescription("AirPort Sell");
		 * createAuctionRequest.setInitialBid(123456);
		 * createAuctionRequest.setStartDate("2005-05-25");
		 * createAuctionRequest.setEndDate("2006-05-25");
		 * createAuctionRequest.setCreatedBy("root"); Response rs =
		 * as.create(createAuctionRequest); System.out.println(rs);
		 */

	}

}
