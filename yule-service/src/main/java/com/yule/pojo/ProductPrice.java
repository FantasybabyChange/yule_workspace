package com.yule.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
/**
 * 价格管理pojo
 */
public class ProductPrice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	//区间段
	private String time;
	//星期一到星期日
	private BigDecimal monday;
	private BigDecimal tuesday;
	private BigDecimal wednesday;
	private BigDecimal thursday;
	private BigDecimal friday;
	private BigDecimal saturday;
	private BigDecimal sunday;
	//创建和修改时间
	private Timestamp create_time;
	private Timestamp update_time;
	/**
	 * 产品的外键
	 */
	private String product_id;
	public ProductPrice() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public BigDecimal getMonday() {
		return monday;
	}
	public void setMonday(BigDecimal monday) {
		this.monday = monday;
	}
	public BigDecimal getTuesday() {
		return tuesday;
	}
	public void setTuesday(BigDecimal tuesday) {
		this.tuesday = tuesday;
	}
	public BigDecimal getWednesday() {
		return wednesday;
	}
	public void setWednesday(BigDecimal wednesday) {
		this.wednesday = wednesday;
	}
	public BigDecimal getThursday() {
		return thursday;
	}
	public void setThursday(BigDecimal thursday) {
		this.thursday = thursday;
	}
	public BigDecimal getFriday() {
		return friday;
	}
	public void setFriday(BigDecimal friday) {
		this.friday = friday;
	}
	public BigDecimal getSaturday() {
		return saturday;
	}
	public void setSaturday(BigDecimal saturday) {
		this.saturday = saturday;
	}
	public BigDecimal getSunday() {
		return sunday;
	}
	public void setSunday(BigDecimal sunday) {
		this.sunday = sunday;
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
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	
	

}
