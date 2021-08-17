package com.yule.admin.param;

import java.io.Serializable;
import java.util.List;

public class InsertAdminNoticeParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 169499632819730036L;

	private String id;
	//标题
	private String title;
	//内容
	private String content;
	//创建时间
	private Integer type;
	// 附件名称
	private  List<String> name;
	// 系统生成文件名
	private List<String> system_name;
	// 附件地址
	private List<String> path;
	// 附件类型(如：png ，jpg 等等)
	private List<String> attachment_type;
	// 附件大小
	private List<String> size;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public List<String> getName() {
		return name;
	}
	public void setName(List<String> name) {
		this.name = name;
	}
	public List<String> getSystem_name() {
		return system_name;
	}
	public void setSystem_name(List<String> system_name) {
		this.system_name = system_name;
	}
	public List<String> getPath() {
		return path;
	}
	public void setPath(List<String> path) {
		this.path = path;
	}
	public List<String> getAttachment_type() {
		return attachment_type;
	}
	public void setAttachment_type(List<String> attachment_type) {
		this.attachment_type = attachment_type;
	}
	public List<String> getSize() {
		return size;
	}
	public void setSize(List<String> size) {
		this.size = size;
	}
	
	
}
