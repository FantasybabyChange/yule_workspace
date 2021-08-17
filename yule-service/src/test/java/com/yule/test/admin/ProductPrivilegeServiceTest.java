//package com.yule.test.admin;
//import java.math.BigDecimal;
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.admin.service.IProductPrivilegeService;
//import com.yule.constant.CustomBeanConst;
//
//import com.yule.pojo.ProductPrivilege;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//public class ProductPrivilegeServiceTest extends TestCase {
//	private IProductPrivilegeService productPrivilegeServiceImpl;
//
//	public ProductPrivilegeServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		productPrivilegeServiceImpl = (IProductPrivilegeService) context.getBean("productPrivilegeServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("ProductPrivilegeServiceTest接口测试");
//		test.addTest(new ProductPrivilegeServiceTest("findProductPrivilegeList"));
//		test.addTest(new ProductPrivilegeServiceTest("findProductPrivilegeCount"));
//		test.addTest(new ProductPrivilegeServiceTest("findProductPrivilegeById"));
//		test.addTest(new ProductPrivilegeServiceTest("updateProductPrivilege"));
//		test.addTest(new ProductPrivilegeServiceTest("insertProductPrivilege"));
//		test.addTest(new ProductPrivilegeServiceTest("deleteProductPrivilegeById"));
//		return test;
//	}
//
//	public void findProductPrivilegeList() throws YuleException{
//		List<ProductPrivilege> lists = productPrivilegeServiceImpl.findProductPrivilegeList();
//		System.out.println(lists.size());
////		for (ProductPrivilege ProductPrivilege : lists) {
////			System.out.println(ProductPrivilege.getName());
////		}
//	}
//	public void findProductPrivilegeCount() throws YuleException{
//		System.out.println(productPrivilegeServiceImpl.findProductPrivilegeCount());
//	}
//	public void findProductPrivilegeById() throws YuleException{
//		ProductPrivilege address = productPrivilegeServiceImpl.findProductPrivilegeById(IDUtil.getID());
//		System.out.println(address);
//	}
//	public void insertProductPrivilege() {
//		ProductPrivilege ProductPrivilege = new ProductPrivilege();
//		ProductPrivilege.setId(IDUtil.getID());
//		ProductPrivilege.setPrice(new BigDecimal(1));
//		ProductPrivilege.setProduct_id(IDUtil.getID());
//		ProductPrivilege.setContent("insert");
//		ProductPrivilege.setIs_delete("0");
//		try {
//			System.out.println(productPrivilegeServiceImpl.insertProductPrivilege(ProductPrivilege));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public void updateProductPrivilege() {
//		ProductPrivilege ProductPrivilege = new ProductPrivilege();
//		ProductPrivilege.setId(IDUtil.getID());
//		ProductPrivilege.setPrice(new BigDecimal(2));
//		ProductPrivilege.setProduct_id(IDUtil.getID());
//		ProductPrivilege.setIs_delete("1");
//		ProductPrivilege.setContent("update");
//		try {
//			System.out.println(productPrivilegeServiceImpl.updateProductPrivilege(ProductPrivilege));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public void deleteProductPrivilegeById() {
//		try {
//			System.out.println(productPrivilegeServiceImpl.deleteProductPrivilegeById(IDUtil.getID()));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
