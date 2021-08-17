package com.yule.admin.param;

import java.io.Serializable;
import java.util.List;

public class InsertUserLoginParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6868604143722942370L;

	private List<String> id;

	private List<String> name;

	private List<String> password;

	private List<String> phone;

	private List<String> mail;

	public InsertUserLoginParam() {
		super();
	}

	public List<String> getId() {
		return id;
	}

	public void setId(List<String> id) {
		this.id = id;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<String> getPassword() {
		return password;
	}

	public void setPassword(List<String> password) {
		this.password = password;
	}

	public List<String> getPhone() {
		return phone;
	}

	public void setPhone(List<String> phone) {
		this.phone = phone;
	}

	public List<String> getMail() {
		return mail;
	}

	public void setMail(List<String> mail) {
		this.mail = mail;
	}


	
}
