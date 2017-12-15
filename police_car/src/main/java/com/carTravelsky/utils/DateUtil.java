package com.carTravelsky.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 日期工具
 * 
 * @author hc24
 */
public class DateUtil {

    /** 月份 */
    private static final Map<String, String> MONTH = new HashMap<String, String>();
    /** 星期 */
    private static final Map<String, String> WEEK = new HashMap<String, String>();
    /** 中文数字 */
    private static final Map<String, String> NUMBER = new HashMap<String, String>();

    static {
        MONTH.put("01", "JAN");
        MONTH.put("02", "FEB");
        MONTH.put("03", "MAR");
        MONTH.put("04", "APR");
        MONTH.put("05", "MAY");
        MONTH.put("06", "JUN");
        MONTH.put("07", "JUL");
        MONTH.put("08", "AUG");
        MONTH.put("09", "SEP");
        MONTH.put("10", "OCT");
        MONTH.put("11", "NOV");
        MONTH.put("12", "DEC");

        MONTH.put("JAN", "01");
        MONTH.put("FEB", "02");
        MONTH.put("MAR", "03");
        MONTH.put("APR", "04");
        MONTH.put("MAY", "05");
        MONTH.put("JUN", "06");
        MONTH.put("JUL", "07");
        MONTH.put("AUG", "08");
        MONTH.put("SEP", "09");
        MONTH.put("OCT", "10");
        MONTH.put("NOV", "11");
        MONTH.put("DEC", "12");

        WEEK.put(Calendar.SUNDAY + "", "日");
        WEEK.put(Calendar.MONDAY + "", "一");
        WEEK.put(Calendar.TUESDAY + "", "二");
        WEEK.put(Calendar.WEDNESDAY + "", "三");
        WEEK.put(Calendar.THURSDAY + "", "四");
        WEEK.put(Calendar.FRIDAY + "", "五");
        WEEK.put(Calendar.SATURDAY + "", "六");

        NUMBER.put("0", "零");
        NUMBER.put("1", "一");
        NUMBER.put("2", "二");
        NUMBER.put("3", "三");
        NUMBER.put("4", "四");
        NUMBER.put("5", "五");
        NUMBER.put("6", "六");
        NUMBER.put("7", "七");
        NUMBER.put("8", "八");
        NUMBER.put("9", "九");
    }

    private DateUtil() {
    }

