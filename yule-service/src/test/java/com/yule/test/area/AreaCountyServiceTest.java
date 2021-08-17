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
//import com.yule.admin.service.IAreaCountyService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.AreaCounty;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//public class AreaCountyServiceTest extends TestCase {
//	private IAreaCountyService areaCountyServiceImpl;
//
//	public AreaCountyServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		areaCountyServiceImpl = (IAreaCountyService) context
//				.getBean("areaCountyServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("AreaCountyServiceTest接口测试");
//		test.addTest(new AreaCountyServiceTest("findAreaCountyList"));
//		test.addTest(new AreaCountyServiceTest("findAreaCountyCount"));
//		test.addTest(new AreaCountyServiceTest("insertAreaCounty"));
//		test.addTest(new AreaCountyServiceTest("updateAreaCounty"));
//		test.addTest(new AreaCountyServiceTest("deleteAreaCountyById"));
//		return test;
//	}
//
//	public void findAreaCountyList() throws YuleException{
//		List<AreaCounty> lists = areaCountyServiceImpl.findAreaCountyList(IDUtil.getID());
//		System.out.println(lists.size());
//		// for (AreaCounty areaCounty : lists) {
//		// System.out.println(areaCounty.getName());
//		// }
//	}
//
//	public void findAreaCountyCount() throws YuleException{
//		System.out.println(areaCountyServiceImpl.findAreaCountyCount());
//	}
//
//	public void insertAreaCounty() throws YuleException{
//		AreaCounty areaCounty = new AreaCounty();
//		areaCounty.setId(IDUtil.getID());
//		areaCounty.setName("insert");
//		try {
//			System.out.println(areaCountyServiceImpl.insertAreaCounty(areaCounty));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void updateAreaCounty() throws YuleException{
//		AreaCounty areaCounty = new AreaCounty();
//		areaCounty.setId(IDUtil.getID());
//		areaCounty.setName("update");
//		try {
//			System.out.println(areaCountyServiceImpl.updateAreaCounty(areaCounty));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void deleteAreaCountyById() throws YuleException{
//		try {
//			System.out.println(areaCountyServiceImpl.deleteAreaCountyById(IDUtil.getID()));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
