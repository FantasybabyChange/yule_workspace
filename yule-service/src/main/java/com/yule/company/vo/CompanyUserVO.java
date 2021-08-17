package com.yule.company.vo;


import java.io.Serializable;
/**
 * 企业子用户表
 *
 */
public class CompanyUserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4695292778890044121L;
	
	private String id;
	private String company_id;
	private Integer code;
	private String account;
	private String name;
	private String password;
	private String mail;
	private String phone;
	private Integer status;
	private String login_time;
	private Integer commision;
	private String image_path;
	private Integer info_is_auth;
	private	Integer address_is_auth;
	
	public CompanyUserVO() {
		super();
	}
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getCommision() {
		return commision;
	}
	public void setCommision(Integer commision) {
		this.commision = commision;
	}
	public String getImage_path() {
		return image_path;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhone() {
		return phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public Integer getInfo_is_auth() {
		return info_is_auth;
	}
	public void setInfo_is_auth(Integer info_is_auth) {
		this.info_is_auth = info_is_auth;
	}
	public Integer getAddress_is_auth() {
		return address_is_auth;
	}
	public void setAddress_is_auth(Integer address_is_auth) {
		this.address_is_auth = address_is_auth;
	}

	
}
