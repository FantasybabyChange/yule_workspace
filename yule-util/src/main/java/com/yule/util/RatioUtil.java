package com.yule.util;

import java.text.DecimalFormat;

public class RatioUtil {
	/**
	 * 获取百分比
	 * @param x 小数
	 * @param y 大数
	 * @return
	 */
	public static String getRatio(int x,int y){
		DecimalFormat df = new DecimalFormat("##%");
		return df.format(x*1.0/y*1.0);
	}
	
	/**
	 * 获取百分比
	 * @param x 小数
	 * @param y 大数
	 * @return
	 */
	public static String getRatio(double x,int y){
		DecimalFormat df = new DecimalFormat("##%");
		return df.format(x*1.0/y*1.0);
	}
	
	/**
	 * 获取上线(100%)百分比
	 * @param x 小数
	 * @param y 大数
	 * @return
	 */
	public static String getUpperRatio(double x,int y){
		DecimalFormat df = new DecimalFormat("##%");
		double num = x*1.0/y*1.0;
		if(num>1){
			num = 1.0;
		}
		return df.format(num);
	}
	
}
