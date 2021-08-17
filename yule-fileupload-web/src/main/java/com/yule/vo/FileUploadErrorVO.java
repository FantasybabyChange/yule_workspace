package com.yule.vo;

import java.io.Serializable;

public class FileUploadErrorVO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1482461905092967539L;
	
	
	//错误内容
	private Integer code;
	
	private String message;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
