//package com.yule.test.admin;
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.admin.service.IUserService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.User;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//import com.yule.vo.Page;
//
//public class UserServiceTest extends TestCase {
//	
//	private IUserService userServiceImpl;
//
//	public UserServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		userServiceImpl = (IUserService) context.getBean("userServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("UserServiceTest接口测试");
//		test.addTest(new UserServiceTest("findUserList"));
//		test.addTest(new UserServiceTest("insertUser"));
//		test.addTest(new UserServiceTest("findUserCount"));
//		test.addTest(new UserServiceTest("updateUser"));
//		test.addTest(new UserServiceTest("findUserById"));
//		test.addTest(new UserServiceTest("deleteUserById"));
//		return test;
//	}
//	
//	public void findUserList() throws YuleException{
//		List<User> lists = userServiceImpl.findUserList();
//		System.out.println(lists.size());
//		for (User user : lists) {
//			System.out.println(user.getName());
//		}
//	}
//	
//	public void findProductPage() throws YuleException{
//		Page<User> page = this.userServiceImpl.findUserPage(10, 1);
//		List<User> list = page.getDatas();
//		System.out.println(page.getRowCount());
//		for (User user : list) {
//			System.out.println(user.getName());
//		}
//	}
//	
//	public void findUserCount() {
//		try {
//			System.out.println(userServiceImpl.findUserCount());
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	public void findUserById() {
//		 
//		try {
//			User user = userServiceImpl.findUserById("463670d0-e862-450b-9efc-977d2addc13d");
//			System.out.println(user);
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	public void insertUser() {
//		User user = new User();
//		user.setId(IDUtil.getID());
//		user.setName("vddc");
//		user.setPassword("123vdv4565");
//		user.setPhone("152295v2522");
//		user.setMail("31567vd6336");
//		user.setIs_delete("1");
//		try {
//			System.out.println(userServiceImpl.insertUser(user));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	public void updateUser() {
//		User user = new User();
//		user.setId("463670d0-e862-450b-9efc-977d2addc13d");
//		user.setName("陈登勇");
//		user.setPassword("654321");
//		user.setPhone("15229503895");
//		user.setMail("415676336@qq.com");
//		user.setIs_delete("0");
//		user.setUpdate_time("2014-7-18");
//		try {
//			System.out.println(userServiceImpl.updateUser(user));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	public void deleteUserById() {
//		try {
//			System.out.println(userServiceImpl.deleteUserById("7f4b319e-9af0-4557-8b9f-930758ba13fe"));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//}
