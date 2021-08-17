package com.yule.mongo.param;

import java.io.Serializable;
import java.util.List;


public class InsertProductGalleryParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7190856115007735952L;
	
	private List<String> name;
	
	private List<String> system_name;
	
	private List<String> path;
	
	private List<String> type;
	
	private List<String> size;
	
	private String product_id;
	
	private String company_id;
	
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

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public List<String> getSize() {
		return size;
	}

	public void setSize(List<String> size) {
		this.size = size;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	
	
}
