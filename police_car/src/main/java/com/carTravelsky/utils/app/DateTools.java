package com.carTravelsky.utils.app;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @ClassName: DateTools
 * @Description: 日期工具类
 * @author: wangyi
 * @date: 2017年9月26日 下午3:25:31
 */
public class DateTools {

    /**
     * 日期格式：yyyy-MM-dd HH:mm
     */
    public static final String DATE_PATTERN_TIME = "yyyy年MM月dd日 HH:mm";

    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式：yyyyMMddHHmmss
     */
    public static final String DATE_PATTERN_STRING = "yyyyMMddHHmmss";
    /**
     * 日期格式：【yyyy】年【MM】月【dd】
     */
    public static final String DATE_PATTERN_DAY_CHINNESS = "【yyyy】年【MM】月【dd】";
    /**
     * 日期格式：yyyy年MM月dd日
     */
    public static final String DATE_PATTERN_DAY_CHINNESS_DEFAULT = "yyyy年MM月dd日";
    /**
     * 日期格式：MM月dd日
     */
    public static final String DAY_CHINNESS_DEFAULT = "MM月dd日";
    /**
     * 日期格式：yyyy-MM-dd
     */
    public static final String DATE_PATTERN_DAY = "yyyy-MM-dd";
    /**
     * 日期格式：yyyyMMdd
     */
    public static final String DATE_PATTERN_NUMBER_DAY = "yyyyMMdd";

    /**
     * 日期格式：yyyyMMdd
     */
    public static final String DATE_PATTERN_DAY_NUM = "yyyyMMdd";

    /**
     * 日期格式：yyyy-MM
     */
    public static final String DATE_PATTERN_MONTH = "yyyy-MM";

    /**
     * 日期格式：dd
     */
    public static final String DATE_PATTERN_JUEST_DAY = "dd";

    /**
     * 每天的最后时间点： " 23:59:59"
     */
    public static final String DAY_LAST_TIME = " 23:59:59";

    /**
     * 每天的最早时间点：" 00:00:00"
     */
    public static final String DAY_FIRST_TIME = " 00:00:00";

    public static String[] weeks = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    @SuppressWarnings("unused")
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @SuppressWarnings("unused")
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 日期格式：yyyyMMddHHmmssSSSS
     */
    public static final String DATE_TIME_MICRO_SECONDS_PATTERN_STRING = "yyyyMMddHHmmssSSSS";

    public static final String DATE_SPECIAL_PATTERN = "yyyy-MM-dd";

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        Date now = new Date();
        return now;
    }

    /**
     * 获取当前时间
     *
     * @return long 类型
     */
    public static long getCurrentTime() {
        return getCurrentDate().getTime();
    }




 

    /**
     * 格式化时间格式
     *
     * @return
     * @date 2016-12-06 09:47:06
     * @author wy
     */
    public static Date formatDate(String date, String pattern) throws ParseException {
        return stringToDate(date, pattern);
    }

   /**
 * @Title: formatStandardDate
 * @Description:将日期格式化为指定形式
 * @param date
 * @param pattern
 * @return
 * @throws ParseException
 * @return: Date
 * @author: wangyu  
 * @date: 2017年11月15日 下午1:06:46
 */
