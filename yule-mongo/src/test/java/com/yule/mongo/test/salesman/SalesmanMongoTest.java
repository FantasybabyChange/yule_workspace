/*package com.yule.mongo.test.salesman;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;

import com.yule.constant.CustomBeanConst;
import com.yule.mongo.pojo.CompanyBalance;
import com.yule.mongo.salesman.query.SalesmanBalanceQuery;
import com.yule.mongo.salesman.service.ISalesmanBalanceMongo;
import com.yule.mongo.salesman.vo.SalesmanBalanceVO;
import com.yule.util.CustomBeanFactory;
import com.yule.vo.Page;

public class SalesmanMongoTest extends TestCase {

	private ISalesmanBalanceMongo salesmanBalanceMongoImpl;

	public SalesmanMongoTest(String name) {
		super(name);
		ApplicationContext context = CustomBeanFactory
				.getContext(CustomBeanConst.SALESMAN_MONGO_PATHS);
		salesmanBalanceMongoImpl = (ISalesmanBalanceMongo) context
				.getBean("salesmanBalanceMongoImpl");
	}
	
	public static Test suite() {
		TestSuite test = new TestSuite("OrdersManagerTest接口测试");
		test.addTest(new SalesmanMongoTest("findSonSalesmanBalanceVOByMonth"));
		////test.addTest(new OrdersManagerTest("updateOrders"));
		//test.addTest(new OrdersManagerTest("insertOrders"));
		return test;
	}
	public void findSalesmanBalanceVOByMonth(){
		SalesmanBalanceQuery salesmanBalanceQuery=new SalesmanBalanceQuery();
		salesmanBalanceQuery.setMonth("2014-08");
		salesmanBalanceQuery.setSalesman_id("425a6893-a775-4f0c-89c7-2a5de137d9fa");
		try {
			SalesmanBalanceVO salesmanBalanceVOs = salesmanBalanceMongoImpl.findSonSalesmanBalanceVOByMonth(salesmanBalanceQuery);
			System.err.println(salesmanBalanceVOs.toString());
		} catch (YuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void findSonSalesmanBalanceVOByMonth(){
		SalesmanBalanceQuery salesmanBalanceQuery=new SalesmanBalanceQuery();
		salesmanBalanceQuery.setMonth("2014-08");
		salesmanBalanceQuery.setSalesman_id("279e5508-6cfc-436a-81fd-d7e86ff82461");
		try {
			SalesmanBalanceVO balanceVO = salesmanBalanceMongoImpl.findSonSalesmanBalanceVOByMonth(salesmanBalanceQuery);
			System.err.println(balanceVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void findSonSalesmanBalanceVOByMonthPage(){
		SalesmanBalanceQuery salesmanBalanceQuery=new SalesmanBalanceQuery();
		salesmanBalanceQuery.setMonth("2014-08");
		salesmanBalanceQuery.setSalesman_id("279e5508-6cfc-436a-81fd-d7e86ff82461");
		try {
			Page<SalesmanBalanceVO> page = this.salesmanBalanceMongoImpl.findSonSalesmanBalanceVOPage(salesmanBalanceQuery, 10, 1);
			for(SalesmanBalanceVO salesmanBalanceVO :page.getDatas()){
				System.err.println(salesmanBalanceVO.toString());
			}
			System.out.println(page.getRowCount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void findCompanyBalancePage(){
		SalesmanBalanceQuery salesmanBalanceQuery=new SalesmanBalanceQuery();
		salesmanBalanceQuery.setMonth("2014-08");
		salesmanBalanceQuery.setSalesman_id("425a6893-a775-4f0c-89c7-2a5de137d9fa");
		salesmanBalanceQuery.setCompany_name("ni");
		salesmanBalanceQuery.setBonus(1);
		Page<CompanyBalance> page = new Page<CompanyBalance>();
		try {
			page=salesmanBalanceMongoImpl.findCompanyBalancePage(salesmanBalanceQuery, 30, 1);
		} catch (YuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (CompanyBalance companyBalance : page.getDatas()) {
			System.err.println(companyBalance.toString());
		}
		
	}
}
*/