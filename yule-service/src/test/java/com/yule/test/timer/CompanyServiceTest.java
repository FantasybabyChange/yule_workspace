//package com.yule.test.timer;
//
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.timer.service.ICompanyService;
//import com.yule.timer.vo.CompanyLuceneVO;
//import com.yule.util.CustomBeanFactory;
//
//public class CompanyServiceTest extends TestCase {
//
//	private ICompanyService companyServiceImpl;
//
//	public CompanyServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.TIMER_SERVICE_PATHS);
//		companyServiceImpl = (ICompanyService) context.getBean("companyServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyServiceTest接口测试");
//		 test.addTest(new CompanyServiceTest("findCompanyVOList"));
//		return test;
//	}
//    
//	public void findCompanyVOList() throws YuleException{
//		List<CompanyLuceneVO> list = companyServiceImpl.findCompanyLuceneVOList();
//		System.out.println(list.size());
//	}
//	
//}