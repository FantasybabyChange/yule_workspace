package com.yule.util;

import com.yule.constant.DoMainConst;


public class WordUtil {
	
	public static String doFilter(String str) throws Exception{
		return HttpRequestUtil.doPost(DoMainConst.WORD_URL+"/word/doFilter.do", "word="+str);
	}
	
}
