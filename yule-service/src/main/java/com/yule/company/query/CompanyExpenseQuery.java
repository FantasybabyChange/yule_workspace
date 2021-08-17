package com.yule.company.query;

import java.io.Serializable;


public class CompanyExpenseQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1308796089976608355L;
	private String company_id;
	private String expense_category_id;
	private String name;

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getExpense_category_id() {
		return expense_category_id;
	}

	public void setExpense_category_id(String expense_category_id) {
		this.expense_category_id = expense_category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CompanyExpenseQuery() {
		super();
	}

}
