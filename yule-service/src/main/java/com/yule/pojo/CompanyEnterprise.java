package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class CompanyEnterprise implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6307426088215051567L;

	private String  id;
	
	private String  name;
	
	private String  pinyin;                 
	//公司许可证好
	private String  business_license;      
	//法人姓名
	private String  legal_person_name;  
	
	private Integer is_delete;  
	
	private Timestamp create_time;
	
	private Timestamp update_time;

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

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getBusiness_license() {
		return business_license;
	}

	public void setBusiness_license(String business_license) {
		this.business_license = business_license;
	}


	public String getLegal_person_name() {
		return legal_person_name;
	}

	public void setLegal_person_name(String legal_person_name) {
		this.legal_person_name = legal_person_name;
	}

	public Integer getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
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

	public CompanyEnterprise() {
		super();
	}
	
}
