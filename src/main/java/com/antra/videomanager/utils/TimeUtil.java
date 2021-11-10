package com.antra.videomanager.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TimeUtil {

    private final static String ISO_DATE_FORMAT_ZERO_OFFSET = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private final static String UTC_TIMEZONE_NAME = "UTC";

    private TimeUtil() {}

    public static Timestamp convertStringToTimestamp(String str) throws ParseException {
        if(str == null) {
            throw new IllegalArgumentException("input time can't be null");
        }
        return new Timestamp(provideDateFormat().parse(str).getTime());
    }

    public static Timestamp getCurrentTimestamp() throws ParseException {
        final String currentTime = provideDateFormat().format(System.currentTimeMillis());
        return convertStringToTimestamp(currentTime);
    }

    private static SimpleDateFormat provideDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ISO_DATE_FORMAT_ZERO_OFFSET);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(UTC_TIMEZONE_NAME));
        return simpleDateFormat;
    }
}
