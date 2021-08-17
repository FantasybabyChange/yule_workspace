package com.yule.util;

import java.util.Date;

import org.springframework.util.StringUtils;

public class OrdersDateUtil {

	public static  Long compareTime(String estimate_arrive_time){
		Long time = null;
		try {
			if (!StringUtils.isEmpty(estimate_arrive_time)) {
				Date now_time = DateUtil.getCurrentDate();
				Date  arrive_time = DateUtil.StringToDate(estimate_arrive_time);	
				time = arrive_time.getTime() -now_time.getTime();
				int hour = DateUtil.getHour(arrive_time);
				if (hour>=5&&hour<19) {
					time = -1L;
				}
				now_time = null;
				arrive_time = null;
			}
		} catch (Exception e) {
		}
		return time;
	}
}
