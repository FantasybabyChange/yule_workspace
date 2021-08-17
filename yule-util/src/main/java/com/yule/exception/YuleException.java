package com.yule.exception;

import org.apache.log4j.Logger;


public class YuleException extends Exception{
	
	private static final Logger log = Logger.getLogger(YuleException.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1117121627239476131L;
	
	public YuleException() {  
        super();  
    }  
      
    public YuleException(Throwable t) {  
        super(t);  
        log.error(t.getMessage(),t);
    }  
      
    public YuleException(String error) {
        super(error);  
        log.info(error);
    }  
      
    public YuleException(String error, Throwable t) {
        super(error, t);
        log.error(error,t);
    }  
	
}
