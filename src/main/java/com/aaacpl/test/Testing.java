package com.aaacpl.test;

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
		// List<String> emailTo = new ArrayList<String>();
		// emailTo.add("dhavalbhanderi13@gmail.com");
		// emailTo.add("dhavalb@zedo.com");
		//
		// SendEmailRequest service = new SendEmailRequest(emailTo,
		// "Test Subject", "Test Body", "dhavalbhanderi13@gmail.com");
		//
		// SendEmailService ser = new SendEmailService();
		// ser.sendEmailList(service);

		// LotsService lotService = new LotsService();
		// UpdateLotRequest updatecreateLotRequest = new UpdateLotRequest();
		// {

		// }
		// updatecreateLotRequest.setId(1);
		// updatecreateLotRequest.setStatus("A");
		// updatecreateLotRequest.setAuctionId(1);
		// updatecreateLotRequest.setName("DHAVAL_LOT");
		// updatecreateLotRequest.setDescription("TESTING PURPOSE");
		// updatecreateLotRequest.setStartBid("100");
		// updatecreateLotRequest.setDifferenceFactor(150);
		// updatecreateLotRequest.setStartDate("2016-02-12 00:00:00.0");
		// updatecreateLotRequest.setEndDate("2016-03-12 00:00:00.0");
		// updatecreateLotRequest.setCreatedBy(1);
		// updatecreateLotRequest.setUpdatedBy(1);
		// // lotService.getBidHistory(1);
		// lotService.updateLot(updatecreateLotRequest);

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
