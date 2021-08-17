package com.yule.admin.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class SalesmanVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9112754515993724281L;
	public SalesmanVO() {
		super();
	}
	private String id;
	private String account;
	private Integer is_delete;
	private Integer status;
	private String name;
	private String image_path;
	private String identity_card_image_path;
	private Timestamp login_time;
	private Timestamp create_time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getIdentity_card_image_path() {
		return identity_card_image_path;
	}
	public void setIdentity_card_image_path(String identity_card_image_path) {
		this.identity_card_image_path = identity_card_image_path;
	}
	public Timestamp getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Timestamp login_time) {
		this.login_time = login_time;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	
	
}
