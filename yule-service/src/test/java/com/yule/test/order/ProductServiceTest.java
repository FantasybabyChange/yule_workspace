/*package com.yule.test.order;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;

import com.yule.constant.CustomBeanConst;
import com.yule.orders.service.IProductService;
import com.yule.orders.vo.ProductVO;
import com.yule.util.CustomBeanFactory;

public class ProductServiceTest extends TestCase {

	private IProductService productServiceImpl;

	public ProductServiceTest(String name) {
		super(name);
		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ORDER_SERVICE_PATHS);
		productServiceImpl = (IProductService) context.getBean("productServiceImpl");
	}

	public static Test suite() {
		TestSuite test = new TestSuite("ProductServiceTest接口测试");
		 test.addTest(new ProductServiceTest("findProductVOById"));
		return test;
	}
	
	public void findProductVOById(){
		try {
			ProductVO productVO = this.productServiceImpl.findProductVOById("38ad2201-c733-4e56-b199-7dd548f7ec54");
			System.err.println(productVO.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 }
*/