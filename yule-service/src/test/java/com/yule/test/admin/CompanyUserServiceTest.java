//package com.yule.test.admin;
//
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//import org.springframework.context.ApplicationContext;
//import com.yule.admin.service.ICompanyUserService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.CompanyUser;
//import com.yule.util.CustomBeanFactory;
//import com.yule.vo.Page;
//
//public class CompanyUserServiceTest extends TestCase {
//	private ICompanyUserService companyUserService;
//
//	public CompanyUserServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		companyUserService = (ICompanyUserService) context.getBean("companyUserServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("ProductPriceServiceTest接口测试");
//		//		test.addTest(new CompanyUserServiceTest("insertCompanyService"));
//				test.addTest(new CompanyUserServiceTest("findCompanyUserServiceByAccount"));
//		//		test.addTest(new CompanyUserServiceTest("findCompanyUserServiceByAccount"));
////				test.addTest(new CompanyUserServiceTest("deleteCompanyUserByID"));
////				test.addTest(new CompanyUserServiceTest("updateCompanyUserService"));
//		return test;
//	}
//	public void insertCompanyService() throws YuleException{
//		CompanyUser cu = new CompanyUser();
//		cu.setAccount("雷帅");
//		cu.setCompany_id("075d3a2e-c52d-4803-9183-afe320bc07ec");
//		cu.setPassword("admin123");
//		boolean flag = companyUserService.insertCompanyUser(cu);
//		System.out.println(flag+	"---");
//	}
//	public void findCompanyServicePage() throws YuleException{
//		Page<CompanyUser> pages = this.companyUserService.findCompanyUserPage(2, 1,"54a7a923-55a6-4672-b54d-442bee81723a");
//		System.out.println(	pages.getPageCount());
//		if (pages != null) {
//			List<CompanyUser> datas = pages.getDatas();
//			if (datas != null && datas.size() > 0 ) {
//				for (CompanyUser cu : datas) {
//					System.out.println(cu.getAccount());
//					System.out.println(cu.getPassword());
//					System.out.println(cu.getIs_delete());
//					System.out.println(cu.getCompany_id());
//				}
//
//			}
//			System.out.println(datas);
//		}
//	}
//	public void findCompanyUserServiceByAccount() throws YuleException{
//		CompanyUser companyUser = this.companyUserService.findCompanyUserByAccount("磊","6694a539-fe14-4415-a130-44361e364192");
//		if (companyUser != null) {
//			System.out.println(companyUser.getId());
//			System.out.println(companyUser.getCreate_time());			
//		}
//		System.out.println(companyUser);
//	}
//	public void deleteCompanyUserByID() throws YuleException{
//		System.out.println(this.companyUserService.deleteCompanyUserById("faa22329-4b16-4fff-a9bd-4ea3ae6d5c04"));
//	}
//	public void findCompanyUserServiceById() throws YuleException{
//		CompanyUser companyUser = this.companyUserService.findCompanyUserById("44143b8d-939f-459a-a77c-e2e162205723");
//		if (companyUser != null) {
//			System.out.println(companyUser.getAccount());
//			System.out.println(companyUser.getCreate_time());			
//		}
//		System.out.println(companyUser);
//	}
//
//	public void updateCompanyUserService() throws YuleException{
//		CompanyUser companUser = new CompanyUser();
//		companUser.setId("fe740bd7-a738-49be-bbb4-521086ce1ba9");
//		companUser.setAccount("磊娃");
//		companUser.setPassword("wahaha");
//		companUser.setIs_delete("1");
//		this.companyUserService.updateCompanyUser(companUser);
//	}
//}
