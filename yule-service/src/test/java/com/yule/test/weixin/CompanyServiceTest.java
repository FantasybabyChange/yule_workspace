//package com.yule.test.weixin;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.exception.YuleException;
//import com.yule.util.CustomBeanFactory;
//import com.yule.weixin.service.ICompanyService;
//
//public class CompanyServiceTest extends TestCase {
//
//	private ICompanyService companyServiceImpl;
//
//	public CompanyServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.WEIXIN_SERVICE_PATHS);
//		companyServiceImpl = (ICompanyService) context.getBean("companyServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyServiceTest接口测试");
//		 test.addTest(new CompanyServiceTest("updateCompanyOpenId"));
//		return test;
//	}
//    
//	public void updateCompanyOpenId() throws YuleException{
//		System.out.println(companyServiceImpl.updateCompanyOpenId("0968cb6d-c5cf-45db-835a-77c8416162fe", "321351434354354"));
//	}
//	
// }
