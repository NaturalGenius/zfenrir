package com.zfenrir.util.date;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class DateUtil {

    public static final String DATE_TIME_FORMATTER_PATTERN_1 = "yyyy-MM-dd HH:mm";
    public static final Date THE_INITIAL_DATE = new Date(0);
    public static final String DATE_TIME_STAMP_PATTERN = "yyMMddHHmmss";
    private static final String ZONED_DATE_TIME_FORMATTER_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String DATE_TIME_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATTER_PATTERN = "yyyy-MM-dd";
    private static final String TIME_FORMATTER_PATTERN = "HH:mm:ss";
    public static final String HOUR_MINUTE_FORMATTER_PATTERN = "HH:mm";

    private static final DateTimeFormatter ZONED_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(ZONED_DATE_TIME_FORMATTER_PATTERN);
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_PATTERN);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMATTER_PATTERN);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMATTER_PATTERN);
    public static final DateTimeFormatter DATE_TIME_NUMBER_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_STAMP_PATTERN);
    public static final DateTimeFormatter HOUR_MINUTE_FORMATTER = DateTimeFormatter.ofPattern(HOUR_MINUTE_FORMATTER_PATTERN);

    /**
     * 一天的毫秒数
     */
    private static final long DAY_LONG = 24 * 60 * 60 * 1000L;

    /**
     * 根据预设格式 <b>yyyy-MM-dd HH:mm:ss</b> 转换当前日期为{@link String}类型的日期
     *
     * @return {@link String}类型的日期
     */
    public static String now() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    /**
     * 根据自定义格式转换当前日期为{@link String}类型的日期
     *
     * @param pattern 自定义的日期格式
     * @return {@link String}类型的日期
     */
    public static String now(String pattern) {
        return Instant.now().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期时间格式化
     * @param localDateTime 日期时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatLocalDate(LocalDateTime localDateTime){
        if (null != localDateTime){
            return localDateTime.format(DATE_TIME_FORMATTER);
        }
        return null;
    }

    /**
     * 根据预设格式 <b>yyyy-MM-dd</b> 转换当前日期为{@link String}类型的日期
     *
     * @return {@link String}类型的日期
     */
    public static String today() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * 根据自定义格式转换当前日期为{@link String}类型的日期
     *
     * @param pattern 自定义的日期格式
     * @return {@link String}类型的日期
     */
    public static String today(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 根据预设格式 <b>yyyy-MM-dd HH:mm:ss</b> 转换{@link String}类型的日期为{@link Date}类型。
     *
     * @param date {@link Date}类型的日期
     * @return {@link String}类型的日期
     */
    public static String format(Date date) {
        return format(date, DATE_TIME_FORMATTER);
    }

    public static String formatDate(Date date) {
        return format(date, DATE_FORMATTER);
    }

    /**
     * 根据自定义格式转换指定日期为{@link String}类型的日期
     *
     * @param date 日期
     * @param pattern 日期格式
     * @return
     */
    public static String format(Date date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return format(date, formatter);
    }

    /**
     * 根据自定义格式转换指定日期的毫秒数为{@link String}类型的日期
     *
     * @param millis 毫秒数
     * @param pattern 日期格式
     * @return {@link String}类型的日期
     */
    public static String format(long millis, String pattern) {
        Date date = new Date(millis);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return format(date, formatter);
    }

    /**
     * 根据指定格式转换指定日期为{@link String}类型的日期
     *
     * @param date 指定的日期
     * @param formatter 日期格式
     * @return {@link String}类型的日期
     */
    public static String format(Date date, DateTimeFormatter formatter) {
        return date.toInstant().atZone(ZoneId.systemDefault()).format(formatter);
    }

    /**
     * 使用预设格式转换 {@link String} 为 {@link Date} 类型， 预设格式有三种，优先级是 yyyy-MM-dd HH:mm:ss > yyyy-MM-dd > HH:mm:ss
     *
     * @param strDate 日期字符串
     * @return {@link Date} 类型的时间
     */
    public static Date parse(String strDate) {
        try {
            return parse(strDate, DATE_TIME_FORMATTER);
        } catch (Exception e) {
            try {
                return parse(strDate, ZONED_DATE_TIME_FORMATTER);
            } catch (Exception e1) {
                try {
                    return parse(strDate, DATE_FORMATTER);
                } catch (Exception e2) {
                    try {
                        return parse(strDate, TIME_FORMATTER);
                    } catch (Exception e3) {
                        throw new IllegalArgumentException("非法的日期格式");
                    }
                }
            }
        }
    }

    /**
     * 使用自定义的格式转换 {@link String} 为 {@link Date} 类型
     * 
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return {@link Date}类型的时间
     */
    public static Date parse(String strDate, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return parse(strDate, formatter);
    }

    /**
     * 根据预设格式 <b>yyyy-MM-dd</b> 减去指定天数，并返回{@link String}类型的日期。
     * 
     * @param days 要减去的天数
     * @return {@link String}类型的日期
     */
    public static String minusDays(long days) {
        return LocalDateTime.now().minusDays(days).format(DATE_FORMATTER);
    }

    public static String minusDays(long days, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now().minusDays(days).format(formatter);
    }

    /**
     * 根据预设格式 <b>yyyy-MM-dd</b> 加上指定天数，并返回{@link String}类型的日期。
     * 
     * @param days 要加上的天数
     * @return {@link String}类型的日期
     */
    public static String plusDays(long days) {
        return LocalDateTime.now().plusDays(days).format(DATE_FORMATTER);
    }

    private static Date parse(String strDate, DateTimeFormatter formatter) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(strDate, formatter);
            Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
            return Date.from(instant);
        } catch (Exception e) {
            try {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(strDate, formatter);
                Instant instant = zonedDateTime.toInstant();
                return Date.from(instant);
            } catch (Exception e1) {
                try {
                    LocalDate localDate = LocalDate.parse(strDate, formatter);
                    Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
                    return Date.from(instant);
                } catch (Exception e2) {
                    try {
                        LocalTime localTime = LocalTime.parse(strDate, formatter);
                        Instant instant = localTime.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
                        return Date.from(instant);
                    } catch (Exception e3) {
                        throw new IllegalArgumentException("非法的日期格式");
                    }
                }
            }
        }
    }

    public static Date startOfDay(String strDate) {
        LocalDate localDate = LocalDate.parse(strDate, DATE_FORMATTER);
        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date startOfDay(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date endOfDay(String strDate) {
        LocalDate localDate = LocalDate.parse(strDate, DATE_FORMATTER);
        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).minusNanos(1).toInstant();
        return Date.from(instant);
    }

    public static Date endOfDay(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).minusNanos(1).toInstant();
        return Date.from(instant);
    }

    public static long plusAndGetStartOfDayMillis(long days) {
        return LocalDate.now().plusDays(days).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long plusAndGetEndOfDayMillis(long days) {
        return LocalDate.now().atStartOfDay(ZoneId.systemDefault()).plusDays(days + 1).minusNanos(1).toInstant()
            .toEpochMilli();
    }

    public static Date plus(int day) {
        return Date.from(LocalDateTime.now().plusDays(day).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date plus(Date date, int day) {
        return Date.from(date2LocalDate(date).plusDays(day).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime date2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
    
    public static Date localDateTime2date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    public static Date localDate2date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当月第一天
     * 
     * @return
     */
    public static Date getMouthStartDay() {
        return Date.from(LocalDate.now().withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 计算两个时间相隔多少天
     * 
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getDifferDays(Date startTime, Date endTime) {
        long starttime = startOfDay(startTime).getTime();
        long time = startOfDay(endTime).getTime();
        return (int)((time - starttime) / DAY_LONG);
    }

    public static Date max(Date date1, Date date2) {
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        return date1.after(date2) ? date1 : date2;
    }

    public static Date min(Date date1, Date date2) {
        return date1.after(date2) ? date2 : date1;
    }

    /**
     * 获取该天是周几
     * 
     * @param date
     * @return
     */
    public static String getDayOfWeek(Date date) {
        DayOfWeek dayOfWeek = date2LocalDate(date).getDayOfWeek();
        switch (dayOfWeek) {
            case MONDAY:
                return "星期一";
            case TUESDAY:
                return "星期二";
            case WEDNESDAY:
                return "星期三";
            case THURSDAY:
                return "星期四";
            case FRIDAY:
                return "星期五";
            case SATURDAY:
                return "星期六";
            case SUNDAY:
                return "星期日";
            default:
                return "";
        }
    }

    /**
     * 加秒数
     * @param date
     * @param seconds
     * @return
     */
    public static Date plusSeconds(Date date, int seconds) {
        return Date.from(date2LocalDate(date).plusSeconds(seconds).atZone(ZoneId.systemDefault()).toInstant());
    }
    /**
     * 加毫秒秒数
     * @param date
     * @param millisecond
     * @return
     */
    public static Date plusMillisecond(Date date, long millisecond) {
        return new Date(date.getTime() + millisecond);
    }
    /**
     * 加分钟
     * @param date
     * @param minutes
     * @return
     */
    public static Date plusMinutes(Date date, int minutes) {
        return Date.from(date2LocalDate(date).plusMinutes(minutes).atZone(ZoneId.systemDefault()).toInstant());
    }
    /**
     * 加
     * @param date
     * @param minutes
     * @return
     */
    public static Date plusHour(Date date, int hours) {
        return Date.from(date2LocalDate(date).plusHours(hours).atZone(ZoneId.systemDefault()).toInstant());
    }
    /**
     * 加月
     * @param date
     * @param months
     * @return
     */
    public static Date plusMonths(Date date, int months) {
        return Date.from(date2LocalDate(date).plusMonths(months).atZone(ZoneId.systemDefault()).toInstant());
    }
    
    /**
     *  A 时间 在 两个时间段之间
     * @param checkDate
     * @param leftDate
     * @param rightDate
     * @return
     */
    public static boolean between(Date checkDate,Date leftDate,Date rightDate){
        if(checkDate != null && leftDate != null && rightDate != null){
            if(greaterThanEqual(rightDate,leftDate)){
                if(lessThanEqual(checkDate,rightDate)&&greaterThanEqual(checkDate,leftDate)){
                    return true;
                }
            }
        }
        return  false;
    }
    /**
     * A 时间大于等于 B 时间
     * @param date
     * @param when
     * @return
     */
    public static boolean greaterThanEqual(Date date, Date when) {
        if (date != null && when != null) {
            return date.after(when) || date.equals(when);
        }
        return false;
    }
    /**
     * A 时间小于等于 B 时间
     * @param date
     * @param when
     * @return
     */
    public static boolean lessThanEqual(Date date, Date when) {
        if (date != null && when != null) {
            return date.before(when) || date.equals(when);
        }
        return false;
    }
    
    /**
     * 判断起始时间是否跨天
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean isAcrossDay(Date startDate, Date endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
            return true;
        }
        return !Objects.equals(format(startDate, DATE_FORMATTER_PATTERN),
            format(endDate, DATE_FORMATTER_PATTERN));
    }
}
