package com.yule.util;

import java.io.UnsupportedEncodingException;

import org.springframework.util.StringUtils;

import com.yule.exception.YuleException;

public class StringUtil {
	
	public static String cut(String str,int length){
		if(!StringUtils.isEmpty(str)){
			if(str.length()>length){
				str = "<span title=\""+str+"\">"+str.substring(0, length)+"......</span>";
			}
		}
		return str;
	}
	
	/** 
	 * 计算采用utf-8编码方式时字符串所占字节数  UTF8汉字占3个字节
	 * @param content 
	 * @return 
	 */  
	public static int getByteSize(String content) throws Exception{  
	    int size = 0;  
	    if (null != content) {  
	        try {  
	            // 汉字采用utf-8编码时占3个字节  
	            size = content.getBytes("utf-8").length;  
	        } catch (UnsupportedEncodingException e) {  
	            new YuleException(e);
	            throw e;
	        }  
	    }  
	    return size;  
	}  
	
}
