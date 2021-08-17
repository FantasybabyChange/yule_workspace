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
//import com.yule.pojo.CompanyAddress;
//import com.yule.admin.service.ICompanyAddressService;
//import com.yule.admin.vo.CompanyAddressVO;
//import com.yule.constant.CustomBeanConst;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//public class CompanyAddressServiceTest extends TestCase {
//
//	private ICompanyAddressService companyAddressServiceImpl;
//
//	public CompanyAddressServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		companyAddressServiceImpl = (ICompanyAddressService) context
//				.getBean("companyAddressServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyAddressServiceTest接口测试");
//		 test.addTest(new CompanyAddressServiceTest("findCompanyAddressVOById"));
//		 test.addTest(new CompanyAddressServiceTest("findCompanyAddressList"));
//		 test.addTest(new CompanyAddressServiceTest("findCompanyAddressCount"));
//		 test.addTest(new CompanyAddressServiceTest("findCompanyAddressById"));
//		 test.addTest(new CompanyAddressServiceTest("deleteCompanyAddressById"));
//		 test.addTest(new CompanyAddressServiceTest("updateCompanyAddress"));
//		 test.addTest(new CompanyAddressServiceTest("insertCompanyAddress"));
//		return test;
//	}
//	
//	public void findCompanyAddressVOById() throws YuleException {
//		CompanyAddressVO vo = companyAddressServiceImpl.findCompanyAddressVOById(IDUtil.getID());
//		if(null != vo){			
//			System.out.println(vo.getId());
//		}else{
//			System.out.println("没数据");
//		}
//	}
//
//	public void findCompanyAddressList() throws YuleException {
//		List<CompanyAddress> lists = companyAddressServiceImpl
//				.findCompanyAddressList();
//		System.out.println(lists.size());
//		for (CompanyAddress caddress : lists) {
//			System.out.println(caddress.getAddress_detail());
//		}
//	}
//
//	public void findCompanyAddressCount() throws YuleException {
//		System.out.println(companyAddressServiceImpl
//				.findCompanyAddressCount());
//	}
//
//	public void findCompanyAddressById() throws YuleException {
//		CompanyAddress address = companyAddressServiceImpl
//				.findCompanyAddressById(IDUtil.getID());
//		System.out.println(address);
//	}
//
//	public void insertCompanyAddress() throws YuleException {
//		CompanyAddress companyAddress = new CompanyAddress();
//		companyAddress.setId(IDUtil.getID());
//		companyAddress.setAddress_detail("insert");
//		try {
//			System.out.println(companyAddressServiceImpl
//					.insertCompanyAddress(companyAddress));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void updateCompanyAddress() {
//		CompanyAddress companyAddress = new CompanyAddress();
//		companyAddress.setId(IDUtil.getID());
//		companyAddress.setAddress_detail("update");
//		try {
//			System.out.println(companyAddressServiceImpl
//					.updateCompanyAddress(companyAddress));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void deleteCompanyAddressById() {
//		try {
//			System.out.println(companyAddressServiceImpl
//					.deleteCompanyAddressById(IDUtil.getID()));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
// }
