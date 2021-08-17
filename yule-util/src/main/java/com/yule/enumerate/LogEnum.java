package com.yule.enumerate;

/**
 * 日志类型
 */
public enum LogEnum {

	/**
	 * 增加
	 */
	INSERT(0),
	/**
	 * 删除
	 */
	DELETE(1),
	/**
	 * 修改
	 */
	UPDATE(2),
	/**
	 * 查看
	 */
	QUERY(3),
	/**
	 * 批量执行数据
	 */
	BATCH(4),
	/**
	 * 配置
	 */
	CONFIG(5),
	/**
	 * 登录
	 */
	LOGIN(6),
	/**
	 * 退出
	 */
	LOGOUT(7),
	/**
	 * 其他
	 */
	OTHER(8),
	/**
	 * 验证
	 */
	VERIFY(9);
	
	
	private int value;

	LogEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
