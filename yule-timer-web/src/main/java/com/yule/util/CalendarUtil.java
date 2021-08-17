package com.yule.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.yule.enumerate.DateStyle;

public class CalendarUtil {

	/**
	 * 获取当前 前一个月
	 */
	public static String lastMonth() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		String last_month = DateUtil.DateToString(c.getTime(),
				DateStyle.YYYY_MM);
		c = null;
		return last_month;
	}

	/**
	 * 获取本周一
	 */
	public static Date getNowWeekMonday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -1); // 解决周日会出现 并到下一周的情况
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	/**
	 * 获取上周一
	 */
	public static String getLastWeekMonday() {
		Date a = DateUtils.addDays(new Date(), -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.set(Calendar.HOUR_OF_DAY, 0);  
		cal.set(Calendar.MINUTE, 0);  
		cal.set(Calendar.SECOND,0);  
		cal.set(Calendar.MILLISECOND, 0);  
		cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return DateUtil.DateToString(cal.getTime(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN);
	}

	/**
	 * 获取上周日
	 */
	public static String getLastWeekSunday() {
		Date a = DateUtils.addDays(new Date(), -1);    
        Calendar cal = Calendar.getInstance();   
        cal.setTime(a);
		cal.set(Calendar.HOUR_OF_DAY, 23);  
		cal.set(Calendar.MINUTE, 59);  
		cal.set(Calendar.SECOND,59);  
		cal.set(Calendar.MILLISECOND, 999); 
        cal.set(Calendar.DAY_OF_WEEK, 1);    
        return DateUtil.DateToString(cal.getTime(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN);
	}
	
}