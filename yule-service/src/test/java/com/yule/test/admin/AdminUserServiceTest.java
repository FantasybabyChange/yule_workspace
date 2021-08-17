//package com.yule.test.admin;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.admin.service.IAdminUserService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.constant.StatusConst;
//import com.yule.pojo.AdminUser;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.EncryptUtil;
//import com.yule.util.IDUtil;
//import com.yule.vo.Page;
//
//public class AdminUserServiceTest extends TestCase {
//
//	private IAdminUserService adminUserServiceImpl;
//
//	public AdminUserServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		adminUserServiceImpl = (IAdminUserService) context
//				.getBean("adminUserServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("AdminUserServiceTest接口测试");
////		 test.addTest(new AdminUserServiceTest("findAdminUserById"));
////		 test.addTest(new AdminUserServiceTest("findAdminUserByAccount"));
//		 test.addTest(new AdminUserServiceTest("findAdminUserPage"));
////		 test.addTest(new AdminUserServiceTest("insertAdminUser"));
////		 test.addTest(new AdminUserServiceTest("updateAdminUser"));
////		 test.addTest(new AdminUserServiceTest("deleteAdminUserById"));
//		return test;
//	}
//	
//
//	public void findAdminUserPage()throws YuleException {
//		Page<AdminUser> page = adminUserServiceImpl.findAdminUserPage(10, 1);
//		for (AdminUser adminUser : page.getDatas()) {
//			System.out.println(adminUser.getAccount());
//		}
//	}
//
//	public void findAdminUserByAccount() throws YuleException{
//		System.out.println(adminUserServiceImpl
//				.findAdminUserByAccount("accountaccountaccount"));
//	}
//
//	public void findAdminUserById()throws YuleException {
//		AdminUser AdminUser = adminUserServiceImpl.findAdminUserById(IDUtil.getID());
//		System.out.println(AdminUser);
//	}
//
//	public void insertAdminUser() {
//		AdminUser adminUser = new AdminUser();
//		adminUser.setAccount("admin123");
//		adminUser.setIs_delete(StatusConst.IS_DELETE_TRUE);
//		adminUser.setStatus(StatusConst.STATUS_TRUE);
//		adminUser.setId(IDUtil.getID());
//		adminUser.setPassword(EncryptUtil.encryptToMD5("admin123"));
//		try {
//			if (adminUserServiceImpl.insertAdminUser(adminUser)) {
//				String adminUserId = adminUser.getId();
//				System.out.println(adminUserId);
//			} else {
//				System.out.println("no");
//			}
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	public void updateAdminUser()throws YuleException {
//		AdminUser adminUser = new AdminUser();
//		adminUser.setId(IDUtil.getID());
//		adminUser.setAccount("up_count");
//		adminUser.setIs_delete(StatusConst.IS_DELETE_FALSE);
//		adminUser.setStatus(StatusConst.STATUS_FALSE);
//		adminUser.setPassword("up_password");
//		try {
//			if (adminUserServiceImpl.updateAdminUser(adminUser)) {
//				System.out.println("ok");
//			} else {
//				System.out.println("no");
//			}
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	public void deleteAdminUserById() {
//		try {
//			if (adminUserServiceImpl.deleteAdminUserById(IDUtil.getID())) {
//				System.out.println("ok");
//			} else {
//				System.out.println("no");
//			}
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//
//	}
// }
