//package com.yule.mongo.test.detail;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.constant.StatusConst;
//import com.yule.mongo.detail.service.IUserFavoriteMongo;
//import com.yule.mongo.pojo.UserFavorite;
//import com.yule.util.CustomBeanFactory;
//
//public class UserFavoriteMongoText extends TestCase{
//	
//	private IUserFavoriteMongo userFavoriteMongoImpl; 
//	public UserFavoriteMongoText(String name) {
//		super(name);
//		userFavoriteMongoImpl = (IUserFavoriteMongo) CustomBeanFactory.getContext(CustomBeanConst.DETAIL_MONGO_PATHS).getBean("userFavoriteMongoImpl");
//	}
//	public static Test suite() {
//		TestSuite test = new TestSuite("UserFavoriteMongoText接口测试");
//		test.addTest(new UserFavoriteMongoText("findUserFavorite"));
//		return test;
//	}
//	public void findUserFavorite()throws YuleException{
//		int count = this.userFavoriteMongoImpl.findUserFavoriteByUserId(null, "4");
//		System.out.println(count);
//	}
//	public void insertUserFavorite() throws YuleException{
//		UserFavorite uf = new UserFavorite();
//		uf.setAddress_datail("1");
//		uf.setArea_city_name("2");
//		uf.setArea_county_name("3");
//		uf.setCompany_id("4");
//		uf.setCompany_image_path("5");
//		uf.setCompany_name("6");
//		uf.setIs_delete(StatusConst.IS_DELETE_TRUE);
//		uf.setUser_id("10");
//		uf.setUser_name("8");
//		System.out.println(this.userFavoriteMongoImpl.insertUserFavorite(uf));
//		
//	}
//}
