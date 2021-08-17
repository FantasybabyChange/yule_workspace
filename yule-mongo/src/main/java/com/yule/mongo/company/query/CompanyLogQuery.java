package com.yule.mongo.company.query;

import java.io.Serializable;
import java.util.List;

public class CompanyLogQuery implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3470913798845184706L;
	
	private String name;
	private String company_id;
	private List<Integer> type;
	private String start_time;
	private String end_time;
	
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
	public List<Integer> getType() {
		return type;
	}
	public void setType(List<Integer> type) {
		this.type = type;
	}
	
}
