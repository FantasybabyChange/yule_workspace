package com.yule.vo;

import java.io.Serializable;

public class AdminNoticeAttachmentVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3416844556436390242L;

	private String attachment_id;
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
	private String create_time;
	// 更新时间
	private String update_time;	


	public String getAttachment_id() {
		return attachment_id;
	}

	public void setAttachment_id(String attachment_id) {
		this.attachment_id = attachment_id;
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

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
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

	public AdminNoticeAttachmentVO() {
		super();
	}

	@Override
	public String toString() {
		return "AdminNoticeAttachmentVO [attachment_id=" + attachment_id
				+ ", admin_notice_id=" + admin_notice_id + ", name=" + name
				+ ", system_name=" + system_name + ", path=" + path + ", type="
				+ type + ", size=" + size + ", is_delete=" + is_delete
				+ ", create_time=" + create_time + ", update_time="
				+ update_time + "]";
	}

	
}
