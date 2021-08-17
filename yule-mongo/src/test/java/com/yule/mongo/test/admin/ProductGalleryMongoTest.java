//package com.yule.mongo.test.admin;
//
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.mongo.admin.service.IProductGalleryMongo;
//import com.yule.mongo.pojo.ProductGallery;
//import com.yule.util.CustomBeanFactory;
//import com.yule.vo.Page;
//
//public class ProductGalleryMongoTest extends TestCase{
//
//	private IProductGalleryMongo productGalleryMongoImpl;
//
//	public ProductGalleryMongoTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_MONGO_PATHS);
//		productGalleryMongoImpl = (IProductGalleryMongo) context.getBean("productGalleryMongoImpl");
//	}
//	
//	public static Test suite() {
//		TestSuite test = new TestSuite("ProductGalleryMongoTest接口测试");
//		test.addTest(new ProductGalleryMongoTest("insertProductGallery"));
//		test.addTest(new ProductGalleryMongoTest("deleteProductGallery"));
//		test.addTest(new ProductGalleryMongoTest("findProductGalleryPage"));
//	return test;
//	}
//	
//	public void insertProductGallery(){
//		ProductGallery productGallery =new ProductGallery();
//		productGallery.setName("dada");
//		productGallery.setIs_delete(0);
//		productGallery.setProduct_id("618108d8-868d-4406-8b24-d9f010b35373");
//		try {
//			productGalleryMongoImpl.insertProductGallery(productGallery);
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void deleteProductGallery(){
//		try {
//			//this.productGalleryMongoImpl.deleteProductGalleryById("53fda42ed61f2f7c853a976e");
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void findProductGalleryPage(){
//		try {
//			Page<ProductGallery> page=this.productGalleryMongoImpl.findProductGalleryPage("618108d8-868d-4406-8b24-d9f010b35373", 20, 1);
//			List<ProductGallery> productGalleries = page.getDatas();
//			for (ProductGallery productGallery : productGalleries) {
//				System.err.println(productGallery.toString());
//			}
//			System.err.println(page.getPageCount());
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//}
