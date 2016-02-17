package com.aaacpl.util;

import java.sql.Timestamp;

public class DateUtil {
    public static Timestamp getTimeStampFromString(String dateStr){
       return Timestamp.valueOf(dateStr);
    }

    public static String getDateStringFromTimeStamp(Timestamp timestamp){
        return timestamp.toString();
    }
}
