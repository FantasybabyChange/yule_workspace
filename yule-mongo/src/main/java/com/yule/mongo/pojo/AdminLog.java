package com.yule.mongo.pojo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

/**
 * 后台系统日志
 */
@Document(collection=CollectionConst.ADMIN_LOG)
public class AdminLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3885215332720752480L;
	@Id
	private ObjectId id;
	@Indexed
	private String name;
	@Indexed
	private String admin_user_id;
	@Indexed
	private String admin_user_name;
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

	public String getAdmin_user_id() {
		return admin_user_id;
	}

	public void setAdmin_user_id(String admin_user_id) {
		this.admin_user_id = admin_user_id;
	}
	
	public String getAdmin_user_name() {
		return admin_user_name;
	}

	public void setAdmin_user_name(String admin_user_name) {
		this.admin_user_name = admin_user_name;
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

	public AdminLog() {
		super();
	} 
	
}

