package com.yule.admin.param;

import java.io.Serializable;
import java.util.List;

public class InsertUserLevelParam implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6936691120703608100L;
	
	private List<String> id;
	
	private List<String> name;
	
	private List<Integer> expense;

	private List<Integer> score_ratio;

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

	public List<Integer> getExpense() {
		return expense;
	}

	public void setExpense(List<Integer> expense) {
		this.expense = expense;
	}


	public List<Integer> getScore_ratio() {
		return score_ratio;
	}

	public void setScore_ratio(List<Integer> score_ratio) {
		this.score_ratio = score_ratio;
	}

	public InsertUserLevelParam() {
		super();
	}

}
