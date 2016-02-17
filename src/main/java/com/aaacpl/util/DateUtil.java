package com.aaacpl.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	public static Timestamp getTimeStampFromString(String dateStr) {
		return Timestamp.valueOf(dateStr);
	}

	public static String getDateStringFromTimeStamp(Timestamp timestamp) {
		return timestamp.toString();
	}

	public static String getCurrentServerTime() {
		String timeStamp = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS")
				.format(Calendar.getInstance().getTime());

		return timeStamp;
	}
}
