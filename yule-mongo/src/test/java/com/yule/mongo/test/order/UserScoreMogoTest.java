/*package com.yule.mongo.test.order;

import java.util.Random;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;

import com.yule.constant.CustomBeanConst;
import com.yule.mongo.orders.service.IUserScoreMongo;
import com.yule.mongo.pojo.UserScore;
import com.yule.util.CustomBeanFactory;

public class UserScoreMogoTest extends TestCase {

	private IUserScoreMongo userScoreMongoImpl;
	
	public UserScoreMogoTest(String name) {
		super(name);
		ApplicationContext context = CustomBeanFactory
				.getContext(CustomBeanConst.ORDER_MONGO_PATHS);
		userScoreMongoImpl = (IUserScoreMongo) context.getBean("userScoreMongoImpl");
	}


	public static Test suite() {
		TestSuite test = new TestSuite("UserScoreMogoTest接口测试");
		////test.addTest(new OrdersManagerTest("updateOrders"));
		test.addTest(new UserScoreMogoTest("insertUserScore"));
		test.addTest(new UserScoreMogoTest("findUserScore"));
		return test;
	}
	public void findUserScore(){
		try {
			int user_score	=this.userScoreMongoImpl.findUserScore("6b54f900-fe47-4044-9f95-5de6270ba0f1");
			System.err.println("---------");
			System.err.println(user_score);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertUserScore(){
		for (int i = 0; i < 100; i++) {
			UserScore userScore =new UserScore();
			userScore.setScore(new Random().nextInt(1000));
			userScore.setType(1);
			userScore.setStatus(0);
			userScore.setUser_id("6b54f900-fe47-4044-9f95-5de6270ba0f1");
			try {
				this.userScoreMongoImpl.insertUserScore(userScore);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
*/