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
//import com.yule.admin.param.InsertCompanyManagerParam;
//import com.yule.admin.query.CompanyManagerQuery;
//import com.yule.admin.service.ICompanyService;
//import com.yule.admin.vo.CompanyManagerVO;
//import com.yule.constant.CustomBeanConst;
//import com.yule.enumerate.DateStyle;
//import com.yule.pojo.Company;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.DateUtil;
//import com.yule.util.IDUtil;
//import com.yule.vo.CompanyVO;
//import com.yule.vo.Page;
//
//public class CompanyServiceTest extends TestCase {
//
//	private ICompanyService companyServiceImpl;
//
//	public CompanyServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		companyServiceImpl = (ICompanyService) context.getBean("companyServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyServiceTest接口测试");
////		 test.addTest(new CompanyServiceTest("findCompanyList"));
////		 test.addTest(new CompanyServiceTest("findCompanyCount"));
////		 test.addTest(new CompanyServiceTest("findCompanyByAccount"));
////		 test.addTest(new CompanyServiceTest("updateCompany"));
////		 test.addTest(new CompanyServiceTest("batchUpdateCompanyByAdminUserId"));
//		test.addTest(new CompanyServiceTest("insertCompanyManager"));
////		test.addTest(new CompanyServiceTest("findCompanyManagerVOPage"));
//		return test;
//	}
//	
//	public void insertCompanyManager() throws YuleException {
//		InsertCompanyManagerParam param = new InsertCompanyManagerParam();
//		param.setAccount("1111111");
//		param.setCode("1111111");
//		param.setPassword("1111111");
//		param.setName("1111111");
//		companyServiceImpl.insertCompanyManager(param, "12312312321");
//	}
//	
//	public void findCompanyManagerVOPage(){
//		try {
//			CompanyManagerQuery companyManagerQuery = new CompanyManagerQuery();
////			companyManagerQuery.setStart_time("2014-08-16 10:11:11");
//			companyManagerQuery.setEnd_time("2014-08-24 12:11:11");
//			Page<CompanyManagerVO> page = companyServiceImpl.findCompanyManagerVOPage(companyManagerQuery, Integer.MAX_VALUE, 1);
//			for(CompanyManagerVO companyManagerVO:page.getDatas()){
//				System.out.println(DateUtil.DateToString(companyManagerVO.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
//			}
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//   	
//	public void findCompanyList() {
//		
//		try {
//			List<Company> lists = companyServiceImpl.findCompanyList();
//		System.out.println(lists.size());
//			for (Company Company : lists) {
//				System.out.println(Company.getName());
//			}
//		} catch (YuleException e) {
//		e.printStackTrace();
//		}
//	
//	}
//	public void findCompany() throws YuleException{
//		List<Company> cp = this.companyServiceImpl.findCompanyList();
//		System.out.println(cp);
//		for (Company company : cp) {
//			System.out.println(company.getName());
//			System.out.println(company.getId());
//		}
//		
//	}
//
//	public void findCompanyById() throws YuleException {
//		Company Company = companyServiceImpl.findCompanyById(IDUtil.getID());
//		System.out.println(Company);
//	}
//	
//	public void findCompanyVOById() throws YuleException {
//		CompanyVO Company = companyServiceImpl.findCompanyVOById("");
//		System.out.println(Company);
//	}
//
//
//	public void updateCompany() {
//		Company company = new Company();
//		company.setId(IDUtil.getID());
//		company.setCompany_category_id(IDUtil.getID());
//		company.setCompany_grade_id(IDUtil.getID());
//		company.setHours("HoursHours");
//		company.setName("update");
//		company.setPeriphery("peripheryperipheryperiphery");
//		company.setPinyin("pinyinpinyinpinyinpinyin");
//		company.setRebast("1.06");
//		try {
//			System.out.println(companyServiceImpl.updateCompany(company));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
// }
