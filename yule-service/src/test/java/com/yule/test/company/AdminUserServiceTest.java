//package com.yule.test.company;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.company.service.IAdminUserService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.AdminUser;
//import com.yule.util.CustomBeanFactory;
//
//public class AdminUserServiceTest extends TestCase {
//
//	private IAdminUserService adminUserServiceImpl;
//
//	public AdminUserServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.COMPANY_SERVICE_PATHS);
//		adminUserServiceImpl = (IAdminUserService) context
//				.getBean("adminUserServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyAddressServiceTest接口测试");
//		 test.addTest(new AdminUserServiceTest("findAdminUserByRoleIds"));
//		 /*test.addTest(new CompanyAddressServiceTest("findCompanyAddressList"));
//		 test.addTest(new CompanyAddressServiceTest("findCompanyAddressCount"));
//		 test.addTest(new CompanyAddressServiceTest("findCompanyAddressById"));
//		 test.addTest(new CompanyAddressServiceTest("deleteCompanyAddressById"));
//		 test.addTest(new CompanyAddressServiceTest("updateCompanyAddress"));
//		 test.addTest(new CompanyAddressServiceTest("insertCompanyAddress"));*/
//		return test;
//	}
//	public void findAdminUserByRoleIds() throws YuleException{
//		List<String> list = new ArrayList<String>();
//		list.add("1");
//		list.add("2");
//		list.add("ddd84668-a165-41a8-bd2c-e83a134d1421");
//		List<AdminUser> lists = this.adminUserServiceImpl.findAdminUserListByRoleIds(list);
//		for (AdminUser a: lists) {
//			System.out.println(a.getId());
//		}
//		
//		
//	}
//
// }
