package com.yule.mongo.vo;

import java.io.Serializable;

/**
 * 记录聚合总数
 */
public class CountVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -298347639621385313L;
	
	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
