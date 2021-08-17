package com.yule.vo;

import java.io.Serializable;

public class FileUploadVO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3688906500253226991L;
	
	private String name; //文件原始名称
	private String system_name; //系统生成文件名称
	private String type; //文件类型
	private Integer size; //文件大小
	private String path; //文件路径
	private Integer status; //文件上传状态(0成功1失败)
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSystem_name() {
		return system_name;
	}

	public void setSystem_name(String system_name) {
		this.system_name = system_name;
	}
	
}
