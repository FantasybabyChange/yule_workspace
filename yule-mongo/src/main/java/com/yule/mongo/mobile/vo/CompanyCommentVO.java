package com.yule.mongo.mobile.vo;

import java.io.Serializable;

public class CompanyCommentVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 786784654450885917L;
	private int count;
	private float point;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public float getPoint() {
		return point;
	}
	public void setPoint(float point) {
		this.point = point;
	}
	
}
