package com.payc.tool.utils;

import com.payc.tool.constants.BaseConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间工具类
 *
 * @author gary.wang
 * @since 2019/11/13 13:14
 **/
@Slf4j
public class DateUtil {
    public static final long SECOND = 1000;//1秒 java已毫秒为单位

    public static final long MINUTE = SECOND * 60;//一分钟

    public static final long HOUR = MINUTE * 60;// 一小时

    public static final long DAY = HOUR * 24;//一天

    public static final long WEEK = DAY * 7;//一周

    public static final long YEAR = DAY * 365;//一年

    public static final String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";//默认时间格式

    public static final String FORMAT_TIME_MINUTE = "yyyy-MM-dd HH:mm";//默认时间格式

    public final static String DATE_TO_STRING_PATTERN = "yyyy/MM/dd HH:mm:ss";


    public static final String FORMAT_DATE = "yyyy-MM-dd";//默认日期格式

    public static final String FORMAT_DATE_M = "yyyy-MM";//默认月份格式

    public static final String FORMAT_DATE_A = "yyMMdd";

    public static final String FORMAT_DATE_B = "yyyyMM";

    public static final String FORMAT_DATE_C = "yyyyMMdd";

    public static final String FORMAT_DATE_D = "yyyyMMddHHmmss";
    public static final String FORMAT_DATE_E = "yyyy/MM/dd";

    /**
     * 空日期对象, 该时间点为中国(东八区)1970-01-01 00:00:00
     * 使用该日期对象写入SQL的DateTime字段后, 显示上述时间点
     * <p>
     * 可以使用DateUtil.isValidDate(date) 来判定是否是合法的日期
     * EMPTY_DATE_TIMESTAMP 属于非法日期的时间戳
     */
    public static final long EMPTY_DATE_TIMESTAMP = -28800000L;

    /**
     * 中国时区字符串
     */
    public static final String CHINA_ZONE = "GMT+8";

    /**
     * 中国时区
     */
    public static final TimeZone TIME_ZONE_GMT_CHINA = TimeZone.getTimeZone(CHINA_ZONE);

    /**
     * 空日期对象, 该时间点为中国(东八区)1970-01-01 00:00:00
     * 使用该日期对象写入SQL的DateTime字段后, 显示上述时间点
     * <p>
     * 可以使用DateUtil.isValidDate(date) 来判定是否是合法的日期
     * EMPTY_DATE_ 属于非法日期
     *
     * @return 空日期对象
     */
    public static Date emptyDate() {
        return new Date(EMPTY_DATE_TIMESTAMP);
    }

    /**
     * 获取当前系统时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentTime() {
        DateTime dt = new DateTime();
        String time = dt.toString(FORMAT_TIME);
        return time;
    }

    /**
     * 获取系统当前时间按照指定格式返回
     *
     * @param pattern yyyy/MM/dd hh:mm:a
     * @return
     */
    public static String getCurrentTimePattern(String pattern) {
        DateTime dt = new DateTime();
        return dt.toString(pattern);
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        DateTime dt = new DateTime();
        return dt.toString(FORMAT_DATE);
    }


    /**
     * 获取当前日期按照指定格式
     *
     * @param pattern
     * @return
     */
    public static String getCurrentDatePattern(String pattern) {
        DateTime dt = new DateTime();
        return dt.toString(pattern);
    }

    /**
     * 按照时区转换时间
     *
     * @param date
     * @param timeZone 时区
     * @param pattern
     * @return
     */
    public static String format(Date date, TimeZone timeZone, String pattern) {
        if (date == null) {
            return null;
        }
        if (null == timeZone) {
            timeZone = TimeZone.getDefault();
        }
        return new DateTime(date)
                .withZone(DateTimeZone.forTimeZone(timeZone))
                .toString(pattern);
    }

    /**
     * 按照GMT+8时区进行日期格式化
     *
     * @param date    时间
     * @param pattern 格式化模型
     * @return 日期字符串
     */
    public static String formatGmtChina(Date date, String pattern) {
        return format(date, TIME_ZONE_GMT_CHINA, pattern);
    }

