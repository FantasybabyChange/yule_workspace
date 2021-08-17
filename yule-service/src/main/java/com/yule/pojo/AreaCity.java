package com.yule.pojo;

import java.io.Serializable;

/**
 * 城市
 */
public class AreaCity implements Serializable {

	private static final long serialVersionUID = 8278866699639036955L;

	private String id;
	// 所属省
	private String area_province_id;
	// 市名称
	private String name;
	//拼音
	private String pinyin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea_province_id() {
		return area_province_id;
	}

	public void setArea_province_id(String area_province_id) {
		this.area_province_id = area_province_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public AreaCity() {
		super();
	}

}
