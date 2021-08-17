package com.yule.mongo.admin.query;

import java.io.Serializable;
import java.util.List;

public class AdminLogQuery implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3470913798845184706L;
	
	private String name;
	private List<Integer> status;
	private String admin_user_id;
	private String start_time;
	private String end_time;
	
	
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
	public List<Integer> getStatus() {
		return status;
	}
	public void setStatus(List<Integer> status) {
		this.status = status;
	}
	
}
