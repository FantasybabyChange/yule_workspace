//package com.yule.test.admin;
//
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.admin.query.SalesmanQuery;
//import com.yule.admin.service.ISalesmanLoginService;
//import com.yule.admin.vo.SalesmanVO;
//import com.yule.constant.CustomBeanConst;
//import com.yule.constant.PageConst;
//import com.yule.constant.StatusConst;
//import com.yule.pojo.SalesmanLogin;
//import com.yule.util.CustomBeanFactory;
//import com.yule.vo.Page;
//
//public class SalesmanLoginServiceTest extends TestCase {
//
//	private ISalesmanLoginService salesmanLoginServiceImpl;
//
//	public SalesmanLoginServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		salesmanLoginServiceImpl = (ISalesmanLoginService) context
//				.getBean("salesmanLoginServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyServiceTest接口测试");
////		 test.addTest(new CompanyServiceTest("findCompanyList"));
////		 test.addTest(new CompanyServiceTest("findCompanyCount"));
//		 test.addTest(new SalesmanLoginServiceTest("insertSalesmanLogin"));
////		 test.addTest(new SalesmanLoginServiceTest("updateStatusSalesmanLogin"));
////		 test.addTest(new CompanyServiceTest("batchUpdateCompanyByAdminUserId"));
//		return test;
//	}
//	public void insertSalesmanLogin() throws YuleException{
// 		SalesmanLogin salesmanLogin =  new SalesmanLogin();
// 		salesmanLogin.setAccount("销售人员1");
// 		salesmanLogin.setPassword("admin123");
// 		this.salesmanLoginServiceImpl.insertSalesmanLogin(salesmanLogin);
//	}
//	public void deleteSalesmanLogin() throws YuleException{
// 		SalesmanLogin salesmanLogin =  new SalesmanLogin();
// 		salesmanLogin.setId("bc1f0edf-c997-489f-9004-3a3939a79af7");
// 		salesmanLogin.setIs_delete(StatusConst.IS_DELETE_TRUE);
// 		this.salesmanLoginServiceImpl.deleteSalesmanLogin(salesmanLogin);
//	}
//	
//	public void updateStatusSalesmanLogin() throws YuleException{
// 		SalesmanLogin salesmanLogin =  new SalesmanLogin();
// 		salesmanLogin.setId("bc1f0edf-c997-489f-9004-3a3939a79af7");
// 		salesmanLogin.setStatus(StatusConst.STATUS_TRUE);
// 		this.salesmanLoginServiceImpl.updateSalesmanLoginStatus(salesmanLogin);
//	}
//	public void updatePasswordLogin() throws YuleException{
//		SalesmanLogin salesmanLogin =  new SalesmanLogin();
// 		salesmanLogin.setId("bc1f0edf-c997-489f-9004-3a3939a79af7");
// 		salesmanLogin.setPassword("hehehehe");
// 		this.salesmanLoginServiceImpl.updateSalesmanLoginPassword(salesmanLogin);
//	}
//	public void findSalesmanLogin() throws YuleException{
//		SalesmanQuery 	salesmanQuery = new SalesmanQuery();
//		salesmanQuery.setAccount("销售123");
//		salesmanQuery.setIs_delete(1);
//		salesmanQuery.setStatus(1);
//		salesmanQuery.setStart_time("2014-8-30");
//		salesmanQuery.setEnd_time("2014-8-31");
//		Page<SalesmanVO> page = this.salesmanLoginServiceImpl.findSalesmanVOPage(salesmanQuery, PageConst.PAGE_SIZE_TEN, 1);
//		List<SalesmanVO> datas = page.getDatas();
//		System.out.println(page.getPageCount());
//		for (SalesmanVO s : datas) {
//			System.out.println(s.getAccount());
//			System.out.println(s.getName());
//			System.out.println(s.getIs_delete());
//			System.out.println(s.getStatus());
//		}
//	}
//   	
// }
