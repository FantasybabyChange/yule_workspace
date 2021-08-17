package com.yule.exception.constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class YuleExceptionConst {

    public static final String CODE_9999 = "9999";  
    public static final String MSG_9999 = "其它错误!";  
  
    private static Map<String, String> returnCodeMap = new ConcurrentHashMap<String, String>();  
  
    public static Map<String, String> getReturnCODEMap() {  
        if (returnCodeMap.isEmpty()) {  
        	returnCodeMap.put(CODE_9999, MSG_9999);  
        }
        return returnCodeMap;  
    }  
	
}
