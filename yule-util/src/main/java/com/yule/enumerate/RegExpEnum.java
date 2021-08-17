package com.yule.enumerate;


/**
 * 正则格式
 */
public enum RegExpEnum {

	/**
	 * 手机正则
	 */
	PHONE("/^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$|17[0-9]{9}$/"),
	/**
	 * 邮箱
	 */
	MAIL("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"),
	/**
	 * HTTP网址
	 */
	HTTP("http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*");
	
	private String value;

	RegExpEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
