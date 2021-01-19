package com.shan.yellowpages.base.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * 日期工具类
 *
 * @author xuejw
 * @version 1.0
 */
public abstract class DateUtil {

    public final static String STRING_DATE_TIME_FORMATTER_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public final static String STRING_DATE_TIME_FORMATTER_YMDHM = "yyyy-MM-dd HH:mm";

    public final static String STRING_DATE_FORMATTER_YMD = "yyyy-MM-dd";

    public final static String STRING_DATE_TIME_FORMATTER_YMDHMS_CN = "yyyy年MM月dd日 HH:mm:ss";

    public static final String STRING_DATE_TIME_FORMATTER_YMDHMS_COMPACT = "yyyyMMddHHmmss";

    public final static String STRING_DATE_TIME_FORMATTER_YMDHM_COMPACT = "yyyyMMddHHmm";

    public final static String STRING_DATE_TIME_FORMATTER_YMDH_COMPACT = "yyyyMMddHH";

    public static final String STRING_DATE_FORMATTER_YMD_COMPACT = "yyyyMMdd";

    public static final String STRING_DATE_FORMATTER_YM_COMPACT = "yyyyMM";

    public static final long ONE_DAY_MILLISECONF = 86400000L ;

    /**
     * yyyy-MM-dd HH:mm:ss 格式
     */
    public static DateTimeFormatter DATE_TIME_FORMATTER_YMDHMS = DateTimeFormatter.ofPattern(STRING_DATE_TIME_FORMATTER_YMDHMS);

    /**
     * yyyy-MM-dd HH:mm 格式
     */
    public static DateTimeFormatter DATE_TIME_FORMATTER_YMDHM = DateTimeFormatter.ofPattern(STRING_DATE_TIME_FORMATTER_YMDHM);

    /**
     * yyyy-MM-dd 格式
     */
    public static DateTimeFormatter DATE_FORMATTER_YMD = DateTimeFormatter.ofPattern(STRING_DATE_FORMATTER_YMD);

    /**
     * yyyyMMddHHmmss 格式
     */
    public static DateTimeFormatter DATE_TIME_FORMATTER_YMDHMS_COMPACT = DateTimeFormatter.ofPattern(STRING_DATE_TIME_FORMATTER_YMDHMS_COMPACT);

    /**
     * yyyyMMddHHmm 格式
     */
    public static DateTimeFormatter DATE_TIME_FORMATTER_YMDHM_COMPACT = DateTimeFormatter.ofPattern(STRING_DATE_TIME_FORMATTER_YMDHM_COMPACT);

    /**
     * yyyyMMddHH 格式
     */
    public static DateTimeFormatter DATE_TIME_FORMATTER_YMDH_COMPACT = DateTimeFormatter.ofPattern(STRING_DATE_TIME_FORMATTER_YMDH_COMPACT);

    /**
     * yyyyMMdd 格式
     */
    public static DateTimeFormatter DATE_FORMATTER_YMD_COMPACT = DateTimeFormatter.ofPattern(STRING_DATE_FORMATTER_YMD_COMPACT);

    /**
     * yyyyMM 格式
     */
    public static DateTimeFormatter DATE_FORMATTER_YM_COMPACT = DateTimeFormatter.ofPattern(STRING_DATE_FORMATTER_YM_COMPACT);

    /** yyyyMMddHH 格式 */

    /**
     * yyyy年MM月dd HH:mm:ss 格式
     */
    public static DateTimeFormatter DATE_TIME_FORMATTER_YMDHMS_CN = DateTimeFormatter.ofPattern(STRING_DATE_TIME_FORMATTER_YMDHMS_CN);

