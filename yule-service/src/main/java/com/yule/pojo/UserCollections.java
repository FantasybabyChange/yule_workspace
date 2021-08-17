package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 前台用户收藏
 */
public class UserCollections implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -412003372312352731L;
	
	//ID
	private String id;
	// 用户id
	private String user_id;
	// 公司id
	private String company_id;
	// 创建时间
	private Timestamp create_time;
	// 修改时间
	private Timestamp update_time;
	
	
	public UserCollections() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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
	
}
