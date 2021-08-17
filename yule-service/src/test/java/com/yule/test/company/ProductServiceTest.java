/*package com.yule.test.company;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;

import com.yule.company.query.ComparePriceQuery;
import com.yule.company.service.IProductService;
import com.yule.constant.CustomBeanConst;
import com.yule.util.CustomBeanFactory;
import com.yule.vo.Page;
import com.yule.vo.ProductVO;

public class ProductServiceTest extends TestCase {

	private IProductService productServiceImpl;

	public ProductServiceTest(String name) {
		super(name);
		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.COMPANY_SERVICE_PATHS);
		productServiceImpl = (IProductService) context.getBean("productServiceImpl");
	}

	public static Test suite() {
		TestSuite test = new TestSuite("ProductServiceTest接口测试");
		 test.addTest(new ProductServiceTest("findOtherCompanyProduct"));
		return test;
	}
	
	public void findOtherCompanyProduct(){
		ComparePriceQuery comparePriceQuery =new ComparePriceQuery();
		comparePriceQuery.setCompany_id("925bb45f-6bc9-44b2-b63d-91abebf5bff1");
		try {
			Page<ProductVO> page = this.productServiceImpl.findOtherCompanyProductPage(comparePriceQuery, 10, 1);
			for (ProductVO productVO :page.getDatas()) {
				System.err.println(productVO.toString());
			}	
		} catch (YuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

 }
*/