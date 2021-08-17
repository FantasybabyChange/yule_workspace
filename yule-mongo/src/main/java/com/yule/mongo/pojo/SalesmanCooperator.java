package com.yule.mongo.pojo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

/**
 * 企业合作
 */
@Document(collection=CollectionConst.SALESMAN_COOPERATOR)
public class SalesmanCooperator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7309986461917920413L;
	@Id
	private ObjectId id;
	// 销售人员账号
	@Indexed
	private String account;
	// 手机号
	private String phone;
	// 邮箱
	private String mail;
	// 地址
	private String address;
	// 备注
	private String details;
	// 合作状态
	@Indexed
	private Integer status=1;
	// 创建时间
	@Indexed
	private Date create_time;
	// 更新时间
	private Date update_time ;
	
	public SalesmanCooperator() {
		super();
	}
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
