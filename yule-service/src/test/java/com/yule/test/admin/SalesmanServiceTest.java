//package com.yule.test.admin;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.admin.service.ISalesmanService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.Salesman;
//import com.yule.util.CustomBeanFactory;
//
//public class SalesmanServiceTest extends TestCase {
//
//	private ISalesmanService salesmanServiceImpl;
//
//	public SalesmanServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		salesmanServiceImpl = (ISalesmanService) context
//				.getBean("salesmanServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyServiceTest接口测试");
////		 test.addTest(new CompanyServiceTest("findCompanyList"));
////		 test.addTest(new CompanyServiceTest("findCompanyCount"));
//		 test.addTest(new SalesmanServiceTest("updateSalesman"));
////		 test.addTest(new SalesmanLoginServiceTest("updateStatusSalesmanLogin"));
////		 test.addTest(new CompanyServiceTest("batchUpdateCompanyByAdminUserId"));
//		return test;
//	}
//	
//	public void findSalesmanLogin() throws YuleException{
//	/*	SalesmanQuery 	salesmanQuery = new SalesmanQuery();
//		salesmanQuery.setAccount("销售");
//		salesmanQuery.setIs_delete(1);
//		salesmanQuery.setStatus(1);
//		salesmanQuery.setStart_time("2014-8-30");
//		salesmanQuery.setEnd_time("2014-8-31");*/
//		Salesman s = this.salesmanServiceImpl.findSalesmanById("0f991260-ad8a-4aed-b92f-9676d5354bf3");
//		System.out.println(s.getId());
//		System.out.println(s.getName());
//		System.out.println(s.getIdentity_card_image_path());
//	}
//	public void updateSalesman() throws YuleException{
//		Salesman s = new Salesman();
//		s.setId("0f991260-ad8a-4aed-b92f-9676d5354bf3");
//		s.setName("磊测试");
//		s.setCity_id(2);
//		s.setAge(13);
//		s.setIdentity_card("hehehhehe");
//		s.setIdentity_card_image_path("sdsds");
//		s.setSex(0);
//		s.setImage_path("sdasda");
//		boolean flag = this.salesmanServiceImpl.updateSalesman(s);
//		System.out.println(flag);
//		
//		
//		
//	}
//   	
// }
