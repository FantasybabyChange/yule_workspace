package com.yule.admin.param;

import java.io.Serializable;
import java.util.List;

public class InsertProductServeParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7677251109679500608L;

	private List<String> id;

	private List<String> name;
	
	private List<Integer> order;

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

	public List<Integer> getOrder() {
		return order;
	}

	public void setOrder(List<Integer> order) {
		this.order = order;
	}

	public InsertProductServeParam() {
		super();
	}
	
}
