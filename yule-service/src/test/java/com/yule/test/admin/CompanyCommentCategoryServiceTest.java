//package com.yule.test.admin;
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.admin.service.ICompanyCommentCategoryService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.CompanyCommentCategory;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//public class CompanyCommentCategoryServiceTest extends TestCase {
//	
//	private ICompanyCommentCategoryService companyCommentCategoryServiceImpl;
//
//	public CompanyCommentCategoryServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		companyCommentCategoryServiceImpl = (ICompanyCommentCategoryService) context.getBean("companyCommentCategoryServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyCommentCategoryServiceTest接口测试");
//		test.addTest(new CompanyCommentCategoryServiceTest("findCompanyCommentCategoryList"));
//		test.addTest(new CompanyCommentCategoryServiceTest("findCompanyCommentCategoryCount"));
//		test.addTest(new CompanyCommentCategoryServiceTest("findCompanyCommentCategoryById"));
//		test.addTest(new CompanyCommentCategoryServiceTest("deleteCompanyCommentCategoryById"));
//		test.addTest(new CompanyCommentCategoryServiceTest("updateCompanyCommentCategory"));
//		test.addTest(new CompanyCommentCategoryServiceTest("insertCompanyCommentCategory"));
//		return test;
//	}
//	
//	public void findCompanyCommentCategoryList() throws YuleException{
//		List<CompanyCommentCategory> lists = companyCommentCategoryServiceImpl.findCompanyCommentCategoryList();
//		System.out.println(lists.size());
//		for (CompanyCommentCategory companyCommentCategory : lists) {
//			System.out.println(companyCommentCategory.getName());
//		}
//	}
//	
//	public void findCompanyCommentCategoryCount() {
//		try {
//			System.out.println(companyCommentCategoryServiceImpl.findCompanyCommentCategoryCount());
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	public void findCompanyCommentCategoryById() {
//		 
//		try {
//			CompanyCommentCategory companyCommentCategory = companyCommentCategoryServiceImpl.findCompanyCommentCategoryById("7ce0ce11-b687-4548-89fe-b1314129cd84");
//			System.out.println(companyCommentCategory);
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	public void insertCompanyCommentCategory() {
//		CompanyCommentCategory companyCommentCategory = new CompanyCommentCategory();
//		companyCommentCategory.setId(IDUtil.getID());
//		companyCommentCategory.setName("vd11");
//		try {
//			System.out.println(companyCommentCategoryServiceImpl.insertCompanyCommentCategory(companyCommentCategory));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	public void updateCompanyCommentCategory() {
//		CompanyCommentCategory companyCommentCategory = new CompanyCommentCategory();
//		companyCommentCategory.setId("7ce0ce11-b687-4548-89fe-b1314129cd84");
//		companyCommentCategory.setName("陈登勇");
//		try {
//			System.out.println(companyCommentCategoryServiceImpl.updateCompanyCommentCategory(companyCommentCategory));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	public void deleteCompanyCommentCategoryById() {
//		try {
//			System.out.println(companyCommentCategoryServiceImpl.deleteCompanyCommentCategoryById("c384173c-d64d-4d23-95f2-eaee6f13c589"));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//}
