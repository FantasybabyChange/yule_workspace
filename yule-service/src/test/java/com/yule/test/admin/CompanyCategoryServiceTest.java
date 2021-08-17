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
//import com.yule.pojo.CompanyCategory;
//import com.yule.admin.query.InsertCompanyCategoryQuery;
//import com.yule.admin.service.ICompanyCategoryService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.constant.StatusConst;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//import com.yule.vo.Page;
//
//public class CompanyCategoryServiceTest extends TestCase {
//
//			
//	private ICompanyCategoryService companyCategoryServiceImpl;
//
//	public CompanyCategoryServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		companyCategoryServiceImpl = (ICompanyCategoryService) context
//				.getBean("companyCategoryServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("ICompanyCategoryService接口测试");
//		 test.addTest(new  CompanyCategoryServiceTest("findCompanyCategoryList"));
//		 test.addTest(new  CompanyCategoryServiceTest("findCompanyCategoryPage"));
//		 test.addTest(new CompanyCategoryServiceTest("findCompanyCategoryCount"));
//		 test.addTest(new CompanyCategoryServiceTest("findCompanyCategoryById"));
//		 test.addTest(new CompanyCategoryServiceTest("insertCompanyCategory"));
//		 test.addTest(new CompanyCategoryServiceTest("updateCompanyCategory"));
//		 test.addTest(new CompanyCategoryServiceTest("deleteCompanyCategoryById"));
//		 test.addTest(new CompanyCategoryServiceTest("batchUpdateCompanyCategory"));
//		 test.addTest(new CompanyCategoryServiceTest("batchInsertCompanyCategory"));
//		return test;
//	}
//
//	public void batchInsertCompanyCategory()throws YuleException{
//		InsertCompanyCategoryQuery companyCategoryQuery= new  InsertCompanyCategoryQuery();
//        List<CompanyCategory> lists  = new ArrayList<CompanyCategory>();
//        for (int i = 0; i < 5; i++) {
//        	CompanyCategory companyCategory = new CompanyCategory();
//        	if(i==2){
//        		companyCategory.setName("");
//        	}if(i==3){
//        		companyCategory.setName("演绎会所");
//        		companyCategory.setId("f73810b7-0390-4070-a3c0-60ae68e92ce1");
//        	}else{
//        		companyCategory.setName("name");
//        	}
//        	lists.add(companyCategory);
//		}
//        companyCategoryQuery.setCompanyCategoryQuery(lists);
//        this.companyCategoryServiceImpl.batchInsertCompanyCategory(companyCategoryQuery);
//	}
//	
//	public void batchUpdateCompanyCategory() throws YuleException{
//		List<String> listId = new ArrayList<String>();
//		for (int i = 0; i < 5; i++) {
//			listId.add(i+"00000000");
//		}
//		this.companyCategoryServiceImpl.batchUpdateCompanyCategory(listId);
//	}
//	
//	
//	public void insertCompanyCategory() {
//		CompanyCategory companyCategory = new CompanyCategory();
//		companyCategory.setIs_delete(StatusConst.IS_DELETE_TRUE);
//		companyCategory.setName("namenamename");
//		try {
//			System.out.println(companyCategoryServiceImpl
//					.insertCompanyCategory(companyCategory));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void updateCompanyCategory() {
//		CompanyCategory companyCategory = new CompanyCategory();
//		companyCategory.setId("f73810b7-0390-4070-a3c0-60ae68e92ce1");
//		companyCategory.setIs_delete("1");
//		companyCategory.setName("up_namenamename");
//		try {
//			System.out.println(companyCategoryServiceImpl
//					.updateCompanyCategory(companyCategory));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void deleteCompanyCategoryById() {
//		try {
//			System.out.println(companyCategoryServiceImpl
//					.deleteCompanyCategoryById(IDUtil.getID()));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void findCompanyCategoryPage()throws YuleException {
//		Page<CompanyCategory> page = companyCategoryServiceImpl .findCompanyCategoryPage(2, 1);
//		System.out.println(page.getDatas().size());
//		// for (CompanyCategory CompanyCategory : lists) {
//		// System.out.println(CompanyCategory.getName());
//		// }
//	}
//
//	public void findCompanyCategoryList() throws YuleException{
//		List<CompanyCategory> lists = companyCategoryServiceImpl.findCompanyCategoryList();
//		System.out.println(lists.size());
//		// for (CompanyCategory CompanyCategory : lists) {
//		// System.out.println(CompanyCategory.getName());
//		// }
//	}
//
//	public void findCompanyCategoryCount() throws YuleException{
//		System.out.println(companyCategoryServiceImpl
//				.findCompanyCategoryCount());
//	}
//
//	public void findCompanyCategoryById() throws YuleException{
//		CompanyCategory CompanyCategory = companyCategoryServiceImpl.findCompanyCategoryById(IDUtil.getID());
//		System.out.println(CompanyCategory);
//	}
//
// }
