package com.yule.mail.user;

import java.io.Serializable;

import com.yule.util.MailUtil;

public class UserInsertOrdersMail extends MailUtil implements Serializable {
	private static final long serialVersionUID = -63534464355243773L;

	
	public UserInsertOrdersMail() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	private String name;
	
	private String num;
	
	private String estimate_arrive_time;
	
	private String product_name;
	
	private String company_face;
	
	private String company_name;
	
	private String company_id;
	
	private String detal_address;
	
	private String create_time;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getEstimate_arrive_time() {
		return estimate_arrive_time;
	}

	public void setEstimate_arrive_time(String estimate_arrive_time) {
		this.estimate_arrive_time = estimate_arrive_time;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getCompany_face() {
		return company_face;
	}

	public void setCompany_face(String company_face) {
		this.company_face = company_face;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getDetal_address() {
		return detal_address;
	}

	public void setDetal_address(String detal_address) {
		this.detal_address = detal_address;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	
	
}
