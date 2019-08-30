package com.mohan.project.easytools.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期时间工具类
 * @author mohan
 * @date 2018-08-30 09:36:59
 */
public class DateTools {

    public static final String FORMAT_LOG = "yyyy-MM-dd HH:mm:ss,SSS";
    public static final String FORMAT_LOCAL_DATE = "yyyy-MM-dd=";
    public static final String FORMAT_LOCAL_DATE_TIME = "=HH:mm:ss=";

    public static String getDateTimeString(String pattern) {
        return format(LocalDateTime.now(), pattern);
    }

    private static String format(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(localDateTime);
    }
}