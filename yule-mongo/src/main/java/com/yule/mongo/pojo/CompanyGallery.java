package com.yule.mongo.pojo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

//企业图册
@Document(collection=CollectionConst.COMPANY_GALLERY)
public class CompanyGallery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8664140581638190830L;
	@Id
	private ObjectId id;
	// 企业id
	@Indexed
	private String company_id;
	// 附件名称
	private String name;
	// 系统生成文件名
	private String system_name;
	// 附件地址
	private String path;
	// 附件类型(如：png ，jpg 等等)
	private String type;
	// 附件大小
	private String size;
	// 是否删除(0未删除,1删除)
	@Indexed
	private Integer is_delete;
	// 创建时间
	@Indexed
	private Date create_time;
	// 更新时间
	private Date update_time;	


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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
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

	public String getSystem_name() {
		return system_name;
	}
	public void setSystem_name(String system_name) {
		this.system_name = system_name;
	}

	public CompanyGallery() {
		super();
	}

	
}
