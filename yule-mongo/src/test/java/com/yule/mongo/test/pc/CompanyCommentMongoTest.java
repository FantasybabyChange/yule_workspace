//package com.yule.mongo.test.pc;
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
//import com.yule.mongo.pc.service.ICompanyCommentMongo;
//import com.yule.mongo.pojo.CompanyComment;
//import com.yule.mongo.pojo.CompanyPoint;
//import com.yule.mongo.query.CompanyCommentQuery;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//import com.yule.vo.Page;
//
//public class CompanyCommentMongoTest extends TestCase {
//
//	private ICompanyCommentMongo companyCommentMongoImpl = null;
//	
//	public CompanyCommentMongoTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.PC_MONGO_PATHS);
//		companyCommentMongoImpl = (ICompanyCommentMongo) context.getBean("companyCommentMongoImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyCommentMongoTest接口测试");
//		test.addTest(new CompanyCommentMongoTest("insertCompanyComment"));
////		test.addTest(new CompanyCommentMongoTest("findCompanyCommentPage"));
//		return test;
//	}
//	public void insertCompanyComment()throws YuleException{
//		for (int i = 1; i < 12; i++) {
//			CompanyComment companyComment = new CompanyComment();
//			companyComment.setUser_id("c659c7aa-a9cf-4f8c-909b-7869c57a87c5");
//			companyComment.setUser_name("光明小磊");
//			companyComment.setOrder_num("7cafdb83-06fa-484a-8716-e93db6d4cdc1");
//			companyComment.setUser_image_path("http://p.yuleing.com/jpg/20140905/150_1506dde9500-f245-4c8e-898c-7dd89cfe81cc20140905120423361.jpg");
//			companyComment.setCompany_id("925bb45f-6bc9-44b2-b63d-91abebf5bff1");
//			companyComment.setAdvice("张大炮");
//			companyComment.setComment("我是大张磊 我是大张磊我是大张磊我是大张磊我是大张磊我是大张磊我是大张磊我是大张磊我是大张磊我是大张磊我是大张磊我是大张磊我是大张磊");
//			companyComment.setPoint(Float.parseFloat(i+""));
//			companyComment.setTitle("小二磊");
//			List<CompanyPoint> companyPoints=  new ArrayList<CompanyPoint>();
//			CompanyPoint companyPoint = new CompanyPoint();
//			companyPoint.setCompany_point_category_name("长相");
//			companyPoint.setPoint(0f);
//			companyPoints.add(companyPoint);
//			
//			CompanyPoint companyPoint1 = new CompanyPoint();
//			companyPoint1.setCompany_point_category_name("人品");
//			companyPoint1.setPoint(1f);
//			companyPoints.add(companyPoint1);
//
//			CompanyPoint companyPoint2 = new CompanyPoint();
//			companyPoint2.setCompany_point_category_name("味道");
//			companyPoint2.setPoint(0f);
//			companyPoints.add(companyPoint2);
//
//			CompanyPoint companyPoint3 = new CompanyPoint();
//			companyPoint3.setCompany_point_category_name("猥琐程度");
//			companyPoint3.setPoint(10f);
//			companyPoints.add(companyPoint3);
//			CompanyPoint companyPoint4 = new CompanyPoint();
//			companyPoint4.setCompany_point_category_name("脑残程度");
//			companyPoint4.setPoint(10f);
//			companyPoints.add(companyPoint4);
//			CompanyPoint companyPoint5 = new CompanyPoint();
//			companyPoint5.setCompany_point_category_name("不洗澡");
//			companyPoint5.setPoint(10f);
//			companyPoints.add(companyPoint5);
//			companyComment.setCompanyPoints(companyPoints);
//			this.companyCommentMongoImpl.insertCompanyComment(companyComment);
//		
//		}
//	}
//	
//	public void findCompanyCommentPage() {
//		try {
//			CompanyCommentQuery companyCommentMongoQuery = new CompanyCommentQuery();
////			CompanyCommentMongoQuery.setStart_time("2014-06-26");
////			CompanyCommentMongoQuery.setEnd_time("2014-06-31");
////			CompanyComment.setTitle("二哥");
////			CompanyComment.setAdmin_user_id(2);
//			companyCommentMongoQuery.setUser_id("1");
//			Page<CompanyComment> pages = companyCommentMongoImpl.findCompanyCommentPage(companyCommentMongoQuery, 10,1);
//			System.out.println(pages.getDatas().size());
//			for(CompanyComment companyComment :pages.getDatas()){
//				System.out.println(companyComment.getCompanyPoints().size());
//			}
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	
//}
//
