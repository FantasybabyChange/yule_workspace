package com.yule.test.area;
//package com.yule.test.admin;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.admin.service.IAreaCityService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.AreaCity;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//public class AreaCityServiceTest extends TestCase {
//
//	private IAreaCityService areaCityServiceImpl;
//
//	public AreaCityServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		areaCityServiceImpl = (IAreaCityService) context.getBean("areaCityServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("AreaCityServiceTest接口测试");
////		 test.addTest(new AreaCityServiceTest("deleteAreaCityAll"));
////		 test.addTest(new AreaCityServiceTest("batchInsertAreaCity"));
////		 test.addTest(new AreaCityServiceTest("insertAreaCity"));
////		 test.addTest(new AreaCityServiceTest("findAreaCityList"));
////		 test.addTest(new AreaCityServiceTest("findAreaCityCount"));
////		 test.addTest(new AreaCityServiceTest("updateAreaCity"));
////		 test.addTest(new AreaCityServiceTest("deleteAreaCityById"));
//		return test;
//	}
//
//	public void findAreaCityList() throws YuleException{
//		List<AreaCity> lists = areaCityServiceImpl.findAreaCityList(IDUtil.getID());
//		System.out.println(lists.size());
//		for (AreaCity areaCity : lists) {
//			System.out.println(areaCity.getName());
//		}
//	}
//
//	public void findAreaCityCount()throws YuleException {
//		System.out.println(areaCityServiceImpl.findAreaCityCount());
//	}
//
//	public void batchInsertAreaCity() {
//		List<AreaCity> areaCitys = new ArrayList<AreaCity>();
//		AreaCity areaCity = new AreaCity();
//		areaCity.setName("namenamenamename");
//		areaCitys.add(areaCity);
//		areaCitys.add(areaCity);
//		try {
//			System.out.println(areaCityServiceImpl.batchInsertAreaCity(areaCitys));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void insertAreaCity() {
//		AreaCity areaCity = new AreaCity();
//		areaCity.setName("namenamenamename");
//		try {
//			System.out.println(areaCityServiceImpl.insertAreaCity(areaCity));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void updateAreaCity() {
//		AreaCity areaCity = new AreaCity();
//		areaCity.setId(IDUtil.getID());
//		areaCity.setName("up___namenamenamename");
//		try {
//			System.out.println(areaCityServiceImpl.updateAreaCity(areaCity));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void deleteAreaCityAll() {
//		try {			
//			System.out.println(areaCityServiceImpl.deleteAreaCityAll());
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void deleteAreaCityById() {
//		try {
//			System.out.println(areaCityServiceImpl.deleteAreaCityById(IDUtil.getID()));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
// }
