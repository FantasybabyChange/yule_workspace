package com.yule.runnable;

import com.yule.constant.CustomBeanConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.mongo.company.service.ICompanyLogMongo;
import com.yule.mongo.pojo.CompanyLog;
import com.yule.util.CustomBeanFactory;


public class CompanyLogRunnable implements Runnable {

	private static final ICompanyLogMongo companyLogMongoImpl = (ICompanyLogMongo)CustomBeanFactory.getContext(CustomBeanConst.COMPANY_MONGO_PATHS).getBean("companyLogMongoImpl");
	
	// 日志名
	private String name;
	// 操作人ID
	private String company_user_id;
	// 操作人名称
	private String company_name;
	// 操作状态
	private int status;
	//企业代码
	private String company_id;
	
	public CompanyLogRunnable(String name,String company_user_id,String company_name,String company_id,LogEnum logEnum) {
		super();
		this.name = name;
		this.company_user_id = company_user_id;
		this.company_name = company_name;
		this.company_id = company_id;
		this.status = logEnum.getValue();
	}
	
	public void run() {
		try {
			CompanyLog companyLog  = new CompanyLog(); 
			companyLog.setCompany_id(company_id);
			companyLog.setCompany_name(company_name);
			companyLog.setName(name);
			companyLog.setStatus(status);
			companyLog.setCompany_user_id(company_user_id);
			companyLogMongoImpl.insertCompanyLog(companyLog);
		} catch (Exception e) {
			new YuleException("发生错误!",e);
			e.printStackTrace();
		}finally{
			System.gc();
		}
	}
}
