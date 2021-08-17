/*package com.yule.mongo.test.company;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;

import com.yule.constant.CustomBeanConst;
import com.yule.mongo.company.query.BalanceQuery;
import com.yule.mongo.company.service.IOrdersMongo;
import com.yule.mongo.company.vo.BalanceVo;
import com.yule.util.CustomBeanFactory;
import com.yule.vo.Page;

public class OrdersMongoTest extends TestCase {

	private IOrdersMongo ordersMongoImpl = null;
	
	public OrdersMongoTest(String name) {
		super(name);
		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.COMPANY_MONGO_PATHS);
		ordersMongoImpl = (IOrdersMongo) context.getBean("ordersMongoImpl");
	}

	public static Test suite() {
		TestSuite test = new TestSuite("OrdersMongoTest接口测试");
//		test.addTest(new OrdersMongoTest("findLastMonthBalance"));
		test.addTest(new OrdersMongoTest("findLastHistoryBalance"));
		return test;
	}
	
	public void findLastMonthBalance()throws YuleException{
		BalanceQuery balanceQuer = new BalanceQuery();
		balanceQuer.setCompany_id("925bb45f-6bc9-44b2-b63d-91abebf5bff1");
		balanceQuer.setDay("2014-08");
		BalanceVo balanceVo= this.ordersMongoImpl.findLastMonthBalance(balanceQuer);
		System.err.println(balanceVo);
	}
	
	public void findLastHistoryBalance(){
		BalanceQuery balanceQuer = new BalanceQuery();
		balanceQuer.setCompany_id("925bb45f-6bc9-44b2-b63d-91abebf5bff1");
		balanceQuer.setStart_time("2014-08-01 00:00:00");
		balanceQuer.setEnd_time("2014-11-01 00:00:00");
		try {
			Page<BalanceVo> page = this.ordersMongoImpl.findHistoryBalance(balanceQuer, 10, 1);
			List<BalanceVo> balanceVos = page.getDatas();
			for (BalanceVo balanceVo : balanceVos) {
				System.out.println(balanceVo.toString());
			}
			System.err.println(balanceVos.size());
			page.cleanDatas();
			
		} catch (YuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
}

*/