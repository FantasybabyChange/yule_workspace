package com.yule.enumerate;

public enum DecimalEnum {
	
	
	/**
	 * 千位分隔
	 */
	THOUSAND("#,###"),
	/**
	 * 小数
	 */
	FLOAT("0.0");

	private String value;

	DecimalEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
