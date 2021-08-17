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
//import com.yule.pojo.CompanyServeRelevant;
//import com.yule.admin.service.ICompanyServeRelevantService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//public class CompanyServeRelevantServiceTest extends TestCase {
//
//	private ICompanyServeRelevantService companyServeRelevantServiceImpl;
//
//	public CompanyServeRelevantServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		companyServeRelevantServiceImpl = (ICompanyServeRelevantService) context.getBean("companyServeRelevantServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyServeRelevantServiceTest接口测试");
//		 test.addTest(new CompanyServeRelevantServiceTest("findCompanyServeRelevantList"));
//		 test.addTest(new CompanyServeRelevantServiceTest("findCompanyServeRelevantCount"));
//		 test.addTest(new CompanyServeRelevantServiceTest("findCompanyServeRelevantById"));
//		 test.addTest(new CompanyServeRelevantServiceTest("insertCompanyServeRelevant"));
//		 test.addTest(new CompanyServeRelevantServiceTest("updateCompanyServeRelevant"));
//		 test.addTest(new CompanyServeRelevantServiceTest("deleteCompanyServeRelevantById"));
//		return test;
//	}
//
//	public void findCompanyServeRelevantList() {
//		try {
//			List<CompanyServeRelevant> lists = companyServeRelevantServiceImpl.findCompanyServeRelevantList();
//			System.out.println(lists.size());
//			for (CompanyServeRelevant CompanyServeRelevant : lists) {
//				System.out.println(CompanyServeRelevant.getCompany_id());
//			}
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//		
//	}
//
//	public void findCompanyServeRelevantCount() {
//		try {
//			System.out.println(companyServeRelevantServiceImpl.findCompanyServeRelevantCount());
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void findCompanyServeRelevantById() {
//		try {
//			CompanyServeRelevant	CompanyServeRelevant = companyServeRelevantServiceImpl.findCompanyServeRelevantById(IDUtil.getID());
//			System.out.println(CompanyServeRelevant);
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void insertCompanyServeRelevant() {
//		CompanyServeRelevant CompanyServeRelevant = new CompanyServeRelevant();
//		CompanyServeRelevant.setCompany_id(IDUtil.getID());
//		CompanyServeRelevant.setCompany_serve_id(IDUtil.getID());
//		CompanyServeRelevant.setId(IDUtil.getID());
//		try {
//			companyServeRelevantServiceImpl.insertCompanyServeRelevant(CompanyServeRelevant);
//			System.out.println(CompanyServeRelevant.getId());
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void updateCompanyServeRelevant() {
//		CompanyServeRelevant CompanyServeRelevant = new CompanyServeRelevant();
//		CompanyServeRelevant.setCompany_id(IDUtil.getID());
//		CompanyServeRelevant.setCompany_serve_id(IDUtil.getID());
//		CompanyServeRelevant.setId(IDUtil.getID());
//		try {
//			System.out.println(companyServeRelevantServiceImpl.updateCompanyServeRelevant(CompanyServeRelevant));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void deleteCompanyServeRelevantById() {
//		try {
//			System.out.println(companyServeRelevantServiceImpl.deleteCompanyServeRelevantById(IDUtil.getID()));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
// }
