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
//import com.yule.pojo.CompanyGrade;
//import com.yule.admin.query.InsertCompanyGradeQuery;
//import com.yule.admin.service.ICompanyGradeService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//public class CompanyGradeServiceTest extends TestCase {
//
//	private ICompanyGradeService companyGradeServiceImpl;
//
//	public CompanyGradeServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		companyGradeServiceImpl = (ICompanyGradeService) context.getBean("companyGradeServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("ICompanyGradeService接口测试");
//		 
//		 test.addTest(new CompanyGradeServiceTest("findCompanyGradeList"));
//		 test.addTest(new CompanyGradeServiceTest("findCompanyGradeCount"));
//		 test.addTest(new CompanyGradeServiceTest("findCompanyGradeById"));
//		 test.addTest(new CompanyGradeServiceTest("insertCompanyGrade"));
//		 test.addTest(new CompanyGradeServiceTest("updateCompanyGrade"));
//		 test.addTest(new CompanyGradeServiceTest("deleteCompanyGradeById"));
//		 test.addTest(new CompanyGradeServiceTest("batchInsertCompanyGrade"));
//		return test;
//	}
//
//	public void batchInsertCompanyGrade() throws YuleException{
//		InsertCompanyGradeQuery  companyGradeQuery = new InsertCompanyGradeQuery();
//		List<CompanyGrade> lists  = new ArrayList<CompanyGrade>();
//		for (int i = 0; i < 5; i++) {
//			CompanyGrade companyGrade= new CompanyGrade();
//			if(i==2){
//				companyGrade.setName("");
//			} else 
//			if(i==3){
//				companyGrade.setId("4a3921a1-c782-4469-a9d0-61b606f942e4");
//				companyGrade.setName("支持银联");
//			}else{
//				companyGrade.setName("name");
//			}
//			lists.add(companyGrade);
//		}
//		companyGradeQuery.setCompanyGrade(lists);
//		this.companyGradeServiceImpl.batchInsertCompanyGrade(companyGradeQuery);
//	}
//	
//	public void findCompanyGradeList() throws YuleException {
//		List<CompanyGrade> lists = companyGradeServiceImpl.findCompanyGradeList();
//		System.out.println(lists.size());
//		for (CompanyGrade companyGrade : lists) {
//			System.out.println(companyGrade.getName());
//		}
//	}
//
//	public void findCompanyGradeCount() throws YuleException {
//		System.out.println(companyGradeServiceImpl.findCompanyGradeCount());
//	}
//
//	public void findCompanyGradeById() throws YuleException {
//		CompanyGrade companyGrade = companyGradeServiceImpl.findCompanyGradeById(IDUtil.getID());
//		System.out.println(companyGrade);
//	}
//
//	public void insertCompanyGrade() {
//		CompanyGrade companyGrade = new CompanyGrade();
//		companyGrade.setName("高档");
//		companyGrade.setIs_delete("0");
//		try {
//			System.out.println(companyGradeServiceImpl.insertCompanyGrade(companyGrade));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void updateCompanyGrade() {
//		CompanyGrade companyGrade = new CompanyGrade();
//		companyGrade.setName("asdf");
//		companyGrade.setId(IDUtil.getID());
//		companyGrade.setIs_delete("1");
//		companyGrade.setId(IDUtil.getID());
//		try {
//			System.out.println(companyGradeServiceImpl.updateCompanyGrade(companyGrade));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void deleteCompanyGradeById() {
//		try {
//			System.out.println(companyGradeServiceImpl.deleteCompanyGradeById(IDUtil.getID()));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
// }