    private static final String[] WEEK_DAYS = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    /**
     * java.util.Date --> java.time.LocalDateTime
     *
     * @param date
     * @return localDateTime 对象
     */
    public static LocalDateTime DateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * java.util.Date --> java.time.LocalDate
     * @param date
     * @return LocalDate 对象
     */
    public static LocalDate DateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    /**
     * java.util.Date --> java.time.LocalTime
     * @param date
     * @return localTime 对象
     */
    public static LocalTime DateToLocalTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalTime();
    }

    /**
     * 获取 date 类型的当前时间
     * @return Date 对象
     */
    public static Date nowAndReturnDate() {
        LocalDateTime now = LocalDateTime.now();

        return LocalDateTimeToDate(now);
    }

    /**
     * java.time.LocalDateTime --> java.util.Date
     * @param localDateTime
     * @return Date 对象
     */
    public static Date LocalDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();

        Instant instant = localDateTime.atZone(zone).toInstant();

        return Date.from(instant);
    }

    /**
     * java.time.LocalDate --> java.util.Date
     * @param localDate
     * @return  Date对象
     */
    public static Date LocalDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * java.time.LocalTime --> java.util.Date
     * @param localDate
     * @param localTime
     * @return Date 对象
     */
    public static Date LocalTimeToDate(LocalDate localDate, LocalTime localTime) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 是否是指定日期
     * @param compareDate
     * @param standardDay
     * @return 是否时同一天
     */
    public static boolean isInTheDay(LocalDateTime compareDate, LocalDateTime standardDay) {
        int result = compareDate.toLocalDate().compareTo(standardDay.toLocalDate());
        return result == 0;
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime   当前时间
     * @param beginTime 起始时间
     * @param endTime   结束时间
     * @return 是否在时间段内
     */
    public static boolean belongCalendar(LocalDateTime nowTime, LocalDateTime beginTime, LocalDateTime endTime) {
        return nowTime.isAfter(beginTime) && nowTime.isBefore(endTime);
    }

    /**
     * 判断时间是否在今天时间段内
     *
     * @param date 当前时间
     * @return 是否在今天
     */
    public static boolean isToday(LocalDate date) {
        return LocalDate.now().equals(date);
    }

    /**
     * 获取yyyy-MM-dd HH:mm:ss 格式的时间
     *
     * @param dateStr
     * @return 年月日时分秒 格式化的 localDateTime对象
     */
    public static LocalDateTime getLocalDateTimeByYMDHMS(String dateStr) {
        return getLocalDateTime(dateStr, DATE_TIME_FORMATTER_YMDHMS);
    }

    /**
     * 获取yyyy-MM-dd HH:mm 格式的时间
     *
     * @param dateStr
     * @return 年月日时分 格式化的 localDateTime对象
     */
    public static LocalDateTime getLocalDateTimeByYMDHM(String dateStr) {
        return getLocalDateTime(dateStr, DATE_TIME_FORMATTER_YMDHM);
    }

    /**
     * 获取指定格式的时间
     *
     * @param dateStr
     * @param dateTimeFormatter
     * @return 指定格式格式化的 localDateTime 对象
     */
    public static LocalDateTime getLocalDateTime(String dateStr, DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.parse(dateStr, dateTimeFormatter);
    }

    /**
     * 获取yyyy-MM-dd格式的时间
     *
     * @param dateStr
     * @return 年月日时分秒格式化的 localDate对象
     */
    public static LocalDate getLocalDateByYMD(String dateStr) {
        return getLocalDate(dateStr, DATE_TIME_FORMATTER_YMDHMS);
    }

    /**
     * 获取yyyyMMdd 格式的时间
     *
     * @param dateStr
     * @return 年月日格式化的 LocalDate对象
     */
    public static LocalDate getLocalDateByYMDCOMPACT(String dateStr) {
        return getLocalDate(dateStr, DATE_FORMATTER_YMD_COMPACT);
    }

    /**
     * 获取指定格式的日期
     *
     * @param dateStr
     * @param dateTimeFormatter
     * @return 指定格式 格式化的 LocalDate 对象
     */
    public static LocalDate getLocalDate(String dateStr, DateTimeFormatter dateTimeFormatter) {
        return LocalDate.parse(dateStr, dateTimeFormatter);
    }

    /**
     * 获取指日期的时间戳
     *
     * @param localDateTime
     * @return 指定时间的毫秒数
     */
    public static long getTimestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 获取日期的星期信息
     *
     * @param datetime
     * @return 指定日期 周几字符串
     */
    public static String getWeekOfDate(Date datetime) {
        return getWeekOfLocalDate(DateToLocalDate(datetime));
    }

    /**
     * 获取日期的星期信息
     *
     * @param localDate
     * @return 周几
     */
    public static String getWeekOfLocalDate(LocalDate localDate) {
        int w = localDate.getDayOfWeek().getValue() % 7;
        if (w < 0)
            w = 0;
        return WEEK_DAYS[w];
    }


    /**
     * 获取某周的开始日期
     *
     * @param localDate
     * @param offset    0本周，1下周，-1上周，依次类推
     * @return localDate对象
     */
    public static LocalDate getWeekStart(LocalDate localDate, int offset) {
        localDate = localDate.plusWeeks(offset);
        return localDate.with(DayOfWeek.MONDAY);
    }

    /**
     * 装换日期格式
     * @param dateTime 日期字符串
     * @param parsePatterns 模式 可以时多个字符串
     * @return 格式化出的 Date 对象
     */
    public static Date parseDate(String dateTime, String... parsePatterns) {
        Date date;
        try {
            date = DateUtils.parseDate(dateTime, parsePatterns);
        } catch (ParseException e) {
            date = null;
        }
        return date;
    }

    private static SimpleDateFormat mdformat = new SimpleDateFormat("M月d日");

    private static SimpleDateFormat hhmmformat = new SimpleDateFormat("HH:mm");

    private static SimpleDateFormat mdhhmmformat = new SimpleDateFormat("M月d日 HH:mm");

    /**
     * 返回描述性日期
     * @param date 日期
     * @return 描述
     */
    public static String getHumanReadableDate(Date date) {
        long now = System.currentTimeMillis();
        Calendar today = Calendar.getInstance();
        today.setTimeInMillis(now);

        Calendar next = Calendar.getInstance();
        next.setTime(date);

        int days;
        if (next.get(Calendar.YEAR) == today.get(Calendar.YEAR)) {
            days = next.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR);
        } else {
            days = (int) (date.getTime() - now) / 1000 * 60 * 60 * 24;
        }
        int dayOfWeek = today.get(Calendar.DAY_OF_WEEK);
        if (days == 0) {
            return "今天 ";
        } else if (days == 1) {
            return "明天 ";
        } else if (days == 2) {
            return "后天 ";
        } else if (days > 2 && days <= offsetNextWeekend.get(dayOfWeek)) {
            return humanReadableDate[dayOfWeek - 1][days - 3];
        } else {
            return mdformat.format(date);
        }

    }

    /**
     * 返回描述性日期
     * @param date 日期
     * @return 描述
     */
    public static String getHumanReadableDateTime(Date date) {
        long now = System.currentTimeMillis();
        Calendar today = Calendar.getInstance();
        today.setTimeInMillis(now);

        Calendar next = Calendar.getInstance();
        next.setTime(date);

        int days;
        if (next.get(Calendar.YEAR) == today.get(Calendar.YEAR)) {
            days = next.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR);
        } else {
            days = (int) (date.getTime() - now) / 1000 * 60 * 60 * 24;
        }
        int dayOfWeek = today.get(Calendar.DAY_OF_WEEK);
        String time = hhmmformat.format(date);
        time = time.replace("00:00", "");
        if (days == 0) {
            return "今天 " + time;
        } else if (days == 1) {
            return "明天 " + time;
        } else if (days == 2) {
            return "后天 " + time;
        } else if (days > 2 && days <= offsetNextWeekend.get(dayOfWeek)) {
            return humanReadableDate[dayOfWeek - 1][days - 3] + " " + time;
        } else {
            return mdhhmmformat.format(date);
        }

    }

    private static Map<Integer, Integer> offsetNextWeekend = new HashMap<>();

    private static String[][] humanReadableDate = new String[7][11];

    /**
     * 获取某周得第一天
     * @param year 年
     * @param week 周
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week){
        Calendar c = new GregorianCalendar();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.WEEK_OF_YEAR,  week);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return c.getTime();
    }


    /**
     * 获取某周得最后一天
     * @param year 年
     * @param week 周
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week){
        Calendar c = new GregorianCalendar();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.WEEK_OF_YEAR,  week);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 5);
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);

        return c.getTime();
    }

    private static LocalDate getDateByYearAndWeekNumAndDayOfWeek(Integer year, Integer num, DayOfWeek dayOfWeek) {
        //该格式周日为一周第一天，周六为一周最后一天
        //周数小于10在前面补个0
        String numStr = num < 10 ? "0" + num : String.valueOf(num);
        //2019-W01-01获取第一周的周一日期，2019-W02-07获取第二周的周日日期
//        String weekDate = String.format("%s-W%s-%s", year, numStr, dayOfWeek.getValue());
        String weekDate = String.format("%s-%s %s", year, numStr, dayOfWeek.getValue());
//        return LocalDate.parse(weekDate, DateTimeFormatter.ISO_WEEK_DATE);
        return LocalDate.parse(weekDate, DateTimeFormatter.ofPattern("YYYY-ww e"));
    }
//    public static LocalDate parse(String text) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY w e");
//        return LocalDate.parse(text, Locale.US);
//    }
    public static void main(String[] args) {
//        Integer year = 2015;
//        Integer num = 53;
//        LocalDate localDate = getDateByYearAndWeekNumAndDayOfWeek(year, num, DayOfWeek.MONDAY);

//        LocalDate localDate = parse("2016 01 1");
//        System.out.println("localDate = " + localDate);

//        Date firstDayOfWeek = getFirstDayOfWeek(year, num);
//        Date lastDayOfWeek = getLastDayOfWeek(year, num);
//        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.STRING_DATE_FORMATTER_YMD_COMPACT);
//        System.out.println("firstDayOfWeek = " + sdf.format(firstDayOfWeek));
//        System.out.println("firstDayOfWeek = " + sdf.format(lastDayOfWeek));

        LocalDate date = LocalDate.parse("20000201", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate last = date.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate first = date.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("first = " + first);
        System.out.println("last = " + last);

    }


    static {
        offsetNextWeekend.put(Calendar.SUNDAY, 7);
        offsetNextWeekend.put(Calendar.SATURDAY, 8);
        offsetNextWeekend.put(Calendar.FRIDAY, 9);
        offsetNextWeekend.put(Calendar.THURSDAY, 10);
        offsetNextWeekend.put(Calendar.WEDNESDAY, 11);
        offsetNextWeekend.put(Calendar.TUESDAY, 12);
        offsetNextWeekend.put(Calendar.MONDAY, 13);

        humanReadableDate[1][0] = "本周四";
        humanReadableDate[1][1] = "本周五";
        humanReadableDate[1][2] = "本周六";
        humanReadableDate[1][3] = "本周日";
        humanReadableDate[1][4] = "下周一";
        humanReadableDate[1][5] = "下周二";
        humanReadableDate[1][6] = "下周三";
        humanReadableDate[1][7] = "下周四";
        humanReadableDate[1][8] = "下周五";
        humanReadableDate[1][9] = "下周六";
        humanReadableDate[1][10] = "下周日";

        humanReadableDate[2][0] = "本周五";
        humanReadableDate[2][1] = "本周六";
        humanReadableDate[2][2] = "本周日";
        humanReadableDate[2][3] = "下周一";
        humanReadableDate[2][4] = "下周二";
        humanReadableDate[2][5] = "下周三";
        humanReadableDate[2][6] = "下周四";
        humanReadableDate[2][7] = "下周五";
        humanReadableDate[2][8] = "下周六";
        humanReadableDate[2][9] = "下周日";

        humanReadableDate[3][0] = "本周六";
        humanReadableDate[3][1] = "本周日";
        humanReadableDate[3][2] = "下周一";
        humanReadableDate[3][3] = "下周二";
        humanReadableDate[3][4] = "下周三";
        humanReadableDate[3][5] = "下周四";
        humanReadableDate[3][6] = "下周五";
        humanReadableDate[3][7] = "下周六";
        humanReadableDate[3][8] = "下周日";

        humanReadableDate[4][0] = "本周日";
        humanReadableDate[4][1] = "下周一";
        humanReadableDate[4][2] = "下周二";
        humanReadableDate[4][3] = "下周三";
        humanReadableDate[4][4] = "下周四";
        humanReadableDate[4][5] = "下周五";
        humanReadableDate[4][6] = "下周六";
        humanReadableDate[4][7] = "下周日";

        humanReadableDate[5][0] = "下周一";
        humanReadableDate[5][1] = "下周二";
        humanReadableDate[5][2] = "下周三";
        humanReadableDate[5][3] = "下周四";
        humanReadableDate[5][4] = "下周五";
        humanReadableDate[5][5] = "下周六";
        humanReadableDate[5][6] = "下周日";

        humanReadableDate[6][0] = "下周二";
        humanReadableDate[6][1] = "下周三";
        humanReadableDate[6][2] = "下周四";
        humanReadableDate[6][3] = "下周五";
        humanReadableDate[6][4] = "下周六";
        humanReadableDate[6][5] = "下周日";

        humanReadableDate[0][0] = "下周三";
        humanReadableDate[0][1] = "下周四";
        humanReadableDate[0][2] = "下周五";
        humanReadableDate[0][3] = "下周六";
        humanReadableDate[0][4] = "下周日";

    }

//	public static void main(String[] args) {
//		boolean inTheDay = isInTheDay(LocalDateTime.now(), getLocalDateTimeByYMDHM("2018-08-16 17:00"));
//		System.out.println("inTheDay = " + inTheDay);
//
//		boolean belongCalendar = belongCalendar(LocalDateTime.now(), getLocalDateTimeByYMDHM("2018-08-14 15:00"), getLocalDateTimeByYMDHM("2018-08-20 18:00"));
//		System.out.println("belongCalendar = " + belongCalendar);
//		String a = getWeekOfDate(new Date());
//		System.out.println("a = " + a);
//		Date date = parseDate("2018-09-11", "yyyy-MM-dd");
//		System.out.println(date);
//		LocalDate c = getWeekStart(LocalDateTime.now().toLocalDate(), 0);
//		System.out.println("c = " + c);
//
//	}


}
