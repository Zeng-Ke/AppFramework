package com.zk.java_utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * author: ZK.
 * date:   On 2018-07-02.
 * descritption :时间工具类
 * from: http://blankj.com
 */
public class TimeUtils {

    public enum UNIT {
        MILLIONSECOND(1),
        SECOND(1000),
        MIN(60000),
        HOUR(3600000),
        DAY(86400000);

        public int value;

        UNIT(int value) {
            this.value = value;
        }

    }


    private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private TimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 将时间戳转为时间字符串
     */
    public static String millis2String(final long millis) {
        return millis2String(millis, DEFAULT_FORMAT);
    }


    /**
     * 将时间戳转为时间字符串
     */
    public static String millis2String(final long millis, final DateFormat format) {
        return format.format(new Date(millis));
    }


    /**
     * 将时间字符串转为时间戳
     */
    public static long string2Millis(final String time) {
        return string2Millis(time, DEFAULT_FORMAT);
    }

    /**
     * 将时间字符串转为时间戳
     */
    public static long string2Millis(final String time, final DateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将时间字符串转为 Date 类型
     */
    public static Date string2Date(final String time) {
        return string2Date(time, DEFAULT_FORMAT);
    }

    /**
     * 将时间字符串转为 Date 类型
     */
    public static Date string2Date(final String time, final DateFormat format) {
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将 Date 类型 转为时间字符串
     */
    public static String date2String(final Date date) {
        return date2String(date, DEFAULT_FORMAT);
    }


    /**
     * 将 Date 类型 转为时间字符串
     */
    public static String date2String(final Date date, final DateFormat format) {
        return format.format(date);
    }

    /**
     * 将 Date 类型 转为时间戳
     */
    public static long date2Millis(final Date date) {
        return date.getTime();
    }


    /**
     * 将 时间戳转为Date 类型
     */
    public static Date millis2Date(final long millis) {
        return new Date(millis);
    }


    /**
     * 获取两个时间差（单位：UNIT）
     */
    public static long getTimeSpan(final String time1, final String time2, final UNIT unit) {
        return getTimeSpan(time1, time2, DEFAULT_FORMAT, unit);
    }


    /**
     * 获取两个时间差
     */
    public static long getTimeSpan(final String time1, final String time2, final DateFormat format, final UNIT unit) {
        return millis2TimeSpan(string2Millis(time1, format) - string2Millis(time2, format), unit);
    }

    /**
     * 获取两个时间差（单位：UNIT）
     */
    public static long getTimeSpan(final Date date1, final Date date2, final UNIT unit) {
        return millis2TimeSpan(date2Millis(date1) - date2Millis(date2), unit);
    }

    /**
     * 获取两个时间差
     */
    public static long getTimeSpan(final long millis1, final long millis2, final UNIT unit) {
        return millis2TimeSpan(millis1 - millis2, unit);
    }


    /**
     * 获取合适型两个时间差
     * 参数一： 事件一
     * 参数二： 事件二
     * 参数三：
     * <li>precision = 0, return null</li>
     * <li>precision = 1, return 天</li>
     * <li>precision = 2, return 天, 小时</li>
     * <li>precision = 3, return 天, 小时, 分钟</li>
     * <li>precision = 4, return 天, 小时, 分钟, 秒</li>
     * <li>precision &gt;= 5，return 天, 小时, 分钟, 秒, 毫秒</li>
     */
    public static String getFitTimeSpan(final String time1,
                                        final String time2,
                                        final int precision) {
        long delta = string2Millis(time1, DEFAULT_FORMAT) - string2Millis(time2, DEFAULT_FORMAT);
        return millis2FitTimeSpan(delta, precision);
    }

    /**
     * 获取合适型两个时间差
     * 参数一： 事件一
     * 参数二： 事件二
     * 参数三：
     * <li>precision = 0, return null</li>
     * <li>precision = 1, return 天</li>
     * <li>precision = 2, return 天, 小时</li>
     * <li>precision = 3, return 天, 小时, 分钟</li>
     * <li>precision = 4, return 天, 小时, 分钟, 秒</li>
     * <li>precision &gt;= 5，return 天, 小时, 分钟, 秒, 毫秒</li>
     */
    public static String getFitTimeSpan(final String time1,
                                        final String time2,
                                        final DateFormat format,
                                        final int precision) {
        long delta = string2Millis(time1, format) - string2Millis(time2, format);
        return millis2FitTimeSpan(delta, precision);
    }


    /**
     * 获取合适型两个时间差
     * 参数一： 事件一
     * 参数二： 事件二
     * 参数三：
     * <li>precision = 0, return null</li>
     * <li>precision = 1, return 天</li>
     * <li>precision = 2, return 天, 小时</li>
     * <li>precision = 3, return 天, 小时, 分钟</li>
     * <li>precision = 4, return 天, 小时, 分钟, 秒</li>
     * <li>precision &gt;= 5，return 天, 小时, 分钟, 秒, 毫秒</li>
     */
    public static String getFitTimeSpan(final Date date1, final Date date2, final int precision) {
        return millis2FitTimeSpan(date2Millis(date1) - date2Millis(date2), precision);
    }


    /**
     * 获取合适型两个时间差
     * 参数一： 事件一
     * 参数二： 事件二
     * 参数三：
     * <li>precision = 0, return null</li>
     * <li>precision = 1, return 天</li>
     * <li>precision = 2, return 天, 小时</li>
     * <li>precision = 3, return 天, 小时, 分钟</li>
     * <li>precision = 4, return 天, 小时, 分钟, 秒</li>
     * <li>precision &gt;= 5，return 天, 小时, 分钟, 秒, 毫秒</li>
     */
    public static String getFitTimeSpan(final long millis1,
                                        final long millis2,
                                        final int precision) {
        return millis2FitTimeSpan(millis1 - millis2, precision);
    }


    /**
     * 获取当前时间戳
     */
    public static long getNowMills() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间字符串
     */
    public static String getNowString() {
        return millis2String(System.currentTimeMillis(), DEFAULT_FORMAT);
    }

    /**
     * 获取当前时间字符串
     */
    public static String getNowString(final DateFormat format) {
        return millis2String(System.currentTimeMillis(), format);
    }

    /**
     * 获取当前时间Date类型
     */
    public static Date getNowDate() {
        return new Date();
    }


    /**
     * 获取与当前时间的差（单位：unit）
     */
    public static long getTimeSpanByNow(final String time, final UNIT unit) {
        return getTimeSpan(time, getNowString(), DEFAULT_FORMAT, unit);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     */
    public static long getTimeSpanByNow(final String time,
                                        final DateFormat format,
                                        final UNIT unit) {
        return getTimeSpan(time, getNowString(format), format, unit);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     */
    public static long getTimeSpanByNow(final Date date, final UNIT unit) {
        return getTimeSpan(date, new Date(), unit);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     */
    public static long getTimeSpanByNow(final long millis, final UNIT unit) {
        return getTimeSpan(millis, System.currentTimeMillis(), unit);
    }

    /**
     * 获取合适型与当前时间的差
     * 参数一：时间
     * 参数二：
     * <li>precision = 0, return null</li>
     * <li>precision = 1, return 天</li>
     * <li>precision = 2, return 天, 小时</li>
     * <li>precision = 3, return 天, 小时, 分钟</li>
     * <li>precision = 4, return 天, 小时, 分钟, 秒</li>
     * <li>precision &gt;= 5，return 天, 小时, 分钟, 秒, 毫秒</li>
     */
    public static String getFitTimeSpanByNow(final String time, final int precision) {
        return getFitTimeSpan(time, getNowString(), DEFAULT_FORMAT, precision);
    }

    public static String getFitTimeSpanByNow(final String time,
                                             final DateFormat format,
                                             final int precision) {
        return getFitTimeSpan(time, getNowString(format), format, precision);
    }


    public static String getFitTimeSpanByNow(final Date date, final int precision) {
        return getFitTimeSpan(date, getNowDate(), precision);
    }


    public static String getFitTimeSpanByNow(final long millis, final int precision) {
        return getFitTimeSpan(millis, System.currentTimeMillis(), precision);
    }


    /**
     * 获取友好型与当前时间的差
     * <p>
     * 参数一：时间戳.
     * 参数二：
     * <ul>
     * <li>如果小于 1 秒钟内，显示刚刚</li>
     * <li>如果在 1 分钟内，显示 XXX秒前</li>
     * <li>如果在 1 小时内，显示 XXX分钟前</li>
     * <li>如果在 1 小时外的今天内，显示今天15:32</li>
     * <li>如果是昨天的，显示昨天15:32</li>
     * <li>其余显示，2016-10-15</li>
     * <li>时间不合法的情况全部日期和时间信息，如星期六 十月 27 14:21:20 CST 2007</li>
     * </ul>
     */
    public static String getFriendlyTimeSpanByNow(final String time) {
        return getFriendlyTimeSpanByNow(time, DEFAULT_FORMAT);
    }

    public static String getFriendlyTimeSpanByNow(final String time,
                                                  final DateFormat format) {
        return getFriendlyTimeSpanByNow(string2Millis(time, format));
    }


    public static String getFriendlyTimeSpanByNow(final Date date) {
        return getFriendlyTimeSpanByNow(date.getTime());
    }


    public static String getFriendlyTimeSpanByNow(final long millis) {
        long now = System.currentTimeMillis();
        long span = now - millis;
        if (span < 0)
            // U can read http://www.apihome.cn/api/java/Formatter.html to understand it.
            return String.format("%tc", millis);
        if (span < 1000) {
            return "刚刚";
        } else if (span < UNIT.MIN.value) {
            return String.format(Locale.getDefault(), "%d秒前", span / UNIT.SECOND.value);
        } else if (span < UNIT.HOUR.value) {
            return String.format(Locale.getDefault(), "%d分钟前", span / UNIT.MIN.value);
        }
        // 获取当天 00:00
        long wee = getWeeOfToday();
        if (millis >= wee) {
            return String.format("今天%tR", millis);
        } else if (millis >= wee - UNIT.DAY.value) {
            return String.format("昨天%tR", millis);
        } else {
            return String.format("%tF", millis);
        }
    }

    private static long getWeeOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }


    /**
     * 获取与给定时间等于时间差的时间戳
     */
    public static long getMillis(final long millis,
                                 final long timeSpan,
                                 final UNIT unit) {
        return millis + timeSpan2Millis(timeSpan, unit);
    }


    public static long getMillis(final String time,
                                 final long timeSpan,
                                 final UNIT unit) {
        return getMillis(time, DEFAULT_FORMAT, timeSpan, unit);
    }


    public static long getMillis(final String time,
                                 final DateFormat format,
                                 final long timeSpan,
                                 final UNIT unit) {
        return string2Millis(time, format) + timeSpan2Millis(timeSpan, unit);
    }

    public static long getMillis(final Date date,
                                 final long timeSpan,
                                 final UNIT unit) {
        return date2Millis(date) + timeSpan2Millis(timeSpan, unit);
    }


    /**
     * 获取与给定时间等于时间差的时间字符串
     */
    public static String getString(final long millis,
                                   final long timeSpan,
                                   final UNIT unit) {
        return getString(millis, DEFAULT_FORMAT, timeSpan, unit);
    }

    public static String getString(final long millis,
                                   final DateFormat format,
                                   final long timeSpan,
                                   final UNIT unit) {
        return millis2String(millis + timeSpan2Millis(timeSpan, unit), format);
    }


    public static String getString(final String time,
                                   final long timeSpan,
                                   final UNIT unit) {
        return getString(time, DEFAULT_FORMAT, timeSpan, unit);
    }


    public static String getString(final String time,
                                   final DateFormat format,
                                   final long timeSpan,
                                   final UNIT unit) {
        return millis2String(string2Millis(time, format) + timeSpan2Millis(timeSpan, unit), format);
    }


    public static String getString(final Date date,
                                   final long timeSpan,
                                   final UNIT unit) {
        return getString(date, DEFAULT_FORMAT, timeSpan, unit);
    }

    public static String getString(final Date date,
                                   final DateFormat format,
                                   final long timeSpan,
                                   final UNIT unit) {
        return millis2String(date2Millis(date) + timeSpan2Millis(timeSpan, unit), format);
    }


    /**
     * 获取与给定时间等于时间差的 Date
     */
    public static Date getDate(final long millis,
                               final long timeSpan,
                               final UNIT unit) {
        return millis2Date(millis + timeSpan2Millis(timeSpan, unit));
    }


    public static Date getDate(final String time,
                               final long timeSpan,
                               final UNIT unit) {
        return getDate(time, DEFAULT_FORMAT, timeSpan, unit);
    }

    public static Date getDate(final String time,
                               final DateFormat format,
                               final long timeSpan,
                               final UNIT unit) {
        return millis2Date(string2Millis(time, format) + timeSpan2Millis(timeSpan, unit));
    }


    public static Date getDate(final Date date,
                               final long timeSpan,
                               final UNIT unit) {
        return millis2Date(date2Millis(date) + timeSpan2Millis(timeSpan, unit));
    }

    /**
     * 获取与当前时间等于时间差的时间戳
     */
    public static long getMillisByNow(final long timeSpan, final UNIT unit) {
        return getMillis(getNowMills(), timeSpan, unit);
    }

    /**
     * 获取与当前时间等于时间差的时间字符串
     */
    public static String getStringByNow(final long timeSpan, final UNIT unit) {
        return getStringByNow(timeSpan, DEFAULT_FORMAT, unit);
    }

    public static String getStringByNow(final long timeSpan,
                                        final DateFormat format,
                                        final UNIT unit) {
        return getString(getNowMills(), format, timeSpan, unit);
    }

    /**
     * 获取与当前时间等于时间差的 Date
     */
    public static Date getDateByNow(final long timeSpan, final UNIT unit) {
        return getDate(getNowMills(), timeSpan, unit);
    }


    /**
     * 是否为今天
     */
    public static boolean isToday(final String time) {
        return isToday(string2Millis(time, DEFAULT_FORMAT));
    }


    public static boolean isToday(final String time, final DateFormat format) {
        return isToday(string2Millis(time, format));
    }

    public static boolean isToday(final Date date) {
        return isToday(date.getTime());
    }

    public static boolean isToday(final long millis) {
        long wee = getWeeOfToday();
        return millis >= wee && millis < wee + UNIT.DAY.value;
    }

    /**
     * 判断是否闰年
     */
    public static boolean isLeapYear(final String time) {
        return isLeapYear(string2Date(time, DEFAULT_FORMAT));
    }

    public static boolean isLeapYear(final String time, final DateFormat format) {
        return isLeapYear(string2Date(time, format));
    }


    public static boolean isLeapYear(final long millis) {
        return isLeapYear(millis2Date(millis));
    }

    public static boolean isLeapYear(final int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static boolean isLeapYear(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    /**
     * 获取中式星期
     */
    public static String getChineseWeek(final String time) {
        return getChineseWeek(string2Date(time, DEFAULT_FORMAT));
    }

    public static String getChineseWeek(final Date date) {
        return new SimpleDateFormat("E", Locale.CHINA).format(date);
    }


    public static String getChineseWeek(final String time, final DateFormat format) {
        return getChineseWeek(string2Date(time, format));
    }

    public static String getChineseWeek(final long millis) {
        return getChineseWeek(new Date(millis));
    }


    /**
     * 获取美式星期
     */
    public static String getUSWeek(final String time) {
        return getUSWeek(string2Date(time, DEFAULT_FORMAT));
    }

    public static String getUSWeek(final String time, final DateFormat format) {
        return getUSWeek(string2Date(time, format));
    }


    public static String getUSWeek(final Date date) {
        return new SimpleDateFormat("EEEE", Locale.US).format(date);
    }


    public static String getUSWeek(final long millis) {
        return getUSWeek(new Date(millis));
    }


    /**
     * 根据日历字段获取值
     * 格式是{yyyy-MM-dd HH:mm:ss}
     * <p>
     * 参数一 ：格式是{yyyy-MM-dd HH:mm:ss}
     * 参数二：
     * <p>
     * <ul>
     * <li>{@link Calendar#ERA}</li>
     * <li>{@link Calendar#YEAR}</li>
     * <li>{@link Calendar#MONTH}</li>
     * <li>...</li>
     * <li>{@link Calendar#DST_OFFSET}</li>
     * </ul>
     */
    public static int getValueByCalendarField(final String time, final int field) {
        return getValueByCalendarField(string2Date(time, DEFAULT_FORMAT), field);
    }


    public static int getValueByCalendarField(final String time,
                                              final DateFormat format,
                                              final int field) {
        return getValueByCalendarField(string2Date(time, format), field);
    }

    public static int getValueByCalendarField(final Date date, final int field) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(field);
    }

    public static int getValueByCalendarField(final long millis, final int field) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return cal.get(field);
    }

    private static final String[] CHINESE_ZODIAC =
            {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};


    /**
     * 获取生肖
     */
    public static String getChineseZodiac(final String time) {
        return getChineseZodiac(string2Date(time, DEFAULT_FORMAT));
    }

    public static String getChineseZodiac(final String time, final DateFormat format) {
        return getChineseZodiac(string2Date(time, format));
    }

    public static String getChineseZodiac(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return CHINESE_ZODIAC[cal.get(Calendar.YEAR) % 12];
    }

    public static String getChineseZodiac(final long millis) {
        return getChineseZodiac(millis2Date(millis));
    }


    public static String getChineseZodiac(final int year) {
        return CHINESE_ZODIAC[year % 12];
    }


    private static final int[] ZODIAC_FLAGS = {20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22};
    private static final String[] ZODIAC = {
            "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座",
            "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"
    };


    /**
     * 获取星座
     */
    public static String getZodiac(final String time) {
        return getZodiac(string2Date(time, DEFAULT_FORMAT));
    }


    public static String getZodiac(final String time, final DateFormat format) {
        return getZodiac(string2Date(time, format));
    }


    public static String getZodiac(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return getZodiac(month, day);
    }


    public static String getZodiac(final long millis) {
        return getZodiac(millis2Date(millis));
    }


    public static String getZodiac(final int month, final int day) {
        return ZODIAC[day >= ZODIAC_FLAGS[month - 1]
                ? month - 1
                : (month + 10) % 12];
    }

    private static long timeSpan2Millis(final long timeSpan, final UNIT unit) {
        return timeSpan * unit.value;
    }


    private static long millis2TimeSpan(final long millis, final UNIT unit) {
        return millis / unit.value;
    }

    private static String millis2FitTimeSpan(long millis, int precision) {
        if (precision <= 0) return null;
        precision = Math.min(precision, 5);
        String[] units = {"天", "小时", "分钟", "秒", "毫秒"};
        if (millis == 0) return 0 + units[precision - 1];
        StringBuilder sb = new StringBuilder();
        if (millis < 0) {
            sb.append("-");
            millis = -millis;
        }
        int[] unitLen = {86400000, 3600000, 60000, 1000, 1};
        for (int i = 0; i < precision; i++) {
            if (millis >= unitLen[i]) {
                long mode = millis / unitLen[i];
                millis -= mode * unitLen[i];
                sb.append(mode).append(units[i]);
            }
        }
        return sb.toString();
    }


}
