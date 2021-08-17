//package com.yule.mongo.test.admin;
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
//import com.yule.constant.CustomBeanConst;
//import com.yule.mongo.admin.service.ICompanyGalleryMongo;
//import com.yule.mongo.param.InsertCompanyGalleryParam;
//import com.yule.mongo.pojo.CompanyGallery;
//import com.yule.util.CustomBeanFactory;
//import com.yule.vo.Page;
//
//public class CompanyGalleryMongoTest extends TestCase{
//
//	private ICompanyGalleryMongo CompanyGalleryMongoImpl;
//
//	public CompanyGalleryMongoTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_MONGO_PATHS);
//		CompanyGalleryMongoImpl = (ICompanyGalleryMongo) context.getBean("companyGalleryMongoImpl");
//	}
//	
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyGalleryMongoTest接口测试");
//		test.addTest(new CompanyGalleryMongoTest("bathInsertCompanyGallery"));
//		test.addTest(new CompanyGalleryMongoTest("deleteCompanyGallery"));
//		test.addTest(new CompanyGalleryMongoTest("findCompanyGalleryPage"));
//		return test;
//	}
//	
//	
//	public void deleteCompanyGallery(){
//		try {
//			this.CompanyGalleryMongoImpl.deleteCompanyGalleryById("53fdeb7001aba6176d03d1a4");
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void bathInsertCompanyGallery(){
//		InsertCompanyGalleryParam companyGalleryParam=new InsertCompanyGalleryParam();
//		List<String> names =new ArrayList<String>();
//		List<String> sysnames =new ArrayList<String>();
//		List<String> type =new ArrayList<String>();
//		List<String> size =new ArrayList<String>();
//		List<String> path =new ArrayList<String>();
//		names.add("11");
//		sysnames.add("11");
//		type.add("11");
//		size.add("22");
//		path.add("22");
//		companyGalleryParam.setCompany_id("02273571-bb7d-44e0-99c6-d7ec6d252fb2");
//		companyGalleryParam.setName(names);
//		companyGalleryParam.setSystem_name(sysnames);
//		companyGalleryParam.setType(type);
//		companyGalleryParam.setSize(size);
//		companyGalleryParam.setPath(path);
//		try {
//			this.CompanyGalleryMongoImpl.batchInsertCompanyGallery(companyGalleryParam);
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void findCompanyGalleryPage(){
//		try {
//			Page<CompanyGallery> page=this.CompanyGalleryMongoImpl.findCompanyGalleryPage("02273571-bb7d-44e0-99c6-d7ec6d252fb2", 10, 1);
//			List<CompanyGallery> CompanyGalleries = page.getDatas();
//			for (CompanyGallery CompanyGallery : CompanyGalleries) {
//				System.err.println(CompanyGallery.toString());
//			}
//			System.err.println(page.getPageCount());
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//}