package com.yule.constant;



/**
 * 后台权限 
 */
public class PrivilegeConst {

	/**
	 * 新增
	 */
	public final static String ADMIN_PRIVILEGE_CODE_INSERT = "insert";
	/**
	 * 删除
	 */
	public final static String ADMIN_PRIVILEGE_CODE_DELETE = "delete";
	/**
	 * 编辑
	 */
	public final static String ADMIN_PRIVILEGE_CODE_UPDATE = "update";
	/**
	 * 状态操作
	 */
	public final static String ADMIN_PRIVILEGE_CODE_UPDATE_STATUS = "update_status";
	/**
	 * 后台权限  是否左右
	 */
	public final static String ADMIN_PRIVILEGE_CODE_UPDATE_SEAT = "update_seat";
	/**
	 * 后台权限  是否优先显示
	 */
	public final static String ADMIN_PRIVILEGE_CODE_UPDATE_SHOW = "update_show";
	/**
	 * 后台权限  详情
	 */
	public final static String ADMIN_PRIVILEGE_CODE_DETAILS = "details";
//	/**
//	 * 后台权限  批量删除
//	 */
//	public final static String ADMIN_PRIVILEGE_CODE_BATCH_DELETE = "batch_delete";
	/**
	 * 后台权限  批量更新
	 */
	public final static String ADMIN_PRIVILEGE_CODE_BATCH_UPDATE = "batch_update";
	/**
	 * 角色配置
	 */
	public final static String ADMIN_PRIVILEGE_CODE_ROLE_CONFIG = "role_config";
	/**
	 * 权限配置
	 */
	public final static String ADMIN_PRIVILEGE_CODE_PRIVILEGE_CONFIG = "privilege_config";
	/**
	 * 最低消费配置
	 */
	public final static String ADMIN_PRIVILEGE_CODE_EXPENCE_CONFIG = "expense_config";
	/**
	 * 企业优惠配置
	 */
	public final static String ADMIN_PRIVILEGE_CODE_FAVORABLE = "favorable_config";
	/**
	 * 企业联系方式配置
	 */
	public final static String ADMIN_PRIVILEGE_CODE_PHONE = "phone_config";
	/**
	 * 图册
	 */
	public final static String ADMIN_PRIVILEGE_CODE_GALLERY = "gallery_config";
	/**
	 * 企业服务配置
	 */
	public final static String ADMIN_PRIVILEGE_CODE_SERVE = "serve_config";
	/**
	 * 企业交通地理配置
	 */
	public final static String ADMIN_PRIVILEGE_CODE_ADDRESS = "address_config";
	/**
	 * 企业评论配置
	 */
	public final static String ADMIN_PRIVILEGE_CODE_COMMENT = "comment_config";
	/**
	 * 企业评论评分配置
	 */
	public final static String ADMIN_PRIVILEGE_CODE_POINT = "point_config";
	/**
	 * 价格管理
	 */
	public final static String ADMIN_PRIVILEGE_CODE_PRICE = "price_config";
	
	
	/**
	 * 用户收藏
	 */
	public final static String ADMIN_PRIVILEGE_CODE_USER_COLLECTIONS = "collections_config";
	
	/**
	 * 企业子用户
	 */
	public final static String ADMIN_PRIVILEGE_CODE_COMPANY_USER = "user_config";
	
	
	/**
	 * 企业子用户权限
	 */
	public final static String ADMIN_PRIVILEGE_CODE_COMPANY_USERPRIVILEGE_CONFIG = "user_privilege";
	
	/**
		企业消费管理
	 * 
	 */
	public final static String ADMIN_PRIVILEGE_COMPANY_EXPENSE_CONFIG ="companyExpense_config";

	/**
	 * 企业结算惯例
	 */
	public final static String ADMIN_PRIVILEGE_COMPANY_BALANCE_CONFIG ="company_balance_config";
	
	/**
	 * 合作伙伴结算管理
	 */
	public final static String ADMIN_PRIVILEGE_SALESMAN_BALANCE_CONFIG ="salesman_balance_config";
	
	
	/**
	 * 初始化后台数据库
	 */
	public final static String ADMIN_PRIVILEGE_INITADMIN_DB ="initadmin_db";
	/**
	 * 初始化企业后台数据库
	 */
	public final static String ADMIN_PRIVILEGE_INITCOMPANY_DB ="initcompany_db";
	/**
	 * 初始化用户数据库
	 */
	public final static String ADMIN_PRIVILEGE_INITUSER_DB ="inituser_db";
	/**
	 * 初始化地区数据库
	 */
	public final static String ADMIN_PRIVILEGE_INITAREA_DB ="initarea_db";

	/**
	 * 初始化后台REDIS
	 */
	public final static String ADMIN_PRIVILEGE_INITADMIN_REDIS ="initadmin_redis";
	/**
	 * 初始化企业后台REDIS
	 */
	public final static String ADMIN_PRIVILEGE_INIT_COMPANY_CATEGORY_REDIS ="init_company_category_redis";
	public final static String ADMIN_PRIVILEGE_INIT_COMPANY_COMMENT_CATEGORY_REDIS ="init_company_comment_category_redis";
	public final static String ADMIN_PRIVILEGE_INIT_COMPANY_GRAD_REDIS ="init_company_grade_redis";
	public final static String ADMIN_PRIVILEGE_INITCOMPANY_POINT_CATEGOY_REDIS ="init_companyPoint_category_redis";
	/**
	 * 初始化用户REDIS
	 */
	public final static String ADMIN_PRIVILEGE_INITUSER_REDIS ="inituser_redis";
	/**
	 * 初始化地区REDIS
	 */
	public final static String ADMIN_PRIVILEGE_INITAREA_REDIS ="initarea_redis";
	
	public final static String ADMIN_PRIVILEGE_CODE_COMPANY_PAY_MONEY ="company_pay_money";
	
	public final static String ADMIN_PRIVILEGE_CODE_SALESMAN_PAY_MONEY ="salesman_pay_money";

}
