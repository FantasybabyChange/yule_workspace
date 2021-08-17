package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户兴趣偏好
 */
public class UserInterest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7606240196139393253L;
	// 用户ID
	private String id;
	// 用户等级
	private String company_grade_id;
	// 市ID
	private String company_category_id;
	// 创建时间
	private Timestamp create_time;
	// 修改时间
	private Timestamp update_time;
	
	public UserInterest() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCompany_grade_id() {
		return company_grade_id;
	}

	public void setCompany_grade_id(String company_grade_id) {
		this.company_grade_id = company_grade_id;
	}

	public String getCompany_category_id() {
		return company_category_id;
	}

	public void setCompany_category_id(String company_category_id) {
		this.company_category_id = company_category_id;
	}
	
}