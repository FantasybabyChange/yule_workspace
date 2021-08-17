package com.yule.vo;

import java.io.Serializable;

/**
 * 企业服务VO
 */
public class CompanyServeVO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4332222517363000274L;
	
	private String id;
	// 公司名称
	private String name;
	// 拼音名称
	private Integer is_check;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getIs_check() {
		return is_check;
	}
	public void setIs_check(Integer is_check) {
		this.is_check = is_check;
	}
	public CompanyServeVO() {
		super();
	}
}
