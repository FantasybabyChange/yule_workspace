package com.yule.runnable;

import com.yule.constant.TimeConst;
import com.yule.exception.YuleException;
import com.yule.redis.util.JedisUtil;


public class BrowseRecordRunnable implements Runnable {

	// 企业ID
	private String companyId;
	// 用户ID
	private String userId;
	
	public BrowseRecordRunnable(String companyId,String userId) {
		super();
		this.companyId = companyId;
		this.userId = userId;
	}

	public void run() {
		try {
			JedisUtil.getInstance().sadd(companyId, userId);
			JedisUtil.getInstance().pexpire(companyId, TimeConst.ONE_HOUR);
			JedisUtil.getInstance().sadd(userId, companyId);
			JedisUtil.getInstance().pexpire(userId, TimeConst.ONE_HOUR);
		} catch (Exception e) {
			new YuleException(e);
		}
	}
}
