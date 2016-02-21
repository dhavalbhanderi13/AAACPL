package com.aaacpl.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.aaacpl.api.services.AuctionService;

public class Testing {
	static AuctionService as = new AuctionService();

	public static String getCurrentServerTime() {
		String timeStamp = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS")
				.format(Calendar.getInstance().getTime());

		return timeStamp;
	}

	public static void main(String[] args) {

		File file = new File("C://Users/dhaval/Desktop/test/1");
		System.out.println(file.exists());

		if (!file.exists()) {
			file.mkdirs();
		}

		System.out.println(file.exists());

		// LotsDAO dao = new LotsDAO();
		// BidRequestBO bidRequestBO = new BidRequestBO(2, 1, 800L, "10.17.150",
		// getCurrentServerTime());

		// try {
		// // dao.insertBid(bidRequestBO);
		// //System.out.println(dao.getLotStatus(1));
		// } catch (SQLException | IOException e) {
		// // TODO Auto-generated catch block
		// }

		// Test for get auctions

		// System.out.println(as.getAllAuctions(1));

		// System.out.println(DateUtil.getTimeStampFromString("2016-02-12 00:00:00"));

		// System.out.println(getCurrentServerTime());
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
