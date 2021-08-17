package com.yule.util;

public final class YuLeEncryptUtil {	
	
	private static final String FORMAT_ONE = "*&Y!$E@";
	
	private static final String FORMAT_TWO = "*@D%U$";
	
	private static final String FORMAT_THREE = "*H#U^I*";
	
	public static final String encode(String text) { 
		int index = text.length()/2;
		return FORMAT_ONE+text.substring(0,index)+FORMAT_TWO+text.substring(index)+FORMAT_THREE; 
	}
	
	public static final String decode(String text) { 
		return text.replace(FORMAT_ONE, "").replace(FORMAT_TWO, "").replace(FORMAT_THREE, "");
	}
	
	public static void main(String[] args) {
		String text = encode("tTddttDDD");
		System.out.println(text);
		System.out.println(decode(text));
	}
}
