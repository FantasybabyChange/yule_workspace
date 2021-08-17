//package com.yule.test.admin;
//import java.util.ArrayList;
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.admin.param.UpdateAdminRolePrivilegeParam;
//import com.yule.admin.service.IAdminRoleService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.AdminRole;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//public class AdminRoleServiceTest extends TestCase {
//
//	private IAdminRoleService adminRoleServiceImpl;
//
//	public AdminRoleServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		adminRoleServiceImpl = (IAdminRoleService) context.getBean("adminRoleServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("AdminRoleServiceTest接口测试");
////		 test.addTest(new AdminRoleServiceTest("findAdminRoleList"));
////		 test.addTest(new AdminRoleServiceTest("findAdminRoleCount"));
////		 test.addTest(new AdminRoleServiceTest("findAdminRoleById"));
////		 test.addTest(new AdminRoleServiceTest("findAdminRolePage"));
////		 test.addTest(new AdminRoleServiceTest("findAdminRoleByAdminUserId"));
////		 test.addTest(new AdminRoleServiceTest("insertAdminRole"));
////		 test.addTest(new AdminRoleServiceTest("updateAdminRole"));
////		 test.addTest(new AdminRoleServiceTest("deleteAdminRoleById"));
////		 test.addTest(new AdminRoleServiceTest("batchDeleteAdminRoleById"));
//		test.addTest(new AdminRoleServiceTest("updateAdminRolePrivilege"));
//		return test;
//	}
//
//	public void updateAdminRolePrivilege()throws YuleException{
//		UpdateAdminRolePrivilegeParam updateAdminRolePrivilegeParam = new UpdateAdminRolePrivilegeParam();
//		updateAdminRolePrivilegeParam.setAdmin_role_id("a3a4cc8c-060b-4f36-88f9-08e97572ff5e");
//		List<String> id = new ArrayList<String>();
//		id.add(IDUtil.getID());
//		updateAdminRolePrivilegeParam.setAdmin_privilege_id(id);
//		adminRoleServiceImpl.updateAdminRolePrivilege(updateAdminRolePrivilegeParam);
////		this.adminRoleServiceImpl.batchDeleteAdminRoleById(id);
//	}
//	
//	public void batchDeleteAdminRoleById()throws YuleException{
//		List<String> id = new ArrayList<String>();
//		id.add(IDUtil.getID());
////		this.adminRoleServiceImpl.batchDeleteAdminRoleById(id);
//	}
//	
//	public void findAdminRoleByAdminUserId() throws YuleException{
//		System.out.println(this.adminRoleServiceImpl.findAdminRoleByAdminUserId(IDUtil.getID()).size());
//	}
//	
////	public void findAdminRolePage() throws YuleException {
////		Page<AdminRole> page = adminRoleServiceImpl.findAdminRolePage(10, 2);
////		for (AdminRole adminRole : page.getDatas()) {
////			System.out.println(adminRole.getName());
////		}
////	}
//
//	public void findAdminRoleList() throws YuleException {
//		List<AdminRole> lists = adminRoleServiceImpl.findAdminRoleList();
//		System.out.println(lists.size());
//		for (AdminRole AdminRole : lists) {
//			System.out.println(AdminRole.getName());
//		}
//	}
//
//	public void findAdminRoleCount() throws YuleException {
//		System.out.println(adminRoleServiceImpl.findAdminRoleCount());
//	}
//
//	public void findAdminRoleById() throws YuleException {
//		AdminRole AdminRole = adminRoleServiceImpl.findAdminRoleById(IDUtil.getID());
//		System.out.println(AdminRole);
//	}
//
//	public void insertAdminRole() {
//		AdminRole adminRole = new AdminRole();
//		adminRole.setName("name");
//		adminRole.setId(IDUtil.getID());
//		try {
//			if (adminRoleServiceImpl.insertAdminRole(adminRole)) {
//
//				System.out.println("ok");
//			} else {
//				System.out.println("no");
//			}
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	public void updateAdminRole() {
//		AdminRole adminRole = new AdminRole();
//		adminRole.setName("用户角色_name_up");
//		adminRole.setId(IDUtil.getID());
//		try {
//			if (adminRoleServiceImpl.updateAdminRole(adminRole)) {
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
//	public void deleteAdminRoleById() {
//		try {
//			if (adminRoleServiceImpl.deleteAdminRoleById("c87e2945-339c-4c96-96f1-a024b0534d9b")) {
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
