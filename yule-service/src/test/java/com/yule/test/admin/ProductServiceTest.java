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
//import com.yule.admin.service.IProductService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.constant.StatusConst;
//import com.yule.pojo.Product;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//import com.yule.vo.ProductVO;
//
//public class ProductServiceTest extends TestCase {
//	private IProductService productServiceImpl;
//
//	public ProductServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		productServiceImpl = (IProductService) context.getBean("productServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("ProductServiceTest接口测试");
////		test.addTest(new ProductServiceTest("findProductById"));
////		test.addTest(new ProductServiceTest("updateProduct"));
////		test.addTest(new ProductServiceTest("insertProduct"));
////		test.addTest(new ProductServiceTest("deleteProductById"));
//	    test.addTest(new ProductServiceTest("findProductVOList"));
//		return test;
//	}
//	
//
//	public void findProductVOList() {
//		try {
//			List<ProductVO> lists = productServiceImpl.findProductVOList("9cd1c4bc-343d-4600-9e1e-26aa0ce7c0f5");
//			System.out.println(lists.size());
//			for (ProductVO product : lists) {
//				System.out.println(product.getId());
//				System.out.println(product.getProduct_category_id());
//				System.out.println(product.getName());
//				System.out.println(product.getMin_expense());
//				System.out.println(product.getPerson_num());
//				System.out.println(product.getStatus());
//			}
//		}catch(YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void findProductById() {
//		try {
//			Product address = productServiceImpl.findProductById(IDUtil.getID());
//			System.out.println(address);
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	public void insertProduct() {
//		Product product = new Product();
//		product.setId(IDUtil.getID());
//		product.setProduct_category_id("64d042be-f2b2-455c-af1e-fcfd04f2bead");
//		product.setMin_expense(new BigDecimal(100));
//		product.setPerson_num(100);
//		product.setIs_delete(StatusConst.IS_DELETE_TRUE);
//		product.setCompany_id("9cd1c4bc-343d-4600-9e1e-26aa0ce7c0f5");
//		product.setStatus(StatusConst.STATUS_TRUE);
//		try {
//			System.out.println(productServiceImpl.insertProduct(product));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	public void updateProduct() {
//		Product product = new Product();
//		product.setId(IDUtil.getID());
//		product.setProduct_category_id(IDUtil.getID());
//		product.setMin_expense(new BigDecimal(100));
//		product.setIs_delete(StatusConst.IS_DELETE_TRUE);
//		product.setStatus(StatusConst.STATUS_TRUE);
//		product.setCompany_id(IDUtil.getID());
//		try {
//			System.out.println(productServiceImpl.updateProduct(product));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	public void deleteProductById() {
//		try {
//			System.out.println(productServiceImpl.deleteProductById(IDUtil.getID()));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//}
