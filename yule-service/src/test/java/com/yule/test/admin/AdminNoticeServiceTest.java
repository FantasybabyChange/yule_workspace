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
//import com.yule.admin.query.AdminNoticeQuery;
//import com.yule.admin.service.IAdminNoticeService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.AdminNotice;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//import com.yule.vo.Page;
//
//public class AdminNoticeServiceTest extends TestCase {
//
//	private IAdminNoticeService adminNoticeServiceImpl;
//
//	public AdminNoticeServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		adminNoticeServiceImpl = (IAdminNoticeService) context.getBean("adminNoticeServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("AdminNoticeServiceTest接口测试");
////		test.addTest(new AdminNoticeServiceTest("findAdminNoticeVO"));
////		 test.addTest(new AdminNoticeServiceTest("findAdminNoticeCount"));
////		 test.addTest(new AdminNoticeServiceTest("findAdminNoticeById"));
//		 test.addTest(new AdminNoticeServiceTest("findAdminNoticePage"));
////		 test.addTest(new AdminNoticeServiceTest("insertAdminNotice"));
////		 test.addTest(new AdminNoticeServiceTest("updateAdminNotice"));
////		 test.addTest(new AdminNoticeServiceTest("deleteAdminNoticeById"));
//		return test;
//	}
//	
//	public void findAdminNoticeList() {
//		try {
//			List<AdminNotice>	lists = adminNoticeServiceImpl.findAdminNoticeList();
//			System.out.println(lists.size());
//			for (AdminNotice AdminNotice : lists) {
//				System.out.println(AdminNotice.getContent());
//			}
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//		
//	}
//
//	public void findAdminNoticePage() throws YuleException{
//		Page<AdminNotice> page = adminNoticeServiceImpl.findAdminNoticePage(new AdminNoticeQuery(), 10, 1);
//		List<AdminNotice> lists = page.getDatas();
//		for (AdminNotice adminNotice : lists) {
//			System.out.println(adminNotice.getCreate_time());
//		}
//	}
//	
//	public void findAdminNoticeById() {
//		AdminNotice AdminNotice=null;
//		try {
//			AdminNotice = adminNoticeServiceImpl.findAdminNoticeById(IDUtil.getID());
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//		System.out.println(AdminNotice);
//	}
//
//	public void insertAdminNotice() {
//		AdminNotice adminNotice = new AdminNotice();
//		adminNotice.setContent("content");
//		adminNotice.setIs_delete(0);
//		adminNotice.setTitle("title");
//		adminNotice.setId(IDUtil.getID());
//		try {
//			adminNoticeServiceImpl.insertAdminNotice(adminNotice);
//			System.out.println(adminNotice.getId());
//		
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void updateAdminNotice() {
//		AdminNotice adminNotice = new AdminNotice();
//		adminNotice.setContent("content");
//		adminNotice.setIs_delete(1);
//		adminNotice.setTitle("title");
//		adminNotice.setId(IDUtil.getID());
//		try {
//			System.out.println(adminNoticeServiceImpl.updateAdminNotice(adminNotice));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void deleteAdminNoticeById() {
//		try {
//			System.out.println(adminNoticeServiceImpl.deleteAdminNoticeById(IDUtil.getID()));
//		} catch (YuleException e){
//			e.printStackTrace();
//		}
//	}
//}
