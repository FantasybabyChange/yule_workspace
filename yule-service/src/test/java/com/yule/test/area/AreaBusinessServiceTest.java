package com.yule.test.area;
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
//import com.yule.admin.service.IAreaBusinessService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.AreaBusiness;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//public class AreaBusinessServiceTest extends TestCase {
//	private IAreaBusinessService areaBusinessServiceImpl;
//
//	public AreaBusinessServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		areaBusinessServiceImpl = (IAreaBusinessService) context
//				.getBean("areaBusinessServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("AreaBusinessServiceTest接口测试");
////		 test.addTest(new AreaBusinessServiceTest("insertAreaBusiness"));
////		 test.addTest(new AreaBusinessServiceTest("findAreaBusinessList"));
////		 test.addTest(new AreaBusinessServiceTest("findAreaBusinessCount"));
////		 test.addTest(new AreaBusinessServiceTest("updateAreaBusiness"));
////		 test.addTest(new AreaBusinessServiceTest("deleteAreaBusinessById"));
//		 test.addTest(new AreaBusinessServiceTest("deleteAreaBusinessAll"));
//		return test;
//	}
//
//	public void findAreaBusinessList() throws YuleException {
//		List<AreaBusiness> lists = areaBusinessServiceImpl.findAreaBusinessList(IDUtil.getID());
//		System.out.println(lists.size());
//		for (AreaBusiness areaBusiness : lists) {
//			System.out.println(areaBusiness.getName());
//		}
//	}
//
//	public void findAreaBusinessCount() throws YuleException {
//		System.out.println(areaBusinessServiceImpl.findAreaBusinessCount());
//	}
//
//	public void insertAreaBusiness() {
//		AreaBusiness areaBusiness = new AreaBusiness();
//
//		areaBusiness.setName("NameNameName");
//		try {
//			System.out.println(areaBusinessServiceImpl.insertAreaBusiness(areaBusiness));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void updateAreaBusiness() {
//		AreaBusiness areaBusiness = new AreaBusiness();
//		areaBusiness.setName("upNameNameName");
//		try {
//			System.out.println(areaBusinessServiceImpl.updateAreaBusiness(areaBusiness));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void deleteAreaBusinessById() {
//		try {
//			System.out.println(areaBusinessServiceImpl.deleteAreaBusinessById(IDUtil.getID()));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void deleteAreaBusinessAll() {
//		try {
//			System.out.println(areaBusinessServiceImpl.deleteAreaBusinessAll());
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
// }
