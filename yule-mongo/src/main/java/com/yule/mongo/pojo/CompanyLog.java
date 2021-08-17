package com.yule.mongo.pojo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

/**
 * 企业后台系统日志
 */
@Document(collection=CollectionConst.COMPANY_LOG)
public class CompanyLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3178971481448426640L;
	@Id
	private ObjectId id;
	@Indexed
	private String name;
	@Indexed
	private String company_id;
	@Indexed
	private String company_user_id;
	private String company_name;
	@Indexed
	private Integer status;
	@Indexed
	private Date create_time;
	
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

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCompany_user_id() {
		return company_user_id;
	}

	public void setCompany_user_id(String company_user_id) {
		this.company_user_id = company_user_id;
	}

	public CompanyLog() {
		super();
	} 
	
}

