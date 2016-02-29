package com.aaacpl.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	public static Timestamp getTimeStampFromString(String dateStr) {
		return Timestamp.valueOf(dateStr);
	}

	public static String getDateStringFromTimeStamp(Timestamp timestamp) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
	}

	public static String getCurrentServerTime() {
		SimpleDateFormat sdfAmerica = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdfAmerica.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		return sdfAmerica.format(new Date());
	}

	public static String getTimestampForReport(Timestamp timestamp){
		return new SimpleDateFormat("HH:mm:ss").format(timestamp);
	}
}
