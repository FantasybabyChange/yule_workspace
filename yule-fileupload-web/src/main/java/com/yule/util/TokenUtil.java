package com.yule.util;
import java.util.UUID;


public class TokenUtil {
	
	private static TokenUtil instance = new TokenUtil();
	
	private String uuid = "";
	 
    private TokenUtil() {}
 
    public static TokenUtil getInstance() {
        return instance;
    }
 
    public synchronized boolean isTokenValid(String uuidPar) {
        
        return getUuid().equals(uuidPar);
    }
     
    /*
     * 重新设置token，当页面被请求后，将session中的token属性去除
     */
    public synchronized void resetToken()
    {
    	setUuid(getUUID());
    }
    
    /*
     * 为请求新建一个token标记，此标记由一个随机的double数toString形成，并把字符值存入session中
     */
    public synchronized String getToken()
    {
    	uuid = getUUID();
    	setUuid(uuid);
    	return uuid;
    }
    
    public String getUUID(){
    	return UUID.randomUUID().toString().toUpperCase();
    }

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
    
}
