package com.work.auth.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间通用类
 *
 * @author zhaiyunpeng
 */
public class DateUtil {
    /**
     * yyyy-MM-dd
     */
    public final static String DATE_FORMAT_1 = "yyyy-MM-dd";
    /**
     * yyyy/MM/dd
     */
    public final static String DATE_FORMAT_2 = "yyyy/MM/dd";
    /**
     * yyyyMMdd
     */
    public final static String DATE_FORMAT_3 = "yyyyMMdd";
    /**
     * yyyyMMdd
     */
    public final static String DATE_FORMAT_4 = "yyyy年MM月dd日";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public final static String DATE_TIME_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy/MM/dd HH:mm:ss
     */
    public final static String DATE_TIME_FORMAT_2 = "yyyy/MM/dd HH:mm:ss";
    /**
     * yyyyMMddHHmmss
     */
    public final static String DATE_TIME_FORMAT_3 = "yyyyMMddHHmmss";
    /**
     * yyyy年MM月dd日 HH时mm分ss秒
     */
    public final static String DATE_TIME_FORMAT_4 = "yyyy年MM月dd日 HH时mm分ss秒";
    /**
     * HH:mm:ss
     */
    public final static String TIME_FORMAT = "HH:mm:ss";
    /**
     * HH时mm分ss秒
     */
    public final static String TIME_FORMAT_1 = "HH时mm分ss秒";

    /**
     * 获取默认标准化规则（yyyy-MM-dd）时间字符串
     *
     * @return yyyy-MM-dd
     */
    public static String getNowDateStr() {
        return getNowDateStr(TIME_FORMAT_1);
    }

    /**
     * 获取指定格式当前时间
     *
     * @param formatStr 准化规则，例：yyyy-MM-dd
     * @return String
     */
    public static String getNowDateStr(String formatStr) {
        if (StringUtil.isEmpty(formatStr)) {
            // 当标准化规则为空时，默认返回yyyy-MM-dd
            formatStr = DATE_FORMAT_1;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取当前时间
     *
     * @return HH:mm:ss
     */
    public static String getNowTimeStr() {
        return formatDateToStr(new Date(), TIME_FORMAT);
    }

    /**
     * 获取当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDateTimeStr() {
        return formatDateToStr(new Date(), DATE_TIME_FORMAT_1);
    }

    /**
     * 根据标准化规则,将指定时间转化为字符串
     *
     * @param date      日期参数，如为null，则取当前时间
     * @param formatStr 准化规则，如为空，取例：yyyy-MM-dd，
     * @return Date String
     */
    public static String formatDateToStr(Date date, String formatStr) {
        if (StringUtil.isEmpty(formatStr)) {
            // 当标准化规则为空时，默认返回yyyy-MM-dd
            formatStr = DATE_FORMAT_1;
        }
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
        return simpleDateFormat.format(date);
    }

    /**
     * 字符串转日期,需与日期标准化格式对应，否者可能会引发异常
     *
     * @param dateStr    待处理日期字符串
     * @param dateFormat 日期标准化格式
     * @return Date
     */
    public static Date formatStrToDate(String dateStr, String dateFormat) {
        // 当时间字符串、时间标准化格式为空时，返回当前时间
        if (StringUtil.isEmpty(dateStr) || StringUtil.isEmpty(dateFormat)) {
            return new Date();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取当前年份字符串
     *
     * @return yyyy
     */
    public static String getYear() {
        return formatDateToStr(new Date(), "yyyy");
    }

    /**
     * 获取指定时间年份
     *
     * @return yyyy
     */
    public static String getYear(Date date) {
        return formatDateToStr(date, "yyyy");
    }

    /**
     * 获取当前月份字符串
     *
     * @return MM
     */
    public static String getMonth() {
        return formatDateToStr(new Date(), "MM");
    }

    /**
     * 获取指定时间月份
     *
     * @return MM
     */
    public static String getMonth(Date date) {
        return formatDateToStr(new Date(), "MM");
    }

    /**
     * 获取当前月份字符串
     *
     * @return MM
     */
    public static String getDay() {
        return formatDateToStr(new Date(), "dd");
    }

    /**
     * 获取指定时间月份
     *
     * @return MM
     */
    public static String getDay(Date date) {
        return formatDateToStr(new Date(), "dd");
    }

    /**
     * 当前时间，星期几
     *
     * @return MM
     */
    public static String getWeek() {
        return formatDateToStr(new Date(), "E");
    }

    /**
     * 指定日期与当前日期相隔天数
     *
     * @param date 指定日期
     * @return long 天数
     */
    public static long getDayBetweenNow(Date date) {
        return getDayBetweenDates(date, new Date());
    }

    /**
     * 两个日期间隔天数
     *
     * @param date1 指定日期1
     * @param date2 指定日期2
     * @return long 天数
     */
    public static long getDayBetweenDates(Date date1, Date date2) {
        Calendar curCalendar = Calendar.getInstance();
        curCalendar.setTime(date1);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date2);
        return Math.abs(curCalendar.getTimeInMillis() - targetCalendar.getTimeInMillis()) / (24 * 60 * 60 * 1000);
    }
    /**
     * 指定日期与当前日期相隔小时数
     *
     * @param date 指定日期
     * @return long 小时数
     */
    public static long getHourBetweenNow(Date date){
        return getHourBetweenNow(date,new Date());
    }
    /**
     * 两个日期间隔小时数
     *
     * @param date1 指定日期1
     * @param date2 指定日期2
     * @return long 小时数
     */
    public static long getHourBetweenNow(Date date1,Date date2){
        Calendar curCalendar = Calendar.getInstance();
        curCalendar.setTime(date1);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date2);
        return Math.abs(curCalendar.getTimeInMillis() - targetCalendar.getTimeInMillis()) / (60 * 60 * 1000);
    }
    /**
     * 当前日期num天之后、前的日期
     * @param num 天数可正负
     * @return Date
     */
    public static Date dateAddDay(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + num);
        return calendar.getTime();
    }

    /**
     * 当前日期num月之后、前的月份
     * @param num 天数可正负
     * @return Date
     */
    public static Date dateAddMonth(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + num);
        return calendar.getTime();
    }

    /**
     * 当前日期num年之后、前的年份
     * @param num 天数可正负
     * @return Date
     */
    public static Date dateAddYear(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + num);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println(getHourBetweenNow(formatStrToDate("2019-12-18","yyyy-MM-dd")));
    }

}
