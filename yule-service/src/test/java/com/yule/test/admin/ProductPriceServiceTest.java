//package com.yule.test.admin;
//
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.admin.service.IProductPriceService;
//import com.yule.admin.service.IProductPriceService;
//import com.yule.constant.CustomBeanConst;
//
//import com.yule.pojo.ProductPrice;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//import com.yule.vo.Page;
//
//public class ProductPriceServiceTest extends TestCase {
//	private IProductPriceService productPriceServiceImpl;
//
//	public ProductPriceServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		productPriceServiceImpl = (IProductPriceService) context.getBean("productPriceServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("ProductPriceServiceTest接口测试");
//		test.addTest(new ProductPriceServiceTest("insertProductPrice"));
////		test.addTest(new ProductPriceServiceTest("findProductPriceCount"));
////		test.addTest(new ProductPriceServiceTest("findProductPricePage"));
////		test.addTest(new ProductPriceServiceTest("findProductPriceById"));
////		test.addTest(new ProductPriceServiceTest("insertProductPrice"));
////		test.addTest(new ProductPriceServiceTest("deleteProductPriceById"));
//		return test;
//	}
//	
//	public void findProductPricePage() {
//		try {
//			Page<ProductPrice> page = productPriceServiceImpl.findProductPricePage(IDUtil.getID(), Integer.MAX_VALUE, 1);
//			System.out.println(page.getDatas().size());
////			for (ProductPrice ProductPrice : lists) {
////				System.out.println(ProductPrice.getName());
////			}
//		} catch (YuleException e) {
//			// TODO: handle YuleException
//		}
//	
//	}
//
//	public void findProductPriceList() {
//		try {
//			List<ProductPrice> lists = productPriceServiceImpl.findProductPriceListByProductId("17b1ed27-d35c-4bdf-9b62-a21aaf910897");
//			System.out.println(lists.size());
//			for (ProductPrice ProductPrice : lists) {
//				System.out.println(ProductPrice.getTime());
//			}
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	
//	}
//	public void findProductPriceCount() {
//		try {
//			System.out.println(productPriceServiceImpl.findProductPriceCount());
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public void findProductPriceById() {
//		try {
//			ProductPrice productPrice = productPriceServiceImpl.findProductPriceById(IDUtil.getID());
//			System.out.println(productPrice);
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	public void insertProductPrice() {
//		ProductPrice productPrice = new ProductPrice();
//		productPrice.setMonday(new BigDecimal(132));
//		productPrice.setThursday(new BigDecimal(133));
//		productPrice.setWednesday(new BigDecimal(134));
//		productPrice.setThursday(new BigDecimal(135));
//		productPrice.setFriday(new BigDecimal(136));
//		productPrice.setSaturday(new BigDecimal(137));
//		productPrice.setSunday(new BigDecimal(138));
//		productPrice.setProduct_id("17b1ed27-d35c-4bdf-9b62-a21aaf910897");
//		productPrice.setCreate_time("13:00-14:00");
//		try {
//			System.out.println(productPriceServiceImpl.insertProductPrice(productPrice));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	public void updateProductPrice() {
//		ProductPrice productPrice = new ProductPrice();
//		productPrice.setId(IDUtil.getID());
//		try {
//			System.out.println(productPriceServiceImpl.updateProductPrice(productPrice));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	public void deleteProductPriceById() {
//		try {
//			System.out.println(productPriceServiceImpl.deleteProductPriceById(IDUtil.getID()));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
