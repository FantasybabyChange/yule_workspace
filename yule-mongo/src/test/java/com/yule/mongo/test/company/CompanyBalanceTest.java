/*package com.yule.mongo.test.company;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;

import com.yule.constant.CustomBeanConst;
import com.yule.exception.YuleException;
import com.yule.mongo.company.query.BalanceQuery;
import com.yule.mongo.company.service.ICompanyBalanceMongo;
import com.yule.mongo.company.vo.CompanyBalanceVO;
import com.yule.util.CustomBeanFactory;

public class CompanyBalanceTest extends TestCase{

	private ICompanyBalanceMongo companyBalanceMongoImpl = null;
	
	public CompanyBalanceTest(String name) {
		super(name);
		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.COMPANY_MONGO_PATHS);
		companyBalanceMongoImpl = (ICompanyBalanceMongo) context.getBean("companyBalanceMongoImpl");
	}
	public static Test suite() {
		TestSuite test = new TestSuite("OrdersMongoTest接口测试");
		test.addTest(new CompanyBalanceTest("findLastMonthBalance"));
		//test.addTest(new CompanyBalanceTest("findLastHistoryBalance"));
		return test;
	}
	
	public void findLastMonthBalance() throws YuleException{
		BalanceQuery balanceQuery = new BalanceQuery();
		balanceQuery.setCompany_id("ed2ef991-e91f-4468-8cfe-40a81de3eead");
		balanceQuery.setStart_time("2014-10-20 00:00:00");
		balanceQuery.setEnd_time("2014-10-26 23:59:59");
		try {
			
			CompanyBalanceVO balance =companyBalanceMongoImpl.findCompanyBalance(balanceQuery);
			System.err.println(balance);
		} catch (YuleException e) {
			throw e;
		}
	}
}
*/