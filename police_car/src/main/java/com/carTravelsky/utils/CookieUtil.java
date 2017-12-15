package com.carTravelsky.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlbeans.impl.util.Base64;

import com.carTravelsky.bean.system.CarSysUserDO;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.utils.StringUtils;



public class CookieUtil {
	protected static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);

   //保存cookie时的cookieName
   private final static String cookieDomainName = "carTravelsky";
   //加密cookie时的网站自定码
   private final static String webKey = "123456";
   //设置cookie有效期是两个星期，根据需要自定义
   private final static long cookieMaxAge = 60 * 60 * 24 * 7 * 2;

   //保存Cookie到客户端
   //在CheckLogonServlet.java中被调用
   //传递进来的user对象中封装了在登陆时填写的用户名与密码
   public static void saveCookie(CarSysUserDO carSysUserDO, HttpServletResponse response) {
       //cookie的有效期
       long validTime = System.currentTimeMillis() + (cookieMaxAge * 1000);
       //MD5加密用户详细信息
       String cookieValueWithMd5 =getMD5(carSysUserDO.getUsername()+ ":" + carSysUserDO.getPassword()
                                  + ":" + validTime + ":" + webKey);
       //将要被保存的完整的Cookie值
       String cookieValue = carSysUserDO.getUsername()+ ":" + validTime + ":" + cookieValueWithMd5;
       //再一次对Cookie的值进行BASE64编码
       
       String cookieValueBase64 = new String(Base64.encode(cookieValue.getBytes()));
       //开始保存Cookie
       Cookie cookie = new Cookie(cookieDomainName, cookieValueBase64);
       //存两年(这个值应该大于或等于validTime)
       cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
       //cookie有效路径是网站根目录
       cookie.setPath("/");
       //向客户端写入
       response.addCookie(cookie);
   }
  
   //用户注销时,清除Cookie,在需要时可随时调用
   public static void clearCookie( HttpServletResponse response){
       Cookie cookie = new Cookie(cookieDomainName, null);
       cookie.setMaxAge(0);
       cookie.setPath("/");
       response.addCookie(cookie);
   }

   //获取Cookie组合字符串的MD5码的字符串
   public static String getMD5(String value) {
       String result = null;
       try{
           byte[] valueByte = value.getBytes();
           MessageDigest md = MessageDigest.getInstance("MD5");
           md.update(valueByte);
           result = toHex(md.digest());
       } catch (NoSuchAlgorithmException e2){
           e2.printStackTrace();
       }
       return result;
   }

   //将传递进来的字节数组转换成十六进制的字符串形式并返回
   private static String toHex(byte[] buffer){
       StringBuffer sb = new StringBuffer(buffer.length * 2);
       for (int i = 0; i < buffer.length; i++){
           sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
           sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
       }
       return sb.toString();
   }
   /*public static void ListSort(List<DailyDO> list) {
       Collections.sort(list, new Comparator<DailyDO>() {
           @Override
           public int compare(DailyDO o1, DailyDO o2) {
               SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
               try {
                   Date dt1 = format.parse(o1.getReportDate());
                   Date dt2 = format.parse(o2.getReportDate());
                   if (dt1.getTime() > dt2.getTime()) {
                       return -1;
                   } else if (dt1.getTime() < dt2.getTime()) {
                       return 1;
                   } else {
                       return 0;
                   }
               } catch (Exception e) {
            	   logger.error("格式化时间出错{0}", e);a
               }
               return 0;
           }
       });
   }*/
   /**
    * 判断重要属性是不为空
    * @param str
    * @param eqs
    * @return
    */
   public static Boolean getEqueals(String[] str,String eqs){
		for (String string : str) {
			if(string == null){
				return false;
			}else{
				if (StringUtils.equals(string,"")) {
					return false;
				}
			}
		}
	   return true;
   }
   /**
    * 根据日期，获得当月第几周
    * @param year
    * @param week
 * @return 
    * @return
 * @throws ParseException 
    */
	public static String getWeekFirst(String dateString) {
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(dateString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			 calendar.setFirstDayOfWeek(Calendar.MONDAY);  
			int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
			int month = calendar.get(Calendar.MONTH)+1;
			return month+"月第"+weekOfMonth+"周";
		} catch (Exception e) {
			logger.error("时间格式化出错", e);
		}
		return null;
	}
	/**
	 * 获取当前年的周数 至少52周，最多53
	 * @param date
	 * @return
	 */
	 public static int getWeekOfYear(Date date) {
		  Calendar c = new GregorianCalendar();
		  c.setFirstDayOfWeek(Calendar.MONDAY);
		  c.setMinimalDaysInFirstWeek(7);
		  c.setTime(date);
		  return c.get(Calendar.WEEK_OF_YEAR);
		 }
	public static void main(String[] args) {
		System.out.println(getMonthWeeks());
		  Calendar c = Calendar.getInstance();  
	        c.set(Calendar.YEAR, 2017); // 2010年  
	        System.out.println("------------" + c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月的天数和周数-------------");  
	        System.out.println("天数：" + c.getActualMaximum(Calendar.DAY_OF_MONTH));  
	        System.out.println("周数：" + c.getActualMaximum(Calendar.WEEK_OF_MONTH));  
	}
	public static  List<String> getMonthWeeks(){
		List<String> list = new ArrayList<>();
		 for (int j = 1; j <= 12; j++) {
			 	Calendar c = Calendar.getInstance();  
		        c.set(Calendar.YEAR,2017); // 2010年  
		        c.set(Calendar.MONTH, j);
		       int maxWeeks = c.getActualMaximum(Calendar.WEEK_OF_MONTH);
		       for (int k = 1; k <= maxWeeks; k++) {
				list.add(j+"月的第"+k+"周");
			}
		        
		}
		 return list;
	}
}