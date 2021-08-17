//package com.yule.mongo.test.detail;
//
//import java.util.List;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.mongo.detail.service.ICompanyCommentMongo;
//import com.yule.mongo.detail.vo.CompanyCommentTestVO;
//import com.yule.mongo.pojo.CompanyComment;
//import com.yule.util.CustomBeanFactory;
//import com.yule.vo.Page;
//
//public class CompanyCommentMongoText extends TestCase{
//	
//	private ICompanyCommentMongo  companyCommentMongoImpl;
//	
//	public CompanyCommentMongoText(String name) {
//		super(name);
//		companyCommentMongoImpl = (ICompanyCommentMongo) CustomBeanFactory.getContext(CustomBeanConst.DETAIL_MONGO_PATHS).getBean("companyCommentMongoImpl");
//	}
//	public static Test suite() {
//		TestSuite test = new TestSuite("OrdersManagerTest接口测试");
////		test.addTest(new CompanyCommentMongoText("findCompanyCommentList"));
////		test.addTest(new CompanyCommentMongoText("findCompanyCommentNum"));
//		test.addTest(new CompanyCommentMongoText("findCompanyCommentCategoryPointAvgScore"));
//		return test;
//	}
//	public void findCompanyComment()throws Exception{
//		CompanyComment findCompanyComment = this.companyCommentMongoImpl.findHighPoint("925bb45f-6bc9-44b2-b63d-91abebf5bff1");
//		System.out.println(findCompanyComment.getPoint());
//	}
//	public void findCompanyCommentNum()throws Exception{
//		int num = this.companyCommentMongoImpl.findCompanyCommentNum("925bb45f-6bc9-44b2-b63d-91abebf5bff1");
//		System.out.println(num);
//	}
////	public void findCompanyCommentList()throws Exception{
////		List<CompanyComment> list = this.companyCommentMongoImpl.findCompanyComment("925bb45f-6bc9-44b2-b63d-91abebf5bff1", 0,0);
////		Map<String, CompanyCommentVO> map = new HashMap<String, CompanyCommentVO>();
////		int i = 0;
////		for (CompanyComment companyComment : list) {
////			List<CompanyPoint> points = companyComment.getCompanyPoints();
////			i++;
////			for (CompanyPoint companyPoint : points) {
////				String categoryName = companyPoint.getCompany_point_category_name();
////				if (map.containsKey(categoryName)) {
////					CompanyCommentVO companyCommentVO = map.get(categoryName);
////					float mapPoint = companyCommentVO.getPoint() +companyPoint.getPoint();
////					companyCommentVO.setPoint(mapPoint);
////					companyCommentVO.setCount(i);
////					map.replace(categoryName, companyCommentVO);
////				}else{
////					CompanyCommentVO companyCommentVO = new CompanyCommentVO();
////					companyCommentVO.setPoint(companyPoint.getPoint());
////					companyCommentVO.setCount(i);
////					map.replace(categoryName, companyCommentVO);
////					map.put(categoryName, companyCommentVO);
////				}
////			}
////		}
////		Set<String> keys = map.keySet();
////		Iterator<String> iterator = keys.iterator();
////		while (iterator.hasNext()) {
////			String next = iterator.next();
////			System.out.println("key:"+next);
////			CompanyCommentVO companyCommentVO = map.get(next);
////			System.out.println(companyCommentVO.getCount()+"---"+companyCommentVO.getPoint()+"--"+companyCommentVO.getPoint()/companyCommentVO.getCount());
////		}
////	}
//	public void findCompanyCommentPage()throws Exception{
//		 Page<CompanyComment> page = this.companyCommentMongoImpl.findCompanyCommentPage("925bb45f-6bc9-44b2-b63d-91abebf5bff1", 100, 1);
//		 System.out.println(page.getRowCount());
//		 List<CompanyComment> datas = page.getDatas();
//		 for (CompanyComment companyComment : datas) {
//			System.out.println(companyComment.getUser_name());
//			System.out.println(companyComment.getPoint());
//			System.out.println(companyComment.getComment());
//			System.out.println(companyComment.getAdvice());
//		}
//	}
//	
//	public void findCompanyCommentCategoryPointAvgScore()throws Exception{
//		List<CompanyCommentTestVO> companyCommentTestVOs = this.companyCommentMongoImpl.findCompanyCommentCategoryPointAvgScore("925bb45f-6bc9-44b2-b63d-91abebf5bff1");
////		for(CompanyCommentTestVO companyCommentTestVO:companyCommentTestVOs){
////			System.out.println(JSONArray.fromObject(companyCommentTestVO.getCompanyPoints()));
////		}
//	}
//}
