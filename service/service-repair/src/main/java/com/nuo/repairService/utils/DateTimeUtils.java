package com.nuo.repairService.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取指定日期的起始时间，即当天的零点
     * @param date 指定日期
     * @return 起始时间
     */
    public static Date getBeginOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定日期的结束时间，即当天的23:59:59.999
     * @param date 指定日期
     * @return 结束时间
     */
    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 根据指定日期字符串获取当天的起始时间和结束时间
     * @param dateString 指定日期字符串，格式为"yyyy-MM-dd"
     * @return 包含起始时间和结束时间的数组
     * @throws ParseException 解析异常
     */
    public static Date[] getBeginAndEndOfDay(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        Date beginOfDay = getBeginOfDay(date);
        Date endOfDay = getEndOfDay(date);
        return new Date[]{beginOfDay, endOfDay};
    }
}
