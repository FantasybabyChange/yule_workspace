package com.yule.admin.query;

import java.io.Serializable;

/**	
 * 系统通知
 */
public class AdminNoticeQuery implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 224097409684369981L;
	
	//标题
	private String title;
	//内容
	private Integer is_delete;
	//创建时间
	private String start_time;
	//更新时间
	private String end_time;
	
	private Integer type;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public AdminNoticeQuery() {
		super();
	}
}
