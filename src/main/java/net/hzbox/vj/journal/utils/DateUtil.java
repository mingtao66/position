package net.hzbox.vj.journal.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtil extends DateUtils {
    private static Logger log = LoggerFactory.getLogger(DateUtil.class);
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static long SENCOD_IN_MILLS = 1000L;
    public static long MINUTE_IN_MILLS = SENCOD_IN_MILLS * 60;
    public static long HOUR_IN_MILLS = MINUTE_IN_MILLS * 60;
    public static long DAY_IN_MILLS = HOUR_IN_MILLS * 24;

    private static final String[] parsePatterns = new String[] {
        "yyyy-MM-dd HH:mm:ss",
        "yyyy-MM-dd HH:mm",
        "yyyyMMdd",
        "yyyy-MM-dd",
        "yyyy-MM",
        "HH:mm",
        "MM/dd/yyyy HH:mm:ss",
        "MM/dd/yyyy"
        // 这里可以增加更多的日期格式，用得多的放在前面  
    };

    public static String format(Date date, String format) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat df = new SimpleDateFormat(StringUtils.defaultIfEmpty(
            format, DEFAULT_FORMAT));
        return df.format(date);
    }

    public static String format(Date date) {
        return format(date, DEFAULT_FORMAT);
    }

    public static Date parse(String date) {
        try {
            return parseDate(date, parsePatterns);
        } catch (ParseException e) {
            log.error("", e);
        }
        return null;
    }

    public static Date parse(String date, String format) {
        try {
            return parseDate(date, format);
        } catch (ParseException e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 判断是否是昨天的某个时间
     */
    public static boolean isYesterdaySomeTime(Date date) {
        return isSomeTimeInDay(date, -1);
    }

    public static boolean isSomeTimeInDay(Date date, int dayIndex) {
        if (date == null) {
            return false;
        }

        Date d2 = addDays(new Date(), dayIndex);
        return isSameDay(date, d2);
    }


    /**
     * 获取某一天及其前后几天的零点
     */
    public static Date getZeroTime(Date date, int n) {
        return addDays(truncate(date, Calendar.DAY_OF_MONTH), n);
    }

    /**
     * 判断两个日期是否是同一天
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        return truncatedEquals(date1, date2, Calendar.DAY_OF_MONTH);
    }

    /**
     * @param early
     * @param late
     * @param type  {@link Calendar#HOUR_OF_DAY} etc..
     * @return
     */
    public static int timeBetween(Date early, Date late, int type) {
        long v = 1L;
        switch (type) {
            case Calendar.DAY_OF_MONTH:
                v = DAY_IN_MILLS;
                break;
            case Calendar.HOUR:
            case Calendar.HOUR_OF_DAY:
                v = HOUR_IN_MILLS;
                break;
            case Calendar.MINUTE:
                v = MINUTE_IN_MILLS;
                break;
            case Calendar.SECOND:
                v = SENCOD_IN_MILLS;
                break;
            case Calendar.MILLISECOND:
                // default 1
                break;
            default:
                throw new RuntimeException("Unsupported type of:" + type);
        }

        return (int) ((late.getTime() - early.getTime()) / v);
    }


    /**
     * 数据库定义 1 SOLAR 阳历; 2 LUNAR 阴历; 增加START 占位enum
     *
     * @author iacdp
     */
    public enum Type {
        START(""), SOLAR("阳历"), LUNAR("阴历");

        private String description;

        private Type(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    /**
     * 判断当前日期是星期几
     *
     * @param dateTime 要判断的时间
     * @return dayForWeek 判断结果
     */
    public static String dayForWeek(Date dateTime) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(dateTime);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return String.valueOf(dayForWeek);
    }

    /**
     * 根据日期格式，返回指定日期指定格式转换后的字符串
     *
     * @param date    日期对象
     * @param pattern 指定转换格式
     * @return 格式化后的日期字符串
     */
    public static final String getDate(Date date, String pattern) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (date != null) {
            df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    /**
     * 按照日期格式，将字符串解析为日期对象
     *
     * @param aMask   输入字符串的格式
     * @param strDate 一个按aMask格式排列的日期的字符串描述
     * @return Date 对象
     * @throws ParseException
     * @see SimpleDateFormat
     */
    public static final Date convertStringToDate(String aMask, String strDate) {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);
        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            log.error("", pe);
        }
        return (date);
    }


    /**
     * 获取当前时间的毫秒
     *
     * @return 返回毫秒
     */
    public static long getMillis() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 获取当前日期所在月份的所有日期
     *
     * @param date 当前日期
     * @return List<Date>
     */
    public static List<Date> getAllTheDateOftheMonth(Date date) {
        List<Date> list = new ArrayList<Date>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        int month = cal.get(Calendar.MONTH);
        while (cal.get(Calendar.MONTH) == month) {
            list.add(cal.getTime());
            cal.add(Calendar.DATE, 1);
        }
        return list;
    }

    /**
     * 获取指定日期是周几
     *
     * @param date 指定日期
     * @return 指定日期对应的周几
     */
    public static String getDayOfWeekByDate(Date date) {
        String dayOfweek = "-1";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(myFormatter.format(date));
            SimpleDateFormat formatter = new SimpleDateFormat("E", Locale.CHINA);
            String str = formatter.format(myDate);
            dayOfweek = str.replace("星期", "周");

        } catch (Exception e) {
            log.error("错误！", e);
        }
        return dayOfweek;
    }
}
