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
//import com.yule.admin.service.IAdminPrivilegeService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.AdminPrivilege;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//public class AdminPrivilegeServiceTest extends TestCase {
//
//	private IAdminPrivilegeService adminPrivilegeServiceImpl;
//
//	public AdminPrivilegeServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		adminPrivilegeServiceImpl = (IAdminPrivilegeService) context
//				.getBean("adminPrivilegeServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("AdminPrivilegeServiceTest接口测试");
////		 test.addTest(new AdminPrivilegeServiceTest("findAdminPrivilegeList"));
////    	 test.addTest(new AdminPrivilegeServiceTest("findAdminPrivilegePage"));
////	     test.addTest(new AdminPrivilegeServiceTest("findAdminPrivilegeCount"));
////		 test.addTest(new AdminPrivilegeServiceTest("findAdminPrivilegeById"));
////		 test.addTest(new AdminPrivilegeServiceTest("findAdminPrivilegeByAdminUserId"));
////		 test.addTest(new AdminPrivilegeServiceTest("findAdminRoleByAdminUserId"));
////		 test.addTest(new AdminPrivilegeServiceTest("findAdminUserByIsAdmin"));
////		 test.addTest(new AdminPrivilegeServiceTest("findAdminRolePrivilegeById"));
////		 test.addTest(new AdminPrivilegeServiceTest("findAdminPrivilegeByParentId"));
////		 test.addTest(new AdminPrivilegeServiceTest("findAdminPrivilegeFuncList"));
////		 test.addTest(new AdminPrivilegeServiceTest("findAdminPrivilegeAllList"));
////		 test.addTest(new AdminPrivilegeServiceTest("deleteAdminPrivilegeById"));
//		 test.addTest(new AdminPrivilegeServiceTest("insertAdminPrivilege"));
////		 test.addTest(new AdminPrivilegeServiceTest("updateAdminPrivilege"));
//		return test;
//	}
//	
//	
////	public void findAdminUserByIsAdmin()throws YuleException{
////		List<AdminUser> lists = adminPrivilegeServiceImpl.findAdminUserByIsAdmin("1");
////		System.out.println(lists.size());
////	}
////	
////	public void findAdminUserByRoleId() throws YuleException{
////		List<AdminUser> lists = adminPrivilegeServiceImpl.findAdminUserByRoleId(19);
////		for (AdminUser adminUser : lists) {
////			System.out.println(adminUser.getAccount());
////		}
////	}
//	
//	
////	public void findAdminRoleByAdminUserId() throws YuleException {
////		List<AdminRole> lists = adminPrivilegeServiceImpl.findAdminRoleByAdminUserId(2);
////		for (AdminRole adminRole : lists) {
////			System.out.println(adminRole.getName());
////		}
////	}
//
//	public void findAdminPrivilegeList() throws YuleException {
//		List<AdminPrivilege> lists = adminPrivilegeServiceImpl
//				.findAdminPrivilegeList(null);
//		System.out.println(lists.size());
//		for (AdminPrivilege AdminPrivilege : lists) {
//			System.out.println(AdminPrivilege.getName());
//		}
//		// List<AdminPrivilege> lists = adminPrivilegeServiceImpl
//		// .findAdminPrivilegeList();
//		// System.out.println(lists.size());
//		// for (AdminPrivilege AdminPrivilege : lists) {
//		// System.out.println(AdminPrivilege.getName());
//		// }
//	}
//
//	public void findAdminPrivilegeById() throws YuleException {
//		AdminPrivilege AdminPrivilege = adminPrivilegeServiceImpl
//				.findAdminPrivilegeById(IDUtil.getID());
//		System.out.println(AdminPrivilege);
//	}
//
//	public void insertAdminPrivilege() {
//		AdminPrivilege adminPrivilege = new AdminPrivilege();
//		adminPrivilege.setName("tes33333333");
//		// adminPrivilege.setParent_id(0);
//		adminPrivilege.setUrl("TEST4444444444");
//		adminPrivilege.setId(IDUtil.getID());
//		try {
//			System.out.println(adminPrivilegeServiceImpl.insertAdminPrivilege(adminPrivilege));
//			System.out.println(adminPrivilege.getId());
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void updateAdminPrivilege() {
//		AdminPrivilege adminPrivilege = new AdminPrivilege();
//		adminPrivilege.setName("name_up");
//		adminPrivilege.setId(IDUtil.getID());
//		try {
//			System.out.println(adminPrivilegeServiceImpl.updateAdminPrivilege(adminPrivilege));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	 public void deleteAdminPrivilegeById() {
//		 try {
//			System.out.println(adminPrivilegeServiceImpl.deleteAdminPrivilegeById("04237f86-1e9b-466d-abdd-a0a6aaae7ece"));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	 }
//}
