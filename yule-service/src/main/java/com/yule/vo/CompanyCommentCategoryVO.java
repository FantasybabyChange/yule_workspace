package com.yule.vo;

import java.io.Serializable;

/**
 * 企业评论类型VO
 */
public class CompanyCommentCategoryVO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6490430258277188314L;
	private String id;
	// 分类名称
	private String name;
	// 排序
	private Integer order;
	
	public CompanyCommentCategoryVO() {
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
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
}
