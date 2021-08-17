package com.yule.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yule.enumerate.RegExpEnum;

public class RegExpUtil {
	
    /** 
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    public static boolean match(String str,RegExpEnum regExpEnum){
        Pattern pattern = Pattern.compile(regExpEnum.getValue());
        Matcher  matcher = pattern.matcher( str );
        return matcher.matches();
    }
}
