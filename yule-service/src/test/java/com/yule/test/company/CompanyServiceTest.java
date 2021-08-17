/*package com.yule.test.company;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;

import com.yule.company.service.ICompanyService;
import com.yule.constant.CustomBeanConst;
import com.yule.pojo.Company;
import com.yule.util.CustomBeanFactory;
import com.yule.vo.CompanyVO;

public class CompanyServiceTest extends TestCase {

	private ICompanyService companyServiceImpl;
	
	public CompanyServiceTest(String name) {
		super(name);
		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.COMPANY_SERVICE_PATHS);
		companyServiceImpl = (ICompanyService) context.getBean("companyServiceImpl");
	}

	public static Test suite() {
		TestSuite test = new TestSuite("CompanyServiceTest接口测试");
		 test.addTest(new CompanyServiceTest("findCompanyVoById"));
		return test;
	}
	
	public void findSameCompany(){
		try {
			List<Company> companies = this.companyServiceImpl.findSameCompany("925bb45f-6bc9-44b2-b63d-91abebf5bff1");
			for (Company company : companies) {
				System.out.println(company.getName());
				System.out.println(company.getId());
			}
		} catch (YuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void findCompanyVoById(){
		try {
			CompanyVO companyVO = this.companyServiceImpl.findCompanyVOById("925bb45f-6bc9-44b2-b63d-91abebf5bff1");
			System.out.println(company);
		} catch (YuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 }
*/