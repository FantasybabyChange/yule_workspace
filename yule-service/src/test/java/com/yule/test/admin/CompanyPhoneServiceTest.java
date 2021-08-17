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
//import com.yule.admin.service.ICompanyPhoneService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.CompanyPhone;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//public class CompanyPhoneServiceTest extends TestCase {
//
//	private ICompanyPhoneService companyPhoneServiceImpl;
//
//	public CompanyPhoneServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		companyPhoneServiceImpl = (ICompanyPhoneService) context.getBean("companyPhoneServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("ICompanyPhoneService接口测试");
//		 test.addTest(new CompanyPhoneServiceTest("findCompanyPhoneList"));
////		 test.addTest(new CompanyPhoneServiceTest("insertCompanyPhone"));
////		 test.addTest(new CompanyPhoneServiceTest("updateCompanyPhone"));
////		 test.addTest(new CompanyPhoneServiceTest("deleteCompanyPhoneById"));
//		return test;
//	}
//
//	
//	public void findCompanyPhoneList() throws YuleException {
//		List<CompanyPhone> lists = companyPhoneServiceImpl.findCompanyPhoneListByCompanyId("2b959fb8-94e9-453f-bef1-a8f905a419da");
//		System.out.println(lists.size());
//		for (CompanyPhone companyPhone : lists) {
//			System.out.println(companyPhone.getName());
//		}
//	}
//
//
//	public void insertCompanyPhone() {
//		CompanyPhone companyPhone = new CompanyPhone();
//		companyPhone.setName("高档");
//		companyPhone.setIs_delete("0");
//		try {
//			System.out.println(companyPhoneServiceImpl.insertCompanyPhone(companyPhone));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void updateCompanyPhone() {
//		CompanyPhone companyPhone = new CompanyPhone();
//		companyPhone.setName("asdf");
//		companyPhone.setId(IDUtil.getID());
//		companyPhone.setIs_delete("1");
//		companyPhone.setId(IDUtil.getID());
//		try {
//			System.out.println(companyPhoneServiceImpl.updateCompanyPhone(companyPhone));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void deleteCompanyPhoneById() {
//		try {
//			System.out.println(companyPhoneServiceImpl.deleteCompanyPhoneById(IDUtil.getID()));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
// }
