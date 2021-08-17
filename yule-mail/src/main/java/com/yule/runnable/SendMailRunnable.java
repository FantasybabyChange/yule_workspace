package com.yule.runnable;

import com.yule.exception.YuleException;
import com.yule.util.MailUtil;


public class SendMailRunnable implements Runnable {

	// 日志名
	private MailUtil mailUtil;
	// 操作人ID
	private String toMail;
	
	private String[] files;
	
	public SendMailRunnable(MailUtil mailUtil,String toMail,String... files) {
		super();
		this.mailUtil = mailUtil;
		this.toMail = toMail;
		this.files = files;
	}
	
	public void run() {
		try {
			mailUtil.send(toMail,files);
		} catch (Exception e) {
			new YuleException("发生错误!",e);
		}
	}
}
