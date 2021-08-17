//package com.yule.test.pc;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.pc.service.ICompanyService;
//import com.yule.pc.vo.CompanyBrowseVO;
//import com.yule.util.CustomBeanFactory;
//
//public class CompanyServiceTest extends TestCase {
//
//	private ICompanyService companyServiceImpl;
//
//	public CompanyServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.PC_SERVICE_PATHS);
//		companyServiceImpl = (ICompanyService) context.getBean("companyServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyServiceTest接口测试");
//		 test.addTest(new CompanyServiceTest("findCompanyBrowseVOById"));
//		return test;
//	}
//    
//	public void findCompanyBrowseVOById() throws YuleException{
//		
//		CompanyBrowseVO companyBrowseVO = companyServiceImpl.findCompanyBrowseVOById("bfa26532-e5c2-4631-b50e-afa5611412b5");
//	    System.out.println(companyBrowseVO.getCompany_category_name());
//	}
//	
// }
