package com.aaacpl.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Timestamp getTimeStampFromString(String dateStr) {
		return Timestamp.valueOf(dateStr);
	}

	public static String getDateStringFromTimeStamp(Timestamp timestamp) {
		return new SimpleDateFormat("YYYY-MM-DD HH:mm:ss").format(timestamp);
	}

	public static String getCurrentServerTime() {
		String timeStamp = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss")
				.format(new Timestamp(new Date().getTime()));

		return timeStamp;
	}
}
