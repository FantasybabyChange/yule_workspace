package com.yule.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.yule.constant.CalendarConst;
import com.yule.enumerate.DateStyle;
import com.yule.util.DateUtil;

public class CalendarUtil {

	/**
	 *查询指定日期间的所有日期 
	 * @param start_time 
	 * @param end_time
	 * @param time_type 时间类型  2 天 1 月 0 年 
	 * @return
	 */
	public static List<String> findAreaDates(String start_time, String end_time,Integer time_type) {
		List<String> dates = new ArrayList<String>();
		dates.add(start_time.subSequence(0, CalendarConst.FIRSTDAYSUB[time_type]).toString());
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(DateUtil.StringToDate(start_time));
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(DateUtil.StringToDate(end_time));
		Date end_datetime = DateUtil.StringToDate(end_time);
		while (end_datetime.after(calBegin.getTime())) {
			calBegin.add(CalendarConst.CALENDARTYPE[time_type], 1);
			dates.add(DateUtil.DateToString(calBegin.getTime(), CalendarConst.DATESTYLE[time_type]));
		}
		if (null!=dates&&dates.size()>=2) {
			if (dates.get(dates.size()-2).equals(end_time.subSequence(0, CalendarConst.FIRSTDAYSUB[time_type]).toString())) {
				dates.remove(dates.size()-1);
			}
		}
		return dates;
	}	
	/**
	 * 获取当前月的第一天
	 */
	public static String fisrtDay(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);
		String first_day = DateUtil.DateToString(c.getTime(), DateStyle.YYYY_MM_DD);
		c = null;
		return first_day+" 00:00:00";
	}

	/**
	 * 获取当前 前一个月
	 */
	public static String lastMonth(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		String last_month = DateUtil.DateToString(c.getTime(), DateStyle.YYYY_MM);
		c = null;
		return last_month;
	}
	
	/**
	 * 获取单月最后一天
	 */
	public static String endDay(String month){
		Calendar c = Calendar.getInstance();
		c.setTime(DateUtil.StringToDate(month));
		int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return month+"-"+day+" 23:59:59";
	}
	

	/**
	 * 获取本周一
	 */
	public static String getNowWeekMonday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -1); // 解决周日会出现 并到下一周的情况
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String nowWeekMonday = DateUtil.DateToString(cal.getTime(), DateStyle.YYYY_MM_DD_EN);
		return nowWeekMonday+" 00:00:00";
	}
}