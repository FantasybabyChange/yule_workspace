package com.yule.enumerate;

/**
 * 企业流程
 */
public enum CompanyFlowEnum {
	/**
	 * 企业基本信息
	 */
	COMPANY(0),
	/**
	 * 企业地址
	 */
	COMPANY_ADDRESS(1),
	/**
	 * 企业服务
	 */
	COMPANY_SERVE(2),
	/**
	 * 企业图册
	 */
	COMPANY_GALLERY(3),
	/**
	 * 企业联系方式
	 */
	COMPANY_PHONE(4),
	/**
	 * 产品基本信息
	 */
	PRODUCT(5),
	/**
	 * 产品图册
	 */
	PRODUCT_GALLERY(6),
	/**
	 * 企业优惠
	 */
	COMPANY_FAVORABLE(7);
	
	private int value;

	CompanyFlowEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
