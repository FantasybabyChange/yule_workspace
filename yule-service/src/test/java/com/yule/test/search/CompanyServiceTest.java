/*package com.yule.test.search;


import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;

import com.yule.constant.CustomBeanConst;
import com.yule.search.service.ICompanyService;
import com.yule.search.vo.CityCompanyVO;
import com.yule.util.CustomBeanFactory;

public class CompanyServiceTest extends TestCase {

	private ICompanyService companyServiceImpl;

	public CompanyServiceTest(String name) {
		super(name);
		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.SEARCH_SERVICE_PATHS);
		companyServiceImpl = (ICompanyService) context.getBean("companyServiceImpl");
	}

	public static Test suite() {
		TestSuite test = new TestSuite("CompanyServiceTest接口测试");
		 test.addTest(new CompanyServiceTest("findCompany"));
		return test;
	}
    
	public void findCompany() throws Exception{
		List<CityCompanyVO> cityCompanyVOs = this.companyServiceImpl.findCityCompany("610100");
		for (CityCompanyVO cityCompanyVO : cityCompanyVOs) {
			System.out.println(cityCompanyVO.toString());
		}
	}
	
}*/