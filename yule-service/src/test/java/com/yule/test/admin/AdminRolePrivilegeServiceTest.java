//package com.yule.test.admin;
//
//import java.util.List;
//
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//import junit.framework.Test;
//import org.springframework.context.ApplicationContext;
//import com.yule.pojo.AdminRolePrivilege;
//import com.yule.admin.service.IAdminRolePrivilegeService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//public class AdminRolePrivilegeServiceTest extends TestCase {
//
//	private IAdminRolePrivilegeService adminRolePrivilegeServiceImpl;
//
//	public AdminRolePrivilegeServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		adminRolePrivilegeServiceImpl = (IAdminRolePrivilegeService) context
//				.getBean("adminRolePrivilegeServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("AdminRolePrivilegeServiceTest接口测试");
//		     test.addTest(new AdminRolePrivilegeServiceTest("findAdminRolePrivilegeList"));
//		     test.addTest(new AdminRolePrivilegeServiceTest("findAdminRolePrivilegeCount"));
//		     test.addTest(new AdminRolePrivilegeServiceTest("findAdminRolePrivilegeById"));
////		 test.addTest(new AdminRolePrivilegeServiceTest("insertAdminRolePrivilege"));
////		 test.addTest(new AdminRolePrivilegeServiceTest("updateAdminRolePrivilege"));
////		 test.addTest(new AdminRolePrivilegeServiceTest( "deleteAdminRolePrivilegeById"));
////		 test.addTest(new AdminRolePrivilegeServiceTest("deleteAdminRolePrivilegeByRoleId"));
////		 test.addTest(new AdminRolePrivilegeServiceTest("deleteAdminRolePrivilegeByPrivilegeId"));
//		     test.addTest(new AdminRolePrivilegeServiceTest("findAdminRolePrivilegeByAminRoleIdOrAdminPrivilegeId"));
//		return test;
//	}
//   
//	public void findAdminRolePrivilegeByAminRoleIdOrAdminPrivilegeId() throws YuleException{
//		AdminRolePrivilege adminRolePrivilege=this.adminRolePrivilegeServiceImpl.findAdminRolePrivilegeByAminRoleIdOrAdminPrivilegeId("1",IDUtil.getID());
//		System.out.println(adminRolePrivilege);
//	}
//
//	public void deleteAdminRolePrivilegeByPrivilegeId(){
//		try {
//			adminRolePrivilegeServiceImpl.deleteAdminRolePrivilegeByPrivilegeId(IDUtil.getID());
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void deleteAdminRolePrivilegeByRoleId(){
//		try {
//			adminRolePrivilegeServiceImpl.deleteAdminRolePrivilegeByRoleId(IDUtil.getID());
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void findAdminRolePrivilegeList() throws YuleException {
//		List<AdminRolePrivilege> lists = adminRolePrivilegeServiceImpl.findAdminRolePrivilegeList(null);
//		System.out.println(lists.size());
//		for (AdminRolePrivilege AdminRolePrivilege : lists) {
//			System.out.println(AdminRolePrivilege.getAdmin_privilege_id());
//		}
//	}
//
//	public void findAdminRolePrivilegeCount() throws YuleException {
//		System.out.println(adminRolePrivilegeServiceImpl
//				.findAdminRolePrivilegeCount());
//	}
//
//	public void findAdminRolePrivilegeById() throws YuleException {
//		AdminRolePrivilege AdminRolePrivilege = adminRolePrivilegeServiceImpl.findAdminRolePrivilegeById(IDUtil.getID());
//
//		System.out.println(AdminRolePrivilege);
//	}
//
//	public void insertAdminRolePrivilege() {
//		AdminRolePrivilege adminRolePrivilege = new AdminRolePrivilege();
//		adminRolePrivilege.setAdmin_privilege_id(IDUtil.getID());
//		adminRolePrivilege.setAdmin_role_id(IDUtil.getID());
//		adminRolePrivilege.setId(IDUtil.getID());
//		try {
//			System.out.println(adminRolePrivilegeServiceImpl
//					.insertAdminRolePrivilege(adminRolePrivilege));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void updateAdminRolePrivilege() {
//		AdminRolePrivilege adminRolePrivilege = new AdminRolePrivilege();
//		adminRolePrivilege.setId(IDUtil.getID());
//		adminRolePrivilege.setAdmin_privilege_id(IDUtil.getID());
//		adminRolePrivilege.setAdmin_role_id(IDUtil.getID());
//		try {
//			System.out.println(adminRolePrivilegeServiceImpl
//					.updateAdminRolePrivilege(adminRolePrivilege));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void deleteAdminRolePrivilegeById() {
//		try {
//			System.out.println(adminRolePrivilegeServiceImpl
//					.deleteAdminRolePrivilegeById(IDUtil.getID()));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
// }
