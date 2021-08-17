package com.yule.admin.param;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class InsertProductPriceParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6789948244127082044L;
	private List<String> id;
	//区间段
	private List<String> time;
	//星期一到星期日
	private List<BigDecimal> monday;
	private List<BigDecimal> tuesday;
	private List<BigDecimal> wednesday;
	private List<BigDecimal> thursday;
	private List<BigDecimal> friday;
	private List<BigDecimal> saturday;
	private List<BigDecimal> sunday;
	//创建和修改时间
	private List<String> create_time;
	private List<String> update_time;
	private String product_id;
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public List<String> getId() {
		return id;
	}
	public void setId(List<String> id) {
		this.id = id;
	}
	public List<String> getTime() {
		return time;
	}
	public void setTime(List<String> time) {
		this.time = time;
	}
	public List<BigDecimal> getMonday() {
		return monday;
	}
	public void setMonday(List<BigDecimal> monday) {
		this.monday = monday;
	}
	public List<BigDecimal> getTuesday() {
		return tuesday;
	}
	public void setTuesday(List<BigDecimal> tuesday) {
		this.tuesday = tuesday;
	}
	public List<BigDecimal> getWednesday() {
		return wednesday;
	}
	public void setWednesday(List<BigDecimal> wednesday) {
		this.wednesday = wednesday;
	}
	public List<BigDecimal> getThursday() {
		return thursday;
	}
	public void setThursday(List<BigDecimal> thursday) {
		this.thursday = thursday;
	}
	public List<BigDecimal> getFriday() {
		return friday;
	}
	public void setFriday(List<BigDecimal> friday) {
		this.friday = friday;
	}
	public List<BigDecimal> getSaturday() {
		return saturday;
	}
	public void setSaturday(List<BigDecimal> saturday) {
		this.saturday = saturday;
	}
	public List<BigDecimal> getSunday() {
		return sunday;
	}
	public void setSunday(List<BigDecimal> sunday) {
		this.sunday = sunday;
	}
	public List<String> getCreate_time() {
		return create_time;
	}
	public void setCreate_time(List<String> create_time) {
		this.create_time = create_time;
	}
	public List<String> getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(List<String> update_time) {
		this.update_time = update_time;
	}
	

}
