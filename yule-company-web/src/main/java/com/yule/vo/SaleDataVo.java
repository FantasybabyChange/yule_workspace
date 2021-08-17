package com.yule.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SaleDataVo implements Serializable {

	/**
	 * 销售统计所需数据
	 */
	private static final long serialVersionUID = -1222896519750416706L;
	//各个产品销售量
	private Map<String, int[]> productCount;
	//各个产品消费统计
	private Map<String, double[]> productExpenseCount;
	//订单总数
	private Integer ordersCount;
	//订单总销售金额
	private Double ordersExpenseCount;
	
	private List<String> days;
	

	public SaleDataVo() {
		super();
	}

	public Map<String, int[]> getProductCount() {
		return productCount;
	}

	public void setProductCount(Map<String, int[]> productCount) {
		this.productCount = productCount;
	}

	public Map<String, double[]> getProductExpenseCount() {
		return productExpenseCount;
	}

	public void setProductExpenseCount(Map<String, double[]> productExpenseCount) {
		this.productExpenseCount = productExpenseCount;
	}

	public Integer getOrdersCount() {
		return ordersCount;
	}

	public void setOrdersCount(Integer ordersCount) {
		this.ordersCount = ordersCount;
	}

	public Double getOrdersExpenseCount() {
		return ordersExpenseCount;
	}

	public void setOrdersExpenseCount(Double ordersExpenseCount) {
		this.ordersExpenseCount = ordersExpenseCount;
	}

	public List<String> getDays() {
		return days;
	}

	public void setDays(List<String> days) {
		this.days = days;
	}
	
	
}
