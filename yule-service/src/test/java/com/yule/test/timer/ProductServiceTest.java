package com.yule.test.timer;
//package com.yule.test.timer;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.timer.service.IProductService;
//import com.yule.timer.vo.ProductLuceneVO;
//import com.yule.util.CustomBeanFactory;
//
//public class ProductServiceTest extends TestCase {
//
//	private IProductService productServiceImpl;
//
//	public ProductServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.TIMER_SERVICE_PATHS);
//		productServiceImpl = (IProductService) context.getBean("productServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("ProductServiceTest接口测试");
//		 test.addTest(new ProductServiceTest("findProductLuceneVO"));
//		return test;
//	}
//    
//	public void findProductLuceneVO() throws YuleException{
//		
//		ProductLuceneVO productLuceneVO = productServiceImpl.findProductLuceneVO("5a6345f3-0e39-43a4-b804-d06ae93f025e");
//		System.out.println(productLuceneVO.getPerson_num());
//	}
//	
// }