    /**
     * 获取指定时间
     *
     * @param year    年
     * @param month   月
     * @param day     天
     * @param hour    小时
     * @param minute  分钟
     * @param seconds 秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getPointTime(Integer year, Integer month, Integer day, Integer hour, Integer minute,
                                      Integer seconds) {
        DateTime dt = new DateTime(year, month, day, hour, minute, seconds);
        return dt.toString(FORMAT_TIME);
    }

    /**
     * @param year    年
     * @param month   月
     * @param day     天
     * @param hour    小时
     * @param minute  分钟
     * @param seconds 秒
     * @param pattern 自定义格式
     * @return String
     */
    public static String getPointTimePattern(Integer year, Integer month, Integer day, Integer hour, Integer minute,
                                             Integer seconds, String pattern) {
        DateTime dt = new DateTime(year, month, day, hour, minute, seconds);
        return dt.toString(pattern);
    }

    /**
     * 为指定日期设置时分秒
     *
     * @param date    待设置时分秒的日期
     * @param hours   时
     * @param minutes 分
     * @param seconds 秒
     * @return 设置后的时间
     */
    public static Date pointTime(Date date, Integer hours, Integer minutes, Integer seconds) {
        return new DateTime(date).withHourOfDay(hours).withMinuteOfHour(minutes).withSecondOfMinute(seconds).toDate();
    }

    /**
     * 获取指定日期
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getPointDate(Integer year, Integer month, Integer day) {
        LocalDate dt = new LocalDate(year, month, day);
        String date = dt.toString(FORMAT_DATE);
        return date;
    }

    /**
     * 获取指定日期 返回指定格式
     *
     * @param year
     * @param month
     * @param day
     * @param pattern
     * @return
     */
    public static String getPointDatPattern(Integer year, Integer month, Integer day, String pattern) {
        LocalDate dt = new LocalDate(year, month, day);
        String date = dt.toString(pattern);
        return date;
    }

    /**
     * 获取当前是一周星期几
     *
     * @return
     */
    public static String getWeek() {
        DateTime dts = new DateTime();
        String week = null;
        switch (dts.getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                week = "星期日";
                break;
            case DateTimeConstants.MONDAY:
                week = "星期一";
                break;
            case DateTimeConstants.TUESDAY:
                week = "星期二";
                break;
            case DateTimeConstants.WEDNESDAY:
                week = "星期三";
                break;
            case DateTimeConstants.THURSDAY:
                week = "星期四";
                break;
            case DateTimeConstants.FRIDAY:
                week = "星期五";
                break;
            case DateTimeConstants.SATURDAY:
                week = "星期六";
                break;
            default:
                week = "";
                break;
        }
        return week;
    }

    /**
     * 获取指定时间是一周的星期几
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getWeekPoint(Integer year, Integer month, Integer day) {
        LocalDate dts = new LocalDate(year, month, day);
        String week = null;
        switch (dts.getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                week = "星期日";
                break;
            case DateTimeConstants.MONDAY:
                week = "星期一";
                break;
            case DateTimeConstants.TUESDAY:
                week = "星期二";
                break;
            case DateTimeConstants.WEDNESDAY:
                week = "星期三";
                break;
            case DateTimeConstants.THURSDAY:
                week = "星期四";
                break;
            case DateTimeConstants.FRIDAY:
                week = "星期五";
                break;
            case DateTimeConstants.SATURDAY:
                week = "星期六";
                break;
            default:
                break;
        }
        return week;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return new DateTime(date).toString(FORMAT_TIME);
    }

    /**
     * 格式化日期字符串
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return new DateTime(date).toString(pattern);
    }

    /**
     * 按照GMT+8时区解析日期
     *
     * @param date    日期字符串
     * @param pattern 格式化模型
     * @return 日期
     */
    public static Date parseGmtChina(String date, String pattern) {
        return parseWithTimeZone(date, pattern, TIME_ZONE_GMT_CHINA);
    }

