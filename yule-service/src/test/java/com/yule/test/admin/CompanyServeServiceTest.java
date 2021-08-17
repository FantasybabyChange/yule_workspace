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
//import com.yule.pojo.CompanyServe;
//import com.yule.admin.query.InsertCompanyServeQuery;
//import com.yule.admin.service.ICompanyServeService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//public class CompanyServeServiceTest extends TestCase {
//
//	private ICompanyServeService companyServeSerivceImpl;
//
//	public CompanyServeServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		companyServeSerivceImpl = (ICompanyServeService) context.getBean("companyServeServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyServeServiceTest接口测试");
//		 test.addTest(new CompanyServeServiceTest("findCompanyServeList"));
//		 test.addTest(new CompanyServeServiceTest("findCompanyServeCount"));
//		 test.addTest(new CompanyServeServiceTest("findCompanyServeById"));
//		 test.addTest(new CompanyServeServiceTest("insertCompanyServe"));
//		 test.addTest(new CompanyServeServiceTest("updateCompanyServe"));
//		 test.addTest(new CompanyServeServiceTest("deleteCompanyServeById"));
//		 test.addTest(new CompanyServeServiceTest("batchInsertCompanyServe"));
//		return test;
//	}
//
//	public void batchInsertCompanyServe() throws YuleException{
//		InsertCompanyServeQuery  companyServeQuery = new InsertCompanyServeQuery();
//		List<CompanyServe> lists  = new ArrayList<CompanyServe>();
//		for (int i = 0; i < 5; i++) {
//			CompanyServe companyServe = new CompanyServe();
//			if(i==2){
//				companyServe.setName("");
//			}else if(i==3){
//				companyServe.setId("3ab9d8cb-87fd-410d-b76f-1f43edbc1ffd");
//				companyServe.setName("有小姐");
//			}else{
//				companyServe.setName("name");
//			}
//			lists.add(companyServe);
//		}
//		companyServeQuery.setCompanyServeQuery(lists);
//		this.companyServeSerivceImpl.batchInsertCompanyServe(companyServeQuery);
//	}
//	
//	public void findCompanyServeList() {
//		
//		try {
//			List<CompanyServe> lists = companyServeSerivceImpl.findCompanyServeList();
//			System.out.println(lists.size());
//			for (CompanyServe CompanyServe : lists) {
//				System.out.println(CompanyServe.getName());
//			}
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//		
//	}
//
//	public void findCompanyServeCount() {
//		try {
//			System.out.println(companyServeSerivceImpl.findCompanyServeCount());
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void findCompanyServeById() {
//		try {
//			CompanyServe CompanyServe = companyServeSerivceImpl.findCompanyServeById(IDUtil.getID());
//			System.out.println(CompanyServe);
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void insertCompanyServe() {
//		CompanyServe CompanyServe = new CompanyServe();
//		CompanyServe.setId(IDUtil.getID());
//		CompanyServe.setIs_delete("0");
//		CompanyServe.setIs_show("0");
//		CompanyServe.setName("name");
//		try {
//			System.out.println(companyServeSerivceImpl.insertCompanyServe(CompanyServe));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void updateCompanyServe() {
//		CompanyServe CompanyServe = new CompanyServe();
//		CompanyServe.setId(IDUtil.getID());
//		CompanyServe.setIs_delete("0");
//		CompanyServe.setIs_show("0");
//		CompanyServe.setName("name");
//		try {
//			System.out.println(companyServeSerivceImpl.updateCompanyServe(CompanyServe));
//		} catch (YuleException e) {
//		
//			e.printStackTrace();
//		}
//	}
//
//	public void deleteCompanyServeById() {
//		try {
//			System.out.println(companyServeSerivceImpl.deleteCompanyServeById(IDUtil.getID()));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
// }
