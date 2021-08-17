package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 前台用户等级
 */
public class UserLevel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9003971320783823974L;
	//用户ID
	private String id;
	// 等级名称
	private String name;
	// 消费金额
	private Integer expense;
	// 积分增长比率
	private Integer score_ratio;
	// 创建时间
	private Timestamp create_time;
	// 修改时间
	private Timestamp update_time;
	
	public UserLevel() {
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

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

}