    /**
     * 按照GMT+8时区解析日期
     *
     * @param date     日期字符串
     * @param pattern  格式化模型
     * @param timeZone 时区(传null则使用默认时区)
     * @return 日期
     */
    public static Date parseWithTimeZone(String date, String pattern, TimeZone timeZone) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        DateTimeFormatter builder = DateTimeFormat.forPattern(pattern);
        if (null != timeZone) {
            builder.withZone(DateTimeZone.forTimeZone(timeZone));
        }
        return builder.parseDateTime(date).toDate();
    }

    /**
     * 解析日期
     *
     * @param date 日期字符串
     * @return
     */
    public static Date parse(String date, String pattern) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        DateTimeFormatter builder = DateTimeFormat.forPattern(pattern);
        return builder.parseDateTime(date).toDate();
    }

    /**
     * 解析日期
     *
     * @param date 日期字符串
     * @return
     */
    public static Date parse(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        return new DateTime(date).toDate();
    }


    /**
     * 解析日期 yyyy-MM-dd HH:mm:ss
     *
     * @param timestamp
     * @param pattern
     * @return
     */
    public static String format(Long timestamp, String pattern) {
        String dateStr = "";
        if (null == timestamp || timestamp < 0) {
            return dateStr;
        }
        try {
            DateTime date = new DateTime(timestamp);
            dateStr = date.toString(pattern);
        } catch (Exception e) {
            log.error("format:{}", e);
        }

        return dateStr;
    }

    /**
     * 解析日期 yyyy-MM-dd HH:mm:ss
     *
     * @param timestamp
     * @return
     */
    public static String format(Long timestamp) {
        String dateStr = "";
        if (null == timestamp || timestamp < 0) {
            return dateStr;
        }
        try {
            DateTime date = new DateTime(timestamp);
            dateStr = date.toString(FORMAT_TIME);
        } catch (Exception e) {
            log.error("format:{}", e);
        }
        return dateStr;
    }

    /**
     * 获取当前时间前几天时间,按指定格式返回
     *
     * @param days
     * @return
     */
    public static String forwardDay(Integer days, String format) {
        DateTime dt = new DateTime();
        DateTime y = dt.minusDays(days);
        return y.toString(format);
    }

    /**
     * 获取当前时间前几月时间,按指定格式返回
     *
     * @param mouth 月数
     * @return
     */
    public static String forwardMonth(Integer mouth, String format) {
        DateTime dt = new DateTime();
        DateTime y = dt.minusMonths(mouth);
        return y.toString(format);
    }

    /**
     * 获取当前时间前几天时间
     *
     * @param days
     * @return
     */
    public static Date forwardDay(Integer days) {
        DateTime dt = new DateTime();
        DateTime y = dt.minusDays(days);
        return y.toDate();
    }

    /**
     * 获取指定时间之后或者之前的某一天00:00:00 默认返回当天
     *
     * @param days
     * @return
     */
    public static Date dayBegin(Integer days, String date, String timeZoneStr) throws Throwable {
        DateTime dt = getCurrentDay(date, timeZoneStr);
        DateTime y = dt.minusDays(days).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
        return y.toDate();
    }

    /**
     * 获取指定时间之后或者之前的某一天23:59:59 默认返回当天
     *
     * @param days 偏移量
     * @return
     */
    public static Date dayEnd(Integer days, String date, String timeZoneStr) throws Throwable {
        DateTime dt = getCurrentDay(date, timeZoneStr);
        DateTime y = dt.minusDays(days).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return y.toDate();
    }

    private static DateTime getCurrentDay(String date, String timeZoneStr) {
        DateTime dt = null;
        TimeZone timeZone;
        try {
            if (StringUtils.isBlank(timeZoneStr)) {
                timeZone = TimeZone.getDefault();
            } else {
                timeZone = TimeZone.getTimeZone(timeZoneStr);
            }
            if (StringUtils.isBlank(date)) {
                dt = new DateTime().withZone(DateTimeZone.forTimeZone(timeZone)).toLocalDateTime().toDateTime();
            } else {
                dt = new DateTime(date).withZone(DateTimeZone.forTimeZone(timeZone)).toLocalDateTime().toDateTime();
            }
        } catch (Throwable e) {
            dt = DateTime.now();
        }
        return dt;
    }


    /**
     * 计算两个时间相差多少天
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer diffDay(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        DateTime dt1 = new DateTime(startDate);
        DateTime dt2 = new DateTime(endDate);
        int day = Days.daysBetween(dt1, dt2).getDays();
        return Math.abs(day);
    }

    /**
     * 计算两个时间相差多少分钟
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer diffMinutes(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        DateTime dt1 = new DateTime(startDate);
        DateTime dt2 = new DateTime(endDate);
        int day = Minutes.minutesBetween(dt1, dt2).getMinutes();
        return Math.abs(day);
    }

    /**
     * 获取前几个月最后一天时间  23:59:59
     *
     * @return
     */
    public static Date lastDayBeforeMonth(Date date, Integer month) {
        DateTime dt1;
        if (month == null) {
            month = 0;
        }
        if (date == null) {
            dt1 = new DateTime().minusMonths(month);
        } else {
            dt1 = new DateTime(date).minusMonths(month);
        }
        DateTime lastDay = dt1.dayOfMonth().withMaximumValue().
                withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return lastDay.toDate();
    }

    /**
     * 获取某月月之前,之后某一个月第一天,00:00:00
     *
     * @return
     */
    public static Date firstDayBeforeMonth(Date date, Integer month) {
        DateTime dt1;
        if (month == null) {
            month = 0;
        }
        if (date == null) {
            dt1 = new DateTime().minusMonths(month);
        } else {
            dt1 = new DateTime(date).minusMonths(month);
        }
        DateTime lastDay = dt1.dayOfMonth().withMinimumValue().
                withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
        return lastDay.toDate();
    }

    /**
     * 获取某月月之前,之后某一个月第一天,00:00:00
     *
     * @return
     */
    public static Date firstDayBeforeYear(Date date, Integer month) {
        DateTime dt1;
        if (month == null) {
            month = 0;
        }
        if (date == null) {
            dt1 = new DateTime().minusYears(month);
        } else {
            dt1 = new DateTime(date).minusYears(month);
        }
        DateTime lastDay = dt1.dayOfMonth().withMinimumValue().withDayOfYear(1).
                withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
        return lastDay.toDate();
    }

    public static Date addDay(Date date, int offset) {
        DateTime dt1;
        if (date == null) {
            dt1 = new DateTime().plusDays(offset);
            return dt1.toDate();
        }
        dt1 = new DateTime(date).plusDays(offset);
        return dt1.toDate();

    }

    /**
     * 传入日期时间与当前系统日期时间的比较,
     * 若日期相同，则根据时分秒来返回 ,
     * 否则返回具体日期
     *
     * @return 日期或者 xx小时前||xx分钟前||xx秒前
     */
    public static String getNewUpdateDateString(Date now, Date createDate) {
        if (now == null || createDate == null) {
            return null;
        }
        long time = (now.getTime() - createDate.getTime());
        if (time > (24 * 60 * 60 * 1000)) {
            return time / (24 * 60 * 60 * 1000) + "天前";
        } else if (time > (60 * 60 * 1000)) {
            return time / (60 * 60 * 1000) + "小时前";
        } else if (time > (60 * 1000)) {
            return time / (60 * 1000) + "分钟前";
        } else if (time >= 1000) {
            return time / 1000 + "秒前";
        } else {
            return "刚刚";
        }
    }

    public static int getExpireSeconds() {
        DateTime dateTimeA = new DateTime();
        DateTime dateTimeB = new DateTime().plusDays(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
        Seconds days = Seconds.secondsBetween(dateTimeA, dateTimeB);
        return days.getSeconds();
    }

    public static Date getInternalDateByDay(Date d, int days) {
        DateTime dateTimeA = new DateTime(d);
        return dateTimeA.plusDays(days).toDate();
    }

    /**
     * 判断是否是yyyy-MM-dd
     *
     * @param str
     * @return
     */
    public static boolean isValidDate(String str) {
        try {
            DateTimeFormatter builder = DateTimeFormat.forPattern(FORMAT_DATE);
            builder.parseDateTime(str).toString();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 获取当天开始时间
     *
     * @return
     */
    public static Date getTheDayBeginTime() {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * yyyyMMdd ->yyyy-MM-dd
     * 时间格式转换
     *
     * @param dateTime
     * @return
     */
    public static String getFormatDate(String dateTime) {
        StringBuilder stringBuilder = new StringBuilder(dateTime);
        stringBuilder.insert(4, "-");
        stringBuilder.insert(7, "-");
        return stringBuilder.toString();
    }

    /**
     * 获取本周开始时间（周一）
     *
     * @return 本周周一日期
     */
    public static Date getWeekBegin() {
        return getWeekBegin(new Date());
    }

    /**
     * 获取指定时间的当周开始时间（周一）
     *
     * @param date 指定时间
     * @return 当周周一日期
     */
    public static Date getWeekBegin(Date date) {
        DateTime theFirstDateOfWeek = new DateTime(date).dayOfWeek().withMinimumValue();
        theFirstDateOfWeek =
                theFirstDateOfWeek.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
        return theFirstDateOfWeek.toDate();
    }

    /**
     * 获取本周结束时间（周日）
     *
     * @return 本周周日日期
     */
    public static Date getWeekEnd() {
        return getWeekEnd(new Date());
    }

    /**
     * 获取指定时间的当周结束时间（周日）
     *
     * @param date 指定时间
     * @return 当周周日日期
     */
    public static Date getWeekEnd(Date date) {
        DateTime theEndDateOfWeek = new DateTime(date).dayOfWeek().withMaximumValue();
        theEndDateOfWeek =
                theEndDateOfWeek.withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(0);
        return theEndDateOfWeek.toDate();
    }

    /**
     * 获取本月开始时间（1号）
     *
     * @return 本月1号
     */
    public static Date getMonthBegin() {
        return getMonthBegin(new Date());
    }

    /**
     * 获取指定时间所在月开始时间（1号）
     *
     * @param date 指定时间
     * @return 所在月1号
     */
    public static Date getMonthBegin(Date date) {
        DateTime theFirstDateOfMonth = new DateTime(date).dayOfMonth().withMinimumValue();
        theFirstDateOfMonth =
                theFirstDateOfMonth.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
        return theFirstDateOfMonth.toDate();
    }

    /**
     * 获取本月最后一天时间
     *
     * @return 本月最后一天时间
     */
    public static Date getMonthEnd() {
        return getMonthEnd(new Date());
    }

    /**
     * 获取指定时间所在月月最后一天时间
     *
     * @param date 指定时间
     * @return 所在月月最后一天时间
     */
    public static Date getMonthEnd(Date date) {
        DateTime theEndDateOfMonth = new DateTime(date).dayOfMonth().withMaximumValue();
        theEndDateOfMonth =
                theEndDateOfMonth.withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(0);
        return theEndDateOfMonth.toDate();
    }

    /**
     * 根据秒数计算 时分秒
     *
     * @param seconds
     * @return
     */
    public static String getTimeStr(int seconds) {
        int temp = 0;
        StringBuilder stringBuilder = new StringBuilder("");
        temp = seconds / 3600;
        if (temp > 0) {
            stringBuilder.append(temp).append("时");
        }
        temp = seconds % 3600 / 60;
        if (temp > 0) {
            stringBuilder.append(temp).append("分");
        }
        temp = seconds % 3600 % 60;
        if (temp > 0) {
            stringBuilder.append(temp).append("秒");
        }
        return stringBuilder.toString();
    }


    /**
     * 合法Date判定
     * 判定依据: 非null且大于1970-01-01 00:00:00时间点
     *
     * @param date 需要判定的Date对象
     * @return 合法则返回true
     */
    public static boolean isValidDate(Date date) {
        return null != date && 0L < date.getTime();
    }

    /**
     * 非法Date判定
     * 判定依据参照isValidDate函数
     *
     * @param date 需要判定的Date对象
     * @return 非法则返回true
     */
    public static boolean isInvalidDate(Date date) {
        return !isValidDate(date);
    }

    /**
     * 判定是否是合法的开始结束时间
     * 判定依据: 开始结束时间点都符合 非null且大于1970-01-01 00:00:00时间点
     * 且开始时间不大于结束时间
     *
     * @param startDate  需要判定的开始Date对象
     * @param endDate    需要判定的结束Date对象
     * @param allowEqual 是否允许时间相等
     * @return 合法则返回true
     */
    public static boolean isValidStartEndDate(Date startDate, Date endDate, boolean allowEqual) {
        if (!isValidDate(startDate) || !isValidDate(endDate)) {
            return false;
        }
        int result = startDate.compareTo(endDate);
        if (allowEqual) {
            return 0 >= result;
        } else {
            return 0 > result;
        }
    }

    /**
     * 校验当前时间是否大于指定年月份20日
     *
     * @return
     */
    public static boolean validDate(String formatDay) {
        DateTime dt1 = DateTime.parse(formatDay, DateTimeFormat.forPattern(FORMAT_DATE_B));
        DateTime endDateTime = dt1.withDayOfMonth(26).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
        int compare = DateTime.now().compareTo(endDateTime);
        if (compare >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将LocalDate转换为Date
     *
     * @param localDate 本地Date对象
     * @return Date对象
     */
    public static Date fromLocalDate(java.time.LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 将Date转换为LocalDate
     *
     * @param date 本地Date对象
     * @return Date对象
     */
    public static java.time.LocalDate fromDate(Date date) {
        return java.time.Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date parseWithDifferentFormats(String dateStr) {
        if (StringUtils.contains(dateStr, BaseConstants.SLASH)) {
            if (StringUtils.contains(dateStr, BaseConstants.COLON)) {
                return parse(dateStr, DATE_TO_STRING_PATTERN);
            } else {
                return parse(dateStr, FORMAT_DATE_E);
            }
        } else if (StringUtils.contains(dateStr, BaseConstants.DASH)) {
            if (StringUtils.contains(dateStr, BaseConstants.COLON)) {
                return parse(dateStr, FORMAT_TIME);
            } else {
                return parse(dateStr, FORMAT_DATE);
            }
        }
        return parse(dateStr);
    }
}
