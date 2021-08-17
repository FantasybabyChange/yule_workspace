/*package com.yule.test.admin;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.context.ApplicationContext;

import com.yule.admin.query.InsertUserLevelQuery;
import com.yule.admin.service.IUserLevelService;
import com.yule.constant.CustomBeanConst;
import com.yule.pojo.UserLevel;
import com.yule.util.CustomBeanFactory;
import com.yule.util.IDUtil;

public class UserLevelServiceTest extends TestCase{
	private IUserLevelService userLevelServiceImpl;

	public UserLevelServiceTest(String name) {
	super(name);
		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
		userLevelServiceImpl=(IUserLevelService) context.getBean("userLevelServiceImpl");
	}
	public static Test suite() {
		TestSuite test = new TestSuite("UserServiceTest接口测试");
		test.addTest(new UserLevelServiceTest("bathInsert"));
	return test;
	}
	
	public void insertUserLevel(){
		UserLevel userLevel=new UserLevel();
		userLevel.setExpense(20000);
		userLevel.setId(IDUtil.getID());
		userLevel.setName("superN");
		try {
			userLevelServiceImpl.insertUserLevel(userLevel);
		} catch (YuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}	
	}
	public void bathInsert(){
		UserLevel userLevel=new UserLevel();
		userLevel.setExpense(20000);
		userLevel.setName("superN");
		UserLevel userLeve2=new UserLevel();
		userLeve2.setExpense(20000);
		userLeve2.setName("superNa");
		List<UserLevel> userLevels=new ArrayList<UserLevel>();
		userLevels.add(userLeve2);
		userLevels.add(userLevel);
		InsertUserLevelQuery userLevelQuery=new InsertUserLevelQuery();
		userLevelQuery.setUserLevelQuery(userLevels);
		try {
			userLevelServiceImpl.batchInsertAndUpdateUserLevel(userLevelQuery);
		} catch (YuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
*/