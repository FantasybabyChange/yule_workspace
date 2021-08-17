//package com.yule.test.admin;
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
//import com.yule.admin.service.ICompanyPrivilegeService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.CompanyPrivilege;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//import com.yule.vo.CompanyUserPrivilegeVO;
//
//public class CompanyPrivilegeTest extends TestCase {
//	private ICompanyPrivilegeService companyPrivilegeServiceImpl;
//
//	public CompanyPrivilegeTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		companyPrivilegeServiceImpl = (ICompanyPrivilegeService) context
//				.getBean("companyPrivilegeServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("insertCompanyPrivilege接口测试");
//		// test.addTest(new CompanyPrivilegeTest("insertCompanyPrivilege"));
//		// test.addTest(new// CompanyPrivilegeTest("findCompanyPrivilegeByCompanyId"));
//		// test.addTest(new CompanyPrivilegeTest("deleteCompanyPrivilegeById"));
//		test.addTest(new CompanyPrivilegeTest("deleteCompanyPrivilegeById"));
//		return test;
//	}
//
//	public void insertCompanyPrivilege() throws YuleException {
//		CompanyPrivilege cp = new CompanyPrivilege();
//		cp.setCompany_category_id("690cd0b6-92d3-4239-ac53-8b4c43500fa0");
//		cp.setUrl("//yezonghuidezilei1");
//		cp.setName("夜总会的子类1");
//		cp.setOrder(0);
//		boolean flag = this.companyPrivilegeServiceImpl
//				.insertCompanyPrivilege(cp);
//		System.out.println(flag + "-----------");
//	}
//
//	public void findCompanyPrivilegeByCompanyCategoryId() throws YuleException {
//		List<CompanyPrivilege> list = this.companyPrivilegeServiceImpl
//				.findCompanyPrivilegeList("690cd0b6-92d3-4239-ac53-8b4c43500fa0","");
//		for (CompanyPrivilege cp : list) {
//			System.out.println(cp.getId());
//			System.out.println(cp.getName());
//			System.out.println(cp.getCreate_time());
//			System.out.println(cp.getParent_id() + "....parentID");
//			System.out.println(cp.getUrl());
//			System.out.println(cp.getOrder());
//			System.out.println("-----------------------------");
//		}
//	}
//
//	public void deleteCompanyPrivilegeById() throws YuleException {
//		boolean flag = this.companyPrivilegeServiceImpl
//				.deleteCompanyPrivilegeById("ebd65f09-f99f-438a-b826-7ffb48d093b2");
//		System.out.println(flag);
//	}
//	public void findCOmpanyPrivilegeByUserId() throws YuleException {
//		List<CompanyUserPrivilegeVO> lists = this.companyPrivilegeServiceImpl
//				.findCompanyPrivilegeVOList("075d3a2e-c52d-4803-9183-afe320bc07ec","2b3b1ef5-8942-4131-9665-e19ce187160c");
//		for (CompanyUserPrivilegeVO list : lists) {
//			System.out.println(list.getName());
//			System.out.println(list.getPrivilege_id());
//			System.out.println(list.getIs_check());
//			System.out.println("--------------------");
//		}
//	}
//	public void insertCompanyUserPrivilege() throws YuleException{
//		List<String> ids =new ArrayList<String>();
//		ids.add("0dc82a66-abe7-4ed2-802c-00e23a5ea5dc");
//		ids.add("2cb249bd-45d9-4f48-9411-508598155169");
//		String userId = "2b3b1ef5-8942-4131-9665-e19ce187160c"; 
//		System.out.println(this.companyPrivilegeServiceImpl.deleteCompanyUserPrivilegeByUserId(userId,ids));
//	}
//	public void findCompanyUserById() throws YuleException{
//		CompanyPrivilege cp = this.companyPrivilegeServiceImpl.findCompanyPrivilegeById("310c6b46-8353-443e-bbd4-ffd4b86742b6");
//		System.out.println(cp.getName());
//		System.out.println(cp.getUrl());
//		System.out.println(cp.getParent_id());
//		System.out.println("--------------------");
//	}
//	public void findCompanyPrivilegeListByCategoryId() throws YuleException {
//		List<CompanyPrivilege> list = this.companyPrivilegeServiceImpl
//				.findCompanyPrivilegeByCategoryId("690cd0b6-92d3-4239-ac53-8b4c43500fa0");
//		for (CompanyPrivilege cp : list) {
//			System.out.println(cp.getId());
//			System.out.println(cp.getName());
//			System.out.println(cp.getCreate_time());
//			System.out.println(cp.getUrl());
//		}
//	}
//	public void findCompanyPrivilegeVOList()throws YuleException{
//		List<CompanyUserPrivilegeVO> list = this.companyPrivilegeServiceImpl.findCompanyPrivilegeVOList("3803546c-2933-40be-a921-9ae8af14a9c0", "6694a539-fe14-4415-a130-44361e364192");
//		for (CompanyUserPrivilegeVO cp : list) {
//			System.out.println(cp.getId());
//			System.out.println(cp.getName());
//			System.out.println("cp:---" + cp.getParent_id());
//		}
//	}
//	
//	public void batchInsertCompanyPrivilege() throws YuleException{
//		List<CompanyPrivilege> list = new ArrayList<CompanyPrivilege>();
//		CompanyPrivilege cp = new CompanyPrivilege();
//		cp.setId(IDUtil.getID());
//		cp.setParent_id("b59cce63-ffe5-412a-b962-11f4fe6b8f73");
//		cp.setCompany_category_id("d3794833-5401-47c5-b733-a9f1d42fb969");
//		cp.setName("测试1");
//		cp.setUrl("//dd");
//		cp.setOrder(0);
//		list.add(cp);
//		CompanyPrivilege cp1 = new CompanyPrivilege();
//		cp1.setId(IDUtil.getID());
//		cp1.setParent_id("b59cce63-ffe5-412a-b962-11f4fe6b8f73");
//		cp1.setCompany_category_id("d3794833-5401-47c5-b733-a9f1d42fb969");
//		cp1.setName("测试2");
//		cp1.setUrl("//dd2");
//		cp1.setOrder(1);
//		list.add(cp1);
//		this.companyPrivilegeServiceImpl.batchInsertCompanyPrivilege(list);
//	}
//	public void findCompanyPrivialgeByIds() throws YuleException{
//		this.companyPrivilegeServiceImpl.fin
//		
//	}
//	
//}
