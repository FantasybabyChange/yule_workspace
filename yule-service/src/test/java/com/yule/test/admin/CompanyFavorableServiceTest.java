//package com.yule.test.admin;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.admin.query.InsertCompanyFavorableQuery;
//import com.yule.admin.service.ICompanyFavorableService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.CompanyFavorable;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//public class CompanyFavorableServiceTest extends TestCase {
//	
//	private ICompanyFavorableService companyFavorableServiceImpl;
//
//	
//	public CompanyFavorableServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		companyFavorableServiceImpl = (ICompanyFavorableService) context
//				.getBean("companyFavorableServiceImpl");
//	}
//
//	public static Test suite() {
//		 TestSuite test = new TestSuite("CompanyFavorableServiceTest接口测试");
//		 test.addTest(new CompanyFavorableServiceTest("findCompanyFavorableByCompanyId"));
//		 test.addTest(new CompanyFavorableServiceTest("batchInsertCompanyFavorable"));
//		 test.addTest(new CompanyFavorableServiceTest("deleteCompanyFavorableById"));
//		return test;
//	}
//	
//	public void deleteCompanyFavorableById() throws YuleException{
//		System.out.println(this.companyFavorableServiceImpl.deleteCompanyFavorableById(IDUtil.getID()));
//	}
//	
//	public void findCompanyFavorableByCompanyId() throws YuleException{
//		List<CompanyFavorable> lists = this.companyFavorableServiceImpl.findCompanyFavorableByCompanyId("0000");
//		System.out.println(lists.size());
//	}
//	
//    public void batchInsertCompanyFavorable() throws YuleException{
//		InsertCompanyFavorableQuery companyFavorableQuery = new InsertCompanyFavorableQuery();
//		companyFavorableQuery.setCompany_id("0000");
//		List<CompanyFavorable> lists = new ArrayList<CompanyFavorable>();
//		for (int i = 0; i < 10; i++) {
//			CompanyFavorable companyFavorable=new CompanyFavorable();
//			companyFavorable.setContent("content");
//			companyFavorable.setName("name");
//			companyFavorable.setPrice(10);
//			lists.add(companyFavorable);
//		}
////		companyFavorableQuery.setCompanyFavorableQuery(lists);
//		System.out.println(this.companyFavorableServiceImpl.batchInsertCompanyFavorable(companyFavorableQuery));
//	}
//	
//}
