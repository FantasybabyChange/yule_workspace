package com.yule.util;

import com.yule.exception.YuleException;

public class ConvertUtil {
	
	/**
	 * 字符串转换整型
	 */
	public static int stringToInteger(String param) throws YuleException{
		return Integer.parseInt(param);
	}
	
	/**
	 * 整型转换字符
	 */
	public static String integerToString(Integer param) throws YuleException{
		return String.valueOf(param);
	}
	
	/**
	 * LONG转换整型
	 */
	public static int longToInteger(long param) throws YuleException{
		return Integer.parseInt(String.valueOf(param));
	}
}