public static Date formatStandardDate(Date date,String pattern) throws ParseException {
       return dateToStandardDate(date, pattern);
   }
    
    
    /**
     * 将字符串转化为日期,需指定字符串日期格式
     *
     * @date 2016-12-06 09:47:15
     * @author wy
     */
    public static Date stringToDate(String str, String pattern) {
        Date dateTime = null;
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        try {
            dateTime = formater.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }
    
    /**
     * @Title: dateToStandardDate
     * @Description: 将时间转换成相应格式的时间
     * @param date
     * @param pattern
     * @return
     * @return: Date
     * @author: wangyu  
     * @date: 2017年11月15日 上午11:29:29
     */
    public static Date dateToStandardDate(Date date, String pattern) {
        Date dateTime = null;
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        try {
            String strDate = formater.format(date);
            dateTime =formater.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /**
     * long  to  date
     *
     * @date 2017-01-03 15:25:15
     * @author wy
     */
    public static Date timeMillisToDate(Long s) {
        if (s == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(s);

        Date date = calendar.getTime();

        return date;
    }

    /**
     * 将年月日转换为 时间戳
     *
     * @date 2017-01-12 10:05:11
     * @author wy
     */
    public static Long dateToStamp(String str) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long timestamp = cal.getTimeInMillis();
        return timestamp;
    }

    /**
     * 日期转时间戳
     *
     * @param date
     * @return
     */
    public static Long dateTimeToStamp(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getTimeInMillis();

    }

    /**
     * 给定一个日期和天数，得到这个日期+天数的日期
     *
     * @param startDate
     * @param num
     * @return
     */
    public static Date getNextDay(Date startDate, int num) {
        Date nextDay = DateUtils.addDays(startDate, num);
        return nextDay;
    }

    /**
     * 给定一个日期和月数，得到这个日期+月数的日期
     *
     * @param startDate
     * @param num
     * @return
     */
    public static Date getNextMonth(Date startDate, int num) {
        Date nextDay = DateUtils.addMonths(startDate, num);
        return nextDay;
    }


    /**
     * 取得一个date对象对应的日期的0点0分0秒时刻的Date对象。
     *
     * @param date
     * @return
     */
    public static Date getMinDateOfDay(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        return calendar.getTime();
    }

    /**
     * 取得一个date对象对应的日期的23点59分59秒时刻的Date对象。
     *
     * @param date
     * @return
     */
    public static Date getMaxDateOfDay(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return calendar.getTime();
    }

    /**
     * 计算两个时间之间相隔几天（两个都会转换成 00：00：00的时间）
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getBetweenDay(Date startDate, Date endDate) {

        if (startDate == null || endDate == null) {
            throw new RuntimeException("时间格式不正确");
        }

        // 进行时间转换
        startDate = getMinDateOfDay(startDate);
        endDate = getMaxDateOfDay(endDate);

        long dayNumber = -1L;
        long DAY = 24L * 60L * 60L * 1000L;

        dayNumber = (endDate.getTime() - startDate.getTime()) / DAY;
        return (int) dayNumber;
    }

    private static long getByField(Calendar beginCalendar, Calendar endCalendar, int calendarField) {
        return endCalendar.get(calendarField) - beginCalendar.get(calendarField);
    }

    /**
     * 获取两个时间相差月份
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws Throwable
     */
    public static long getBetweenMonth(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return 0L;
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar2.setTime(endDate);
        return Math.abs(getByField(calendar, calendar2, Calendar.YEAR) * 12 + getByField(calendar, calendar2, Calendar.MONTH));
    }

    /**
     * 计算两个时间之间相隔几天（两个都会转换成 00：00：00的时间）
     *
     * @param startDate
     * @param endDate
     * @return: int
     * @author wy
     * @Date 2015-12-8 下午1:48:08
     */
    public static long getBetweenSeconds(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new RuntimeException("时间格式不正确");
        }
        long sedNum = -1L;
        long SED = 1000L;
        long diff = (endDate.getTime() - startDate.getTime());
        sedNum = diff / SED;
        return sedNum;
    }


    /**
     * 获取时间是星期几
     * "星期天:0", "星期一:1", "星期二:2", "星期三:3", "星期四:4", "星期五:5", "星期六:6"
     *
     * @return
     * @date 2016-12-06 09:54:25
     * @author wangyi
     */
    public static String getWeekByDate(Date date) {
        if (date == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weeks[w];
    }

    /**
     * 指定日期 返回 一周的第几天
     * <p>默认 为当前日期</p>
     * <p>0(周末),1(周1),2(周2),3(周3),4(周4),5(周5),6(周6)</p>
     *
     * @param date
     * @return
     */
    public static int getWeekNoByDate(Date date) {
        if (date == null) {
            date = getCurrentDate();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return w;
    }

    /**
     * 指定时间后的 指定days 、hours、mins后的时间
     *
     * @param date  指定日期
     * @param days  天数
     * @param hours 小时
     * @param mins  分钟
     * @return
     */
    public static Date getAfterTime(Date date, int days, int hours, int mins) {
        @SuppressWarnings("unused")
		Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            calendar.setTime(date);
        } else {
            calendar.setTime(new Date());
        }
        calendar.add(Calendar.DAY_OF_YEAR, days);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        calendar.add(Calendar.MINUTE, mins);
        return calendar.getTime();
    }

    /**
     * 获取当前年
     *
     * @param date
     * @return
     */
    public static int getCurrentYear(Date date) {
        date = date == null ? getCurrentDate() : date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前月
     *
     * @param date
     * @return
     */
    public static int getCurrentMonth(Date date) {
        date = date == null ? getCurrentDate() : date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }



    public static DateTools build() {
        return new DateTools();
    }

   
    /**
     * @Title: timeAddHour
     * @Description: 在当前时间上增加特殊时间值
     * @param str
     * @param time
     * @param addHour
     * @return
     * @return: Calendar
     */
    public static Date timeAddHour(String str,Date time,Integer addHour){
    	SimpleDateFormat sdf=new SimpleDateFormat(str);
    	//Date date=null;
    	//date = sdf.parse(time);
		//date = DateTools.stringToDate(time, str);
    	Calendar ca=Calendar.getInstance();
    	ca.setTime(time);
    	ca.add(Calendar.HOUR_OF_DAY, addHour);
    	System.out.println(sdf.format(ca.getTime()));
		return ca.getTime();
    }
 
    public static void main(String[] args) {
    	String addOilTime = "2017-12-12 13:09:12";
    	Date date = DateTools.stringToDate(addOilTime, DateTools.DATE_PATTERN_DEFAULT);
    	System.out.println(date);
	}
}
