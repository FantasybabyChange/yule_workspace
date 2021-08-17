package com.yule.admin.param;

import java.io.Serializable;
import java.util.List;

public class InsertCompanyPointCategoryParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3337804899633595687L;
	
	private List<String> id;
	
	private List<String> name;
	
	private List<Integer> point;

	public List<String> getId() {
		return id;
	}
	
	public void setId(List<String> id) {
		this.id = id;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}
	
	public List<Integer> getPoint() {
		return point;
	}

	public void setPoint(List<Integer> point) {
		this.point = point;
	}

	public InsertCompanyPointCategoryParam() {
		super();
	}
	
}
