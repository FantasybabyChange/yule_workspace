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
//import com.yule.admin.service.IAreaProvinceService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.AreaProvince;
//import com.yule.util.CustomBeanFactory;
//
//public class AreaProvinceServiceTest extends TestCase {
//	private IAreaProvinceService areaProvinceServiceImpl;
//
//	public AreaProvinceServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		areaProvinceServiceImpl = (IAreaProvinceService) context
//				.getBean("areaProvinceServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("AreaProvinceServiceTest接口测试");
////		 test.addTest(new AreaProvinceServiceTest("findAreaProvinceList"));
////		 test.addTest(new AreaProvinceServiceTest("findAreaProvinceCount"));
//		 test.addTest(new AreaProvinceServiceTest("insertAreaProvince"));
////		 test.addTest(new AreaProvinceServiceTest("updateAreaProvince"));
////		 test.addTest(new AreaProvinceServiceTest("deleteAreaProvinceById"));
//		return test;
//	}
//
//	public void findAreaProvinceList() throws YuleException{
//		List<AreaProvince> lists = areaProvinceServiceImpl.findAreaProvinceList();
//		System.out.println(lists.size());
//		for (AreaProvince areaProvince : lists) {
//			System.out.println(areaProvince.getName());
//		}
//	}
//
//	public void findAreaProvinceCount() throws YuleException{
//		System.out.println(areaProvinceServiceImpl.findAreaProvinceCount());
//	}
//
//	public void insertAreaProvince()throws YuleException {
//		AreaProvince areaProvince = new AreaProvince();
//		areaProvince.setName("大味道");
//		areaProvince.setId(1);
//		try {
//			System.out.println(areaProvinceServiceImpl.insertAreaProvince(areaProvince));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void updateAreaProvince() throws YuleException{
//		AreaProvince areaProvince = new AreaProvince();
//		areaProvince.setId(1);
//		areaProvince.setName("up___namenamenamename");
//		try {
//			System.out.println(areaProvinceServiceImpl.updateAreaProvince(areaProvince));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void deleteAreaProvinceById()throws YuleException {
//		try {
//			System.out.println(areaProvinceServiceImpl.deleteAreaProvinceById(1));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
