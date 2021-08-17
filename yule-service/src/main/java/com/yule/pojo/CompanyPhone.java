package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 企业联系方式
 */
public class CompanyPhone implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8818493696271828665L;
	
	private String id;
	// 企业id
	private String company_id;
	// 电话名称
	private String name;
	// 电话
	private String phone;
	
	private Integer type ;
	// 创建时间
	private Timestamp create_time;
	// 更新时间
	private Timestamp update_time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public CompanyPhone() {
		super();
	}
}
