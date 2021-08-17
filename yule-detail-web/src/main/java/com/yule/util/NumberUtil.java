package com.yule.util;

import java.text.NumberFormat;

import com.yule.constant.NumberConst;

public class NumberUtil {
	/**
	 * 满分 五分
	 */
	public final static double SUM_SCORE = 5;
	public static String reverDoubleToKM(double num){
		double num1 = Math.floor(num /1000);
		if ( num1 > 0) {
			return Math.round(num1)+NumberConst.UNIT_KILOMETER;
		}else{
			return Math.round(num) + NumberConst.UNIT_METER;
		}
	}
	public static String formatDouble(double num,int length){
		NumberFormat instance = NumberFormat.getInstance();
		instance.setMaximumFractionDigits(length); 
		return instance.format(num);
	}
	public static String getPrecentStr(double num,double sum){
		return Double.parseDouble(formatDouble(num/sum, 2))*100+"%";
	}
}
