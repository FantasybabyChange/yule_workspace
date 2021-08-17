//package com.yule.test.company;
//
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.company.service.IAdminNoticeService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.constant.PageConst;
//import com.yule.pojo.AdminNotice;
//import com.yule.util.CustomBeanFactory;
//
//public class AdminNoticeServiceTest extends TestCase {
//
//	private IAdminNoticeService adminNoticeServiceImpl;
//
//	public AdminNoticeServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.COMPANY_SERVICE_PATHS);
//		adminNoticeServiceImpl = (IAdminNoticeService) context
//				.getBean("adminNoticeServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyAddressServiceTest接口测试");
//		 test.addTest(new AdminNoticeServiceTest("findAdminNoticeByType"));
//		 /*test.addTest(new CompanyAddressServiceTest("findCompanyAddressList"));
//		 test.addTest(new CompanyAddressServiceTest("findCompanyAddressCount"));
//		 test.addTest(new CompanyAddressServiceTest("findCompanyAddressById"));
//		 test.addTest(new CompanyAddressServiceTest("deleteCompanyAddressById"));
//		 test.addTest(new CompanyAddressServiceTest("updateCompanyAddress"));
//		 test.addTest(new CompanyAddressServiceTest("insertCompanyAddress"));*/
//		return test;
//	}
//	public void findAdminNoticeByType() throws YuleException{
//		List<AdminNotice> list = this.adminNoticeServiceImpl.findAdminNoticeFirstPage(PageConst.PAGE_SIZE_TEN);
//			for (AdminNotice ad : list) {
//				System.out.println(ad.getContent());
//				System.out.println(ad.getTitle());
//				System.out.println(ad.getCreate_time());
//			}
//		
//	}
//
// }
