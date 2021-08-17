//package com.yule.mongo.test.detail;
//package com.yule.mongo.test.detail;
//
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.constant.PageConst;
//import com.yule.mongo.detail.service.ICompanyGalleryMongo;
//import com.yule.mongo.pojo.CompanyGallery;
//import com.yule.util.CustomBeanFactory;
//import com.yule.vo.Page;
//
//public class CompanyGalleryMongoText extends TestCase{
//	
//	private ICompanyGalleryMongo companyGalleryMongoImpl; 
//	public CompanyGalleryMongoText(String name) {
//		super(name);
//		companyGalleryMongoImpl = (ICompanyGalleryMongo) CustomBeanFactory.getContext(CustomBeanConst.DETAIL_MONGO_PATHS).getBean("companyGalleryMongoImpl");
//	}
//	public static Test suite() {
//		TestSuite test = new TestSuite("OrdersManagerTest接口测试");
//		test.addTest(new CompanyGalleryMongoText("findCompanyGalleryMongoImpl"));
//		return test;
//	}
//	public void findCompanyGalleryMongoImpl()throws YuleException{
//		Page<CompanyGallery> page = this.companyGalleryMongoImpl.findCompanyGalleryPageByCompanyId("925bb45f-6bc9-44b2-b63d-91abebf5bff1", Integer.MAX_VALUE, PageConst.PAGE_NO_DEFAULT);
//		List<CompanyGallery> datas = page.getDatas();
//		for (CompanyGallery companyGallery : datas) {
//			System.out.println(companyGallery.getCompany_id());
//			System.out.println(companyGallery.getPath());
//			System.out.println(companyGallery.getSystem_name());
//		}
//	}
//}
