/*package com.yule.test.company;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;

import com.yule.company.query.CompanyUserQuery;
import com.yule.company.service.ICompanyUserService;
import com.yule.company.vo.CompanyUserVO;
import com.yule.constant.CustomBeanConst;
import com.yule.constant.StatusConst;
import com.yule.exception.YuleException;
import com.yule.param.UpdateCompanyUserPrivilegeParam;
import com.yule.pojo.CompanyUser;
import com.yule.util.CustomBeanFactory;
import com.yule.vo.Page;

public class CompanyUserServiceTest extends TestCase {

	private ICompanyUserService companyUserServiceImpl;

	public CompanyUserServiceTest(String name) {
		super(name);
		ApplicationContext context = CustomBeanFactory
				.getContext(CustomBeanConst.COMPANY_SERVICE_PATHS);
		companyUserServiceImpl = (ICompanyUserService) context
				.getBean("companyUserServiceImpl");
	}

	public static Test suite() {
		TestSuite test = new TestSuite("CompanyAddressServiceTest接口测试");
		 test.addTest(new CompanyUserServiceTest("findCompanyUserVOById"));
		 test.addTest(new CompanyAddressServiceTest("findCompanyAddressList"));
		 test.addTest(new CompanyAddressServiceTest("findCompanyAddressCount"));
		 test.addTest(new CompanyAddressServiceTest("findCompanyAddressById"));
		 test.addTest(new CompanyAddressServiceTest("deleteCompanyAddressById"));
		 test.addTest(new CompanyAddressServiceTest("updateCompanyAddress"));
		 test.addTest(new CompanyAddressServiceTest("insertCompanyAddress"));
		return test;
	}
	
	public void findCompanyUserByAccount() throws YuleException {
		CompanyUserVO company = this.companyUserServiceImpl.findCompanyUserVOByAccount("zhanglei3", 10003);
		System.out.println(company.getAccount());
		System.out.println(company.getPassword());
	}
	public void findCompanyUserVOById() throws YuleException{
		CompanyUserVO cu = this.companyUserServiceImpl.findCompanyUserVOById("6fd022d5-245d-41c6-8830-ce2714806963");
		System.out.println(cu.getAccount());
		System.out.println(cu.getPassword());
		System.out.println(cu.getCode());
		System.out.println(cu.getCompany_id());
		System.out.println(cu.getInfo_is_auth());
		System.out.println(cu.getAddress_is_auth());
	}
	public void findCompanyUserPage()throws YuleException{
		CompanyUserQuery companyUser = new CompanyUserQuery();
		companyUser.setCompany_id("e6ff779d-c884-40f9-a2c7-734753dfdc41");
		Page<CompanyUser> page = this.companyUserServiceImpl.findCompanyUserPage(companyUser, 1, Integer.MAX_VALUE);
		List<CompanyUser> datas = page.getDatas();
		for (CompanyUser c : datas) {
			System.out.println(c.getAccount());
			System.out.println(c.getId());
			System.out.println(c.getStatus());
			System.out.println(c.getLogin_time());
		}
	}
	public void updateCompanyUSerStatus()throws YuleException{
		CompanyUser companyUser = new CompanyUser();
		companyUser.setId("98a406c5-47aa-4096-b946-9abfedfd7bdf");
		companyUser.setStatus(StatusConst.STATUS_FALSE);
		this.companyUserServiceImpl.updateCompanyUser(companyUser);
		
	}
	public void updateCompanyUserPrivilege()throws YuleException{
		UpdateCompanyUserPrivilegeParam updateCompanyUserPrivilegeParam = new UpdateCompanyUserPrivilegeParam();
		updateCompanyUserPrivilegeParam.setCompany_user_id("31933a55-c7f4-40ae-9a35-b6d54876b69e");
		this.companyUserServiceImpl.updateCompanyUserPrivilege(updateCompanyUserPrivilegeParam);
		
		
	}

 }
*/