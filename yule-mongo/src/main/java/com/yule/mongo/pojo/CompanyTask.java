package com.yule.mongo.pojo;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

/**
 * 企业任务
 */
@Document(collection=CollectionConst.COMPANY_TASK)
public class CompanyTask implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3885215332720752480L;
	@Id
	private ObjectId id;
	// 企业ID
	@Indexed
	private String company_id;
	// 任务名称
	private String name;
	// 任务路径
	private String url;
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public CompanyTask() {
		super();
	} 
	
}

