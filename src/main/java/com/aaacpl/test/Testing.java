package com.aaacpl.test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.aaacpl.api.services.AuctionService;
import com.aaacpl.api.services.LotsService;
import com.aaacpl.bo.request.participator.CreateParticipatorRequestBO;
import com.aaacpl.dao.UsersDAO;
import com.aaacpl.requestHandlers.ParticipatorRequestHandler;
import com.aaacpl.rest.request.participator.ParticipatorInfo;

public class Testing {
	static AuctionService as = new AuctionService();

	public static String getCurrentServerTime() {
		String timeStamp = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS")
				.format(Calendar.getInstance().getTime());

		return timeStamp;
	}

	public static void main(String[] args) throws IOException, SQLException {
		List<Integer> userIds = new ArrayList<Integer>();
		userIds.add(1);
		ParticipatorInfo participatorInfo = new ParticipatorInfo();
		participatorInfo.setLotId(5);
		participatorInfo.setUserIdList(userIds);
		CreateParticipatorRequestBO createParticipatorRequestBO = new CreateParticipatorRequestBO();
		List<ParticipatorInfo> participatorInfos = new ArrayList<ParticipatorInfo>();
		participatorInfos.add(participatorInfo);
		createParticipatorRequestBO.setParticipatorInfoList(participatorInfos);
		new ParticipatorRequestHandler().createParticipator(createParticipatorRequestBO);

	}
}
