package eat.arvin.com.mychart.utils;


import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final long millisInDay = 86400000;
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    public static final DateFormat FULL_DATE_FORMAT = new SimpleDateFormat("EEE, MMM d, yyyyy hh:mm:ss aa z");
    public static final DateFormat ISO8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS z");
    private static final SimpleDateFormat mFormat8chars =
            new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat formatter;
    private static final SimpleDateFormat mFormatWeeks = new SimpleDateFormat("ww");
    private static final SimpleDateFormat mFormatDayInWeek = new SimpleDateFormat("EE");
    private static final SimpleDateFormat mFormatYear = new SimpleDateFormat("yyyy");


    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }


    public static int getYear(Date date) {
        return Integer.parseInt(mFormatYear.format(date));
    }

    public static String shortDate(Date aDate) {
        if (aDate == null)
            return "";
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(aDate);
    }

    public static Date getEndOfDay(Date day) {
        return getEndOfDay(day, Calendar.getInstance());
    }

    public static Date getEndOfDay(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public static Date getNoonOfDay(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public static SimpleDateFormat get8charDateFormat() {
        return DateUtil.mFormat8chars;
    }

    public static String mailDate(Date aDate) {
        if (aDate == null)
            return "";
        formatter = new SimpleDateFormat("yyyyMMddHHmm");
        return formatter.format(aDate);
    }

    public static String longDate(Date aDate) {
        if (aDate == null)
            return "";
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(aDate);
    }


    /**
     * Returns a Date set to the first possible millisecond of the day, just
     * after midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfDay(Date day) {
        return getStartOfDay(day, Calendar.getInstance());
    }

    /**
     * Returns a Date set to the first possible millisecond of the day, just
     * after midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfDay(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public static String longDateGB(Date aDate) {
        if (aDate == null)
            return "";
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(aDate);
    }

    public static String longDateLog(Date aDate) {
        if (aDate == null)
            return "";
        formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(aDate);
    }

    public static String formatDate(Date aDate, String formatStr) {
        if (aDate == null)
            return "";
        formatter = new SimpleDateFormat(formatStr);
        return formatter.format(aDate);

    }

    public static String LDAPDate(Date aDate) {
        if (aDate == null)
            return "";
        return formatDate(aDate, "yyyyMMddHHmm'Z'");
    }

    public static Date getDate(String yyyymmdd) {
        if (yyyymmdd == null) return null;
        int year = Integer.parseInt(yyyymmdd.substring(0, yyyymmdd.indexOf("-")));
        int month = Integer.parseInt(yyyymmdd.substring(yyyymmdd.indexOf("-") + 1, yyyymmdd.lastIndexOf("-")));
        int day = Integer.parseInt(yyyymmdd.substring(yyyymmdd.lastIndexOf("-") + 1));
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        return cal.getTime();

    }

    public static Date getDateByMillisecond(String millisecond) {
        if (millisecond == null || millisecond.trim().length() == 0 || millisecond.equals("undefined")) {
            return null;
        }
        try {
            Date date = new Date(Long.parseLong(millisecond));
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parser(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return sdf.parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parser24(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parser24NoSecond(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return sdf.parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }


    public static String getTime(Date aDate, String dateformat) {
        if (aDate == null)
            return "";
        formatter = new SimpleDateFormat(dateformat);
        return formatter.format(aDate);
    }

    public static String formatedate(String befTime, String befformat, String targetformat) {
        if (befTime == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(befformat);
        try {
            Date time = sdf.parse(befTime);
            return formatDate(time, targetformat);
        } catch (Exception e) {
            return "";
        }


    }


    public static Date getShortDate(String date) {
        Date shortDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            shortDate = formatter.parse(date);
        } catch (Exception e) {
            shortDate = null;
        }
        return shortDate;
    }

    public static boolean equals(Date date1, Date date2) {
        if (date1 == null && date2 == null)
            return true;
        if (date1 == null && date2 != null)
            return false;
        if (date1 != null && date2 == null)
            return false;
        return date1.equals(date2);
    }

    /**
     * @return java.util.Date
     */
    public static Date tomorrow() {
        Calendar calender = Calendar.getInstance();
        calender.roll(Calendar.DAY_OF_YEAR, true);
        return calender.getTime();
    }

    /**
     * @param date
     * @return java.util.Date
     */
    public static Date nextDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.roll(Calendar.DAY_OF_YEAR, 1);
        if (isEndOfYear(date, cal.getTime())) {
            cal.roll(Calendar.YEAR, true);
            cal.roll(Calendar.DAY_OF_YEAR, 1);
        }
        return cal.getTime();
    }

    /**
     * @param curDate
     * @param rollUpDate
     * @return boolean
     */
    private static boolean isEndOfYear(Date curDate, Date rollUpDate) {
        return (curDate.compareTo(rollUpDate) >= 0);
    }

    /**
     * @return java.util.Date
     */
    public static Date yesterday() {
        Calendar calender = Calendar.getInstance();
        calender.roll(Calendar.DAY_OF_YEAR, false);
        return calender.getTime();
    }

    /**
     * @param aDate
     * @return String
     */
    private static final String getDateFormat(Date aDate) {
        if (aDate == null)
            return null;
        SimpleDateFormat formatter
                = new SimpleDateFormat("M/d/yyyy");
        return formatter.format(aDate);
    }

    /**
     * @param date
     * @return String
     */
    public static String NVL(Date date) {
        if (date == null)
            return "";
        else
            return getDateFormat(date);
    }

    /**
     * @param baseDate
     * @param type
     * @param num
     * @return Date
     */
    public static Date addDate(Date baseDate, int type, int num) {
        Date lastDate = null;
        try {
            Calendar cale = Calendar.getInstance();
            cale.setTime(baseDate);
            if (type == 1) {
                cale.add(Calendar.YEAR, num);
            } else if (type == 2) {
                cale.add(Calendar.MONTH, num);
            } else if (type == 3) {
                cale.add(Calendar.DATE, num);
            }
            lastDate = cale.getTime();
            return lastDate;
        } catch (Exception e) {
            return null;
        }

    }

    public static Date getDate(String strDate, String formatter) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        try {
            return sdf.parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getSysDate() {
        return new Date(System.currentTimeMillis());
    }

    public static Date getTheFirstDayOfCurMonth(Date date) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.set(Calendar.DAY_OF_MONTH, 1);
        return calender.getTime();
    }

    public static Date getTheFirstDayOfCurMonth(String date) {
        return getTheFirstDayOfCurMonth(getShortDate(date));
    }

    public static String getTheFirstDayOfCurMonthStr(Date date) {
        return shortDate(getTheFirstDayOfCurMonth(date));
    }

    public static String getTheFirstDayOfCurMonthStr(String date) {
        return shortDate(getTheFirstDayOfCurMonth(date));
    }

    public static Date getTheEndDayOfCurMonth(Date date) {
        Date firstDay = getTheFirstDayOfCurMonth(date);
        Calendar calender = Calendar.getInstance();
        calender.setTime(firstDay);
        calender.roll(Calendar.MONTH, 1);
        calender.roll(Calendar.DAY_OF_YEAR, -1);
        return calender.getTime();
    }

    public static Date getTheEndDayOfCurMonth(String date) {
        return getTheEndDayOfCurMonth(getShortDate(date));
    }

    public static String getTheEndDayOfCurMonthStr(Date date) {
        return shortDate(getTheEndDayOfCurMonth(date));
    }

    public static String getTheEndDayOfCurMonthStr(String date) {
        return shortDate(getTheEndDayOfCurMonth(date));
    }

    public static int getDateSpan(Date beginDate, Date endDate, int calType) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(beginDate);
        int[] p1 = {cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)};
        cal.clear();
        cal.setTime(endDate);
        int[] p2 = {cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)};
        int[] s = {p2[0] - p1[0], p2[0] * 12 + p2[1] - p1[0] * 12 - p1[1], (int) ((endDate.getTime() - beginDate.getTime()) / (24 * 3600 * 1000))};
        if (calType <= 3 || calType >= 1) {
            return s[calType - 1];
        } else {
            return 0;
        }
    }

    public static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String formatDate4Video(String content_createtime, String format) {
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String systemTime = sdf.format(new Date()).toString();
        try {
            Date begin = sdf.parse(content_createtime);
            Date end = sdf.parse(systemTime);
            long second = (end.getTime() - begin.getTime()) / 1000L;
            long l = 60L * 1000L;
            long l1 = (end.getTime() - begin.getTime()) / l; // 转换成分钟
            long hl = 60L * 60L * 1000L;
            long h1 = (end.getTime() - begin.getTime()) / hl;   //转换成小时
            if (second < 60) {
                if (second < 3) {
                    date = ("刚刚");
                } else {
                    date = (second + "秒前");
                }
            } else if (l1 < 60 && l1 > 0) {
                date = (l1 + "分钟前");
            } else if (h1 < 24 && h1 > 0) {               //24小时前
                date = (h1 + "小时前");
            } else if (h1 < 48 && h1 >= 24) {               //48小时前
                date = "昨天";
            } else {
                String month = String.format("%tm", begin);
                String day = String.format("%td", begin);
                String hour = String.format("%tH", begin);
                String minutes = String.format("%tM", begin);
                date = month + "-" + day + " " + hour + ":"
                        + minutes;
            }
        } catch (ParseException e) {
            return date;
        }
        return date;

    }

    public static String formatDate4Video(long content_createtime, String format) {
        String date = "";
        Date begin = new Date(content_createtime);
        Date end = new Date(System.currentTimeMillis());
        long second = (end.getTime() - begin.getTime()) / 1000L;
        long l = 60L * 1000L;
        long l1 = (end.getTime() - begin.getTime()) / l; // 转换成分钟
        long hl = 60L * 60L * 1000L;
        long h1 = (end.getTime() - begin.getTime()) / hl;   //转换成小时
        if (second < 60) {
            if (second < 3) {
                date = ("刚刚");
            } else {
                date = (second + "秒前");
            }
        } else if (l1 < 60 && l1 > 0) {
            date = (l1 + "分钟前");
        } else if (h1 < 24 && h1 > 0) {               //24小时前
            date = (h1 + "小时前");
        } else if (h1 < 48 && h1 >= 24) {               //48小时前
            date = "昨天";
        } else {
            String month = String.format("%tm", begin);
            String day = String.format("%td", begin);
            String hour = String.format("%tH", begin);
            String minutes = String.format("%tM", begin);
            date = month + "-" + day + " " + hour + ":"
                    + minutes;
        }
        return date;

    }


    public static String formatDate4Video(long content_createtime) {
        String date = "";
        try {
            long begin = content_createtime;
            long end = System.currentTimeMillis();
            long second = (end - begin) / 1000L;
            long l = 60L * 1000L;
            long l1 = (end - begin) / l; // 转换成分钟
            long hl = 60L * 60L * 1000L;
            long h1 = (end - begin) / hl;   //转换成小时
            long dl = 24 * 60L * 60L * 1000L;
            long d1 = (end - begin) / dl;   //转换成天
            long ml = 30 * 24 * 60L * 60L * 1000L;
            long m1 = (end - begin) / ml;   //转换成月
            long yl = 12 * 30 * 24 * 60L * 60L * 1000L;
            long y1 = (end - begin) / yl;   //转换成年
            if (m1 < 12) {
                if (second < 60) {
                    if (second < 3) {
                        date = ("刚刚");
                    } else {
                        date = (second + "秒前");
                    }
                } else if (l1 < 60 && l1 > 0) {
                    date = (l1 + "分钟前");
                } else if (h1 < 24 && h1 > 0) {               //24小时前
                    date = (h1 + "小时前");
                } else if (d1 < 31 && d1 > 0) {               //30天前
                    date = (d1 + "天前");
                } else if (m1 < 13 && m1 > 0) {               //12小时前
                    date = (m1 + "个月前");
                } else {
                    date = y1 + "年前";
                }
            } else {
                date = y1 + "年前";
            }
        } catch (Exception e) {
            return date;
        }
        return date;

    }

    public static String formatDate(String time1, String format, String targetFormat) {
        String targettime = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date1 = sdf.parse(time1);
            SimpleDateFormat targetsdf = new SimpleDateFormat(targetFormat);
            targettime = targetsdf.format(date1);
        } catch (ParseException e) {
            return null;
        }
        return targettime;
    }

    /**
     * 观看时间转换
     *
     * @param playedTime
     * @return
     */
    public static String parsePlayedTime(int playedTime) {
        String parsePt = "";
        int second = playedTime / 1000;
        int minute = 0;
        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        String seconds = String.valueOf(second);
        if (second < 10) {
            seconds = "0" + second;
        }
        String minutes = String.valueOf(minute);
        if (minute < 10) {
            minutes = "0" + minute;
        } else if (minute == 0) {
            minutes = "00";
        }
        parsePt = minutes + ":" + seconds;
        return parsePt;
    }

    /**
     * 与当前时间比较，如果晚于当前时间则返回true
     *
     * @param time1
     * @param currenttime
     * @return
     */
    public static boolean compareTime(String time1, String currenttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = sdf.parse(time1);
            Date currentDate = sdf.parse(currenttime);
            return !(date1.before(currentDate) || date1.equals(currentDate));
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 获取日期中的月份
     *
     * @param strDate
     * @return
     */
    public static String getMonthFromStr(String strDate) {
        String month = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt = sdf.parse(strDate);
            month = String.valueOf(dt.getMonth() + 1);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return month;
    }

    /**
     * 获取日期中的年份
     *
     * @param strDate
     * @return
     */
    public static String getYearFromStr(String strDate) {
        String year = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt = sdf.parse(strDate);
            year = String.valueOf(getYear(dt));
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return year;
    }

    /**
     * 获取两个日期之间的月份间隔
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getMonthBetweenDate(String date1, String date2) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        try {
            d1 = sd.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d2 = null;
        try {
            d2 = sd.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int months = 0;//相差月份
        int y1 = d1.getYear();
        int y2 = d2.getYear();
        if (d1.getTime() < d2.getTime()) {
            months = d2.getMonth() - d1.getMonth() + (y2 - y1) * 12 + 1;
        }
        return months;
    }

    public static long parseTime4Long(String time) {
        long datetime = -1;
        Date date = parser24(time);
        if (date != null) {
            datetime = date.getTime();
        }
        return datetime;
    }


    public static long dateToLong(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getTimeInMillis();
    }

    /**
     * 时分秒转成字符串
     *
     * @param time
     * @return
     */
    public static String getHms(long time) {
        String hms = "";
        long hour = 0l, minutes = 0l, seconds = 0l;
        hour = time / 3600;
        minutes = (time / 60) % 60;
        seconds = time % 60;
        hms = String.format("%02d:%02d:%02d", hour, minutes, seconds);
        return hms;
    }

    public static String[] getDateTime(String time) {
        String[] day = new String[2];

        Date date2 = parser24NoSecond(time);

        int month = date2.getMonth();
        int date = date2.getDate();
        int hour = date2.getHours();
        int minute = date2.getMinutes();
        day[0] = String.format("%02d-%02d", (month + 1), date);
        day[1] = String.format("%02d:%02d", hour, minute);

        return day;
    }

    public static int compareDay(String time) {
        int diff = -1;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date begindate = formatter.parse(time);
            Date end = new Date();
            int ey = end.getYear();
            int by = begindate.getYear();
            int ed = end.getDate();
            int bd = begindate.getDate();
            if (ey == by) {
                diff = ed - bd;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

    /**
     * 得到时间的tab
     *
     * @param newsDate 时间戳
     * @return
     */
    public static String getBeforeDateTwo(String newsDate) {
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数long diff;try
        long diff = 0;

        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 格式化当前系统日期
        String dateTime = dateFm.format(new java.util.Date());


        // 获得两个时间的毫秒时间差异
        try {
            diff = dateFm.parse(dateTime).getTime()
                    - Long.parseLong(newsDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long day = diff / nd;// 计算差多少天
        if (day > 0) {
            if (day > 30) {
                long month = day / 30;
                if (month > 11) {
                    return "一年前";
                } else {
                    return month + "个月前";
                }
            } else {
                return day + "天前";
            }
        }
        long hour = diff % nd / nh;// 计算差多少小时
        if (hour > 0) {
            return hour + "小时前";
        }
        long min = diff % nd % nh / nm;// 计算差多少分钟
        if (min > 0) {
            return min + "分钟前";
        }
        long sec = diff % nd % nh % nm / ns;// 计算差多少秒//输出结果

        return "刚刚";
    }

    /**
     * 金网返回时间处理成hh:mm
     *
     * @param time yyyy-MM-dd HH:mm:ss 2016-08-25 16:03:10
     * @return
     */
    public static String getShortDateJustHourWithStringTime(String time) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return getTranTime(formatter.parse(time).getTime());
    }

    /**
     * 返回时间  HH:mm
     *
     * @param date
     * @return
     */
    public static String getShortDateJustHour(long date) {
        String shortDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        try {
            Long time = new Long(date * 1000);
            shortDate = formatter.format(time);
        } catch (Exception e) {
            shortDate = null;
        }
        return shortDate;
    }

    /**
     * 返回时间格式 HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getTranTime(long date) {
        String shortDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        try {
            Long time = new Long(date * 1000);
            shortDate = formatter.format(time);
        } catch (Exception e) {
            shortDate = null;
        }
        return shortDate;
    }

    /**
     * 返回时间错的  MM-dd
     *
     * @param date
     * @return
     */
    public static String getShortDateJustMonthAndDay(long date) {
        String shortDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        try {
            Long time = new Long(date * 1000);
            shortDate = formatter.format(time);
        } catch (Exception e) {
            shortDate = null;
        }
        return shortDate;
    }

    /**
     * 返回时间  MM-dd HH:mm
     *
     * @param date
     * @return
     */
    public static String getDateAndTime(long date) {
        String shortDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
        try {
            Long time = new Long(date * 1000);
            shortDate = formatter.format(time);
        } catch (Exception e) {
            shortDate = null;
        }
        return shortDate;
    }

    /**
     * 返回时间错的  YY-MM-dd
     *
     * @param date
     * @return
     */
    public static String getShortDateJustYMD(long date) {
        String shortDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Long time = new Long(date * 1000);
            shortDate = formatter.format(time);
        } catch (Exception e) {
            shortDate = null;
        }
        return shortDate;
    }

    /**
     * 返回时间错的  xxxx年xx月xx日
     *
     * @param date
     * @return
     */
    public static String getShortDateJustMD(long date) {
        String shortDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        try {
//            Long time=new Long(date);
//            shortDate = formatter.format(time);
            shortDate = formatter.format(new Date(date * 1000));
        } catch (Exception e) {
            shortDate = null;
        }
        return shortDate;
    }

    /**
     * 返回时间 xxx月xx日
     *
     * @param date
     * @return
     */
    public static String getMonthDay(long date) {
        String shortDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        try {
            shortDate = formatter.format(new Date(date * 1000));
        } catch (Exception e) {
            shortDate = null;
        }
        return shortDate;
    }


    public static String getWeekOfDate(long date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

        Calendar calendar = Calendar.getInstance();

        Long time = new Long(date * 1000);
        calendar.setTime(new Date(time));

        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        Log.e("tag", w + "======================w===========" + date);

        return weekDays[w];
    }

    /**
     * 传入开始时间结束时间，计算经过的每天
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 经过的时间(2016年6月12;2016年6月13)
     */
    public static ArrayList<String> getDateList(long start, long end) {
        ArrayList<String> list = new ArrayList<String>();
        long length = (end - start) / 86400 + 1;
        for (long i = 0; i < length; i++) {
            list.add(getShortDateJustMD(start + i * 86400));
        }
        return list;
    }

    /**
     * 判断是否今天
     *
     * @param time
     * @return
     */
    public static boolean isToday(long time) {
        Calendar calendar = Calendar.getInstance();
        Calendar old = Calendar.getInstance();
        old.setTime(new Date(time));
        return old.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) && old.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 是否今年
     * @param time
     * @return
     */
    public static boolean isThisYear(long time) {
        Calendar calendar = Calendar.getInstance();
        Calendar old = Calendar.getInstance();
        old.setTime(new Date(time));
        return old.get(Calendar.YEAR) == calendar.get(Calendar.YEAR);
    }

    /**
     * 获取时间，金网和恒生返回的不一样
     *
     * @return
     */
    public static String getTimeString(String time) {
        try {
            long i = Long.parseLong(time);
            return DateUtil.getTranTime(i);
        } catch (Exception e) {
            try {
                return DateUtil.getShortDateJustHourWithStringTime(time);
            } catch (Exception e1) {
                return time;
            }
        }
    }

    /**
     * 交易所返回的时间 转为long值，以便进行排序
     * @param time
     * @return
     */
     public static long getTimeLong(String time) {
         try {
             long i = Long.parseLong(time);
             return i;
         } catch (Exception e) {
             try {
                 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                 return formatter.parse(time).getTime();
             } catch (Exception e1) {
                 try {
                    SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
                    return formatter2.parse(time).getTime();
                 } catch (Exception e2) {
                     return 0;
                 }
             }
         }
     }
}

