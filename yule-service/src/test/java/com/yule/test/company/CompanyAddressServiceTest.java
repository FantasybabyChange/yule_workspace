//package com.yule.test.company;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.company.service.ICompanyAddressService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.CompanyAddress;
//import com.yule.util.CustomBeanFactory;
//
//public class CompanyAddressServiceTest extends TestCase {
//
//	private ICompanyAddressService companyAddressServiceImpl;
//
//	public CompanyAddressServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.COMPANY_SERVICE_PATHS);
//		companyAddressServiceImpl = (ICompanyAddressService) context
//				.getBean("companyAddressServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyAddressServiceTest接口测试");
//		 test.addTest(new CompanyAddressServiceTest("insertCompanyAddress"));
//		 /*test.addTest(new CompanyAddressServiceTest("findCompanyAddressList"));
//		 test.addTest(new CompanyAddressServiceTest("findCompanyAddressCount"));
//		 test.addTest(new CompanyAddressServiceTest("findCompanyAddressById"));
//		 test.addTest(new CompanyAddressServiceTest("deleteCompanyAddressById"));
//		 test.addTest(new CompanyAddressServiceTest("updateCompanyAddress"));
//		 test.addTest(new CompanyAddressServiceTest("insertCompanyAddress"));*/
//		return test;
//	}
//	
//	public void insertCompanyAddress() throws YuleException {
//		CompanyAddress ca = new CompanyAddress();
//		ca.setAddress_detail("详细细节");
//		ca.setArea_county_id("1");
//		ca.setArea_business_id("2");
//		ca.setLat("0909090");
//		ca.setLng("-------");
//		System.out.println(companyAddressServiceImpl.insertOrUpdateCompanyAddress(ca, "02273571-bb7d-44e0-99c6-d7ec6d252fb2"));
//	}
//
// }
