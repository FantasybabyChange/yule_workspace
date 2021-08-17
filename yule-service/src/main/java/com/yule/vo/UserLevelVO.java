package com.yule.vo;

import java.io.Serializable;

/**
 * 前台用户等级
 */
public class UserLevelVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8872929549021180010L;

	//用户ID
	private String id;
	// 等级名称
	private String name;
	// 消费金额
	private Integer expense;
	// 积分增长比率
	private Integer score_ratio;
	
	public UserLevelVO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getExpense() {
		return expense;
	}

	public void setExpense(Integer expense) {
		this.expense = expense;
	}
	
	public Integer getScore_ratio() {
		return score_ratio;
	}

	public void setScore_ratio(Integer score_ratio) {
		this.score_ratio = score_ratio;
	}

}
