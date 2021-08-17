package com.yule.runnable;

import com.yule.exception.YuleException;
import com.yule.util.SmsUtil;


public class SendSmsRunnable implements Runnable {

	// 日志名
	private SmsUtil smsUtil;
	// 操作人ID
	private String toPhone;
	
	private String stime;
	
	public SendSmsRunnable(SmsUtil smsUtil,String toPhone,String stime) {
		super();
		this.smsUtil = smsUtil;
		this.toPhone = toPhone;
		this.stime = stime;
	}
	
	public void run() {
		try {
			smsUtil.send(toPhone,stime);
		} catch (Exception e) {
			new YuleException("发生错误!",e);
		}
	}
}
