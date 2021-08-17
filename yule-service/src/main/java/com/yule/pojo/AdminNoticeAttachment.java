package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

//产品附件
public class AdminNoticeAttachment implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8189330094444399661L;
	
	private String id;
	//通知id
	private String admin_notice_id;
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
	private Integer is_delete;
	// 创建时间
	private Timestamp create_time;
	// 更新时间
	private Timestamp update_time;	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public String getSystem_name() {
		return system_name;
	}
	public void setSystem_name(String system_name) {
		this.system_name = system_name;
	}


	public String getAdmin_notice_id() {
		return admin_notice_id;
	}

	public void setAdmin_notice_id(String admin_notice_id) {
		this.admin_notice_id = admin_notice_id;
	}

	public AdminNoticeAttachment() {
		super();
	}
	
}