    /**
     * 获取当前时间
     * 
     * @return 当前时间
     */
    public static Date now() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 将yyyy-MM-dd或yyyy/MM/dd格式的日期字符串转换为日期类型
     * 
     * @param ymd
     *            yyyy-MM-dd或yyyy/MM/dd格式的字符串
     * @return 日期,转化失败返回null
     */
    public static Date ymd2Date(String ymd) {
        String[] f = { "yyyy-MM-dd", "yyyy/MM/dd" };
        for (String o : f) {
            return parse(ymd, o);
        }
        return null;
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式的日期字符串转换为日期类型
     * 
     * @param ymdhms
     *            yyyy-MM-dd HH:mm:ss格式的字符串
     * @return 日期 转换失败返回null
     */
    public static Date ymdhms2Date(String ymdhms) {
        return parse(ymdhms, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将指定格式字符串转换为日期
     * 
     * @param s
     *            字符串
     * @param pattern
     *            格式
     * @return 日期 转换失败返回null
     */
    public static Date parse(String s, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(s);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将日期转换成yyyy-MM-dd格式的字符串
     * 
     * @param date
     *            日期
     * @return yyyy-MM-dd格式的字符串
     */
    public static String ymd(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    /**
     * 将日期转换成HH:mm:ss格式的字符串
     * 
     * @param date
     *            日期
     * @return HH:mm:ss格式的字符串
     */
    public static String hms(Date date) {
        return format(date, "HH:mm:ss");
    }

    /**
     * 将日期转换成yyyy-MM-dd HH:mm:ss格式的字符串
     * 
     * @param date
     *            日期
     * @return yyyy-MM-dd HH:mm:ss格式的字符串
     */
    public static String ymdhms(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将日期转换成指定格式的字符串
     * 
     * @param date
     *            日期
     * @return 指定格式的字符串
     */
    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 指定类型往前推
     * 
     * @param type
     *            类型：年、月、日等
     * @param date
     *            参考日期
     * @param length
     *            长度
     * @return 往前推的日期
     */
    public static Date previous(int type, Date date, int length) {
        return next(type, date, 0 - length);
    }

    /**
     * 指定类型往后推
     * 
     * @param type
     *            类型：年、月、日等
     * @param date
     *            参考日期
     * @param length
     *            长度
     * @return 往后推的日期
     */
    public static Date next(int type, Date date, int length) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(type, length);
        return c.getTime();
    }

    /**
     * 获得前几天的日期
     * 
     * @param date
     *            参考日期
     * @return 前几天的日期
     */
    public static Date previous(Date date, int days) {
        return previous(Calendar.DATE, date, days);
    }

    /**
     * 获得后几天的日期
     * 
     * @param date
     *            参考日期
     * @return 后几天的日期
     */
    public static Date next(Date date, int days) {
        return next(Calendar.DATE, date, days);
    }

    /**
     * 月份往后推的日期
     * 
     * @param date
     *            参考日期
     * @param months
     *            推后几个月
     * @return 月份往后推的日期
     */
    public static Date nextMonth(Date date, int months) {
        return next(Calendar.MONTH, date, months);
    }

    /**
     * 月份往前推的日期
     * 
     * @param date
     *            参考日期
     * @param months
     *            前推几个月
     * @return 月份往前推的日期
     */
    public static Date previousMonth(Date date, int months) {
        return previous(Calendar.MONTH, date, months);
    }

    /**
     * 年份往后推的日期
     * 
     * @param date
     *            参考日期
     * @param months
     *            推后几个月
     * @return 月份往后推的日期
     */
    public static Date nextYear(Date date, int years) {
        return next(Calendar.YEAR, date, years);
    }

    /**
     * 年份往前推的日期
     * 
     * @param date
     *            参考日期
     * @param months
     *            前推几个月
     * @return 月份往前推的日期
     */
    public static Date previousYear(Date date, int years) {
        return previous(Calendar.YEAR, date, years);
    }

    /**
     * 某日期的某类型的数值
     * 
     * @param type
     *            类型：年、月、日等
     * @param date
     *            日期
     * @return 数值
     */
    public static int get(int type, Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(type);
    }

    /**
     * 获取年份
     * 
     * @param date
     *            日期
     * @return 年份
     */
    public static int year(Date date) {
        return get(Calendar.YEAR, date);
    }

    /**
     * 获取月份
     * 
     * @param date
     *            日期
     * @return 月份，如01、10
     */
    public static String month(Date date) {
        int m = get(Calendar.MONTH, date) + 1;
        return (m < 10 ? "0" : "") + m;
    }

    /**
     * 获取天
     * 
     * @param date
     *            日期
     * @return 天，如01、23
     */
    public static String day(Date date) {
        int d = get(Calendar.DATE, date);
        return (d < 10 ? "0" : "") + d;
    }

    /**
     * 日期字符串转换为YYYY-MM-DD格式
     * 
     * @param dayMonthYear
     *            支持的参数格式: 1MAY10 01MAY10 1MAY2010
     *            01MAY2010，不区分大小写，以上转换后为2010-05-01
     * @return YYYY-MM-DD格式的字符串
     */
    public static String ymd(String dayMonthYear) {
        int pre = year(now());
        return ymd(dayMonthYear, String.valueOf(pre));
    }

    /**
     * 日期字符串转换为YYYY-MM-DD格式
     * 
     * @param dayMonthYear
     *            支持的参数格式: 1MAY10 01MAY10 1MAY2010 01MAY2010
     * @param yearPrefix
     *            年份前缀，如yearPrefix=19,1MAY09=1909-05-01
     * @return YYYY-MM-DD格式的字符串
     */
    public static String ymd(String dayMonthYear, String yearPrefix) {
        String s = dayMonthYear.toUpperCase();
        switch (s.length()) {
        case 4:
            s = "0" + s;
            s = s + yearPrefix;
            break;
        case 5:
            s = s + yearPrefix;
            break;
        case 6:
            s = "0" + s;
            // s = s.substring(0,5)+yearPrefix+s.substring(5);
            s = s.substring(0, 5) + yearPrefix;
            break;
        case 7:
            s = s.substring(0, 5) + yearPrefix;
            break;
        case 8:
            s = "0" + s;
            break;
        }
        System.out.println(s);
        s = s.substring(5) + "-" + MONTH.get(s.substring(2, 5)) + "-"
                + s.substring(0, 2);
        return s;
    }

    /**
     * 获取某日期的星期中文表示
     * 
     * @param date
     *            日期
     * @return 日、一、二、三、四、五、六
     */
    public static String weekCH(Date date) {
        return WEEK.get(get(Calendar.DAY_OF_WEEK, date));
    }

    /**
     * 获取某日期的月份中文表示
     * 
     * @param date
     *            日期
     * @return 日、一、二、三、四、五、六
     */
    public static String monthCH(Date date) {
        return new String[] { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十",
                "十一", "十二" }[get(Calendar.MONTH, date)];
    }

    /**
     * 获取某日期的年份中文表示
     * 
     * @param date
     *            日期
     * @return 年份，如二零一零
     */
    public static String yearCH(Date date) {
        String[] y = (year(date) + "").split("");
        StringBuffer s = new StringBuffer();
        for (String o : y) {
            s.append(null == NUMBER.get(o) ? "" : NUMBER.get(o));
        }
        return s.toString();
    }

    /**
     * 
     * @param date
     * @return 月份的英文表示 JUN、JUL
     */
    public static String monthEN(Date date) {
        return MONTH.get(month(date));
    }

    /**
     * 将date类型转换为01JUL12这种格式
     * 
     * @param date
     * @return
     */
    public static String dateEN(Date date) {
        String s = day(date) + monthEN(date) + year(date);
        return s.substring(0, 5) + s.substring(7);
    }

    public static Timestamp toTimeStamp(Date date) {
        return new Timestamp(date.getTime());
    }


    
    public static String monthInt(String date) {
        return MONTH.get(date);
    }
    public static void main(String[] args) {
        // System.out.println(yearCH(now()) + "年" + monthCH(now()) + "月");

    }
}