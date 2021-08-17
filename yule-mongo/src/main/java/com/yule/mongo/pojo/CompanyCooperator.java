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
@Document(collection=CollectionConst.COMPANY_COOPERATOR)
public class CompanyCooperator implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3408109093602355362L;
	@Id
	private ObjectId id;
	// 商家名称
	@Indexed
	private String name;
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
	
	public CompanyCooperator() {
		super();
	}
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
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
