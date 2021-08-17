package com.yule.data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yule.admin.service.IUserLevelService;
import com.yule.constant.ConfigConst;
import com.yule.constant.CustomBeanConst;
import com.yule.pojo.UserLevel;
import com.yule.util.ConvertUtil;
import com.yule.util.CustomBeanFactory;
import com.yule.util.IDUtil;

public class UserInit {
	public static void main(String[] args) throws Exception {
		initUser();
	}

	@SuppressWarnings("unchecked")
	public static void initUser() throws Exception{
		IUserLevelService userLevelServiceImpl = (IUserLevelService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("userLevelServiceImpl");
		userLevelServiceImpl.deleteUserLevelAll();
		SAXReader sr = new SAXReader();
		InputStream in = UserInit.class.getClassLoader().getResourceAsStream(ConfigConst.USER);
		Document doc = sr.read(in);
		Element root = doc.getRootElement();
		UserLevel userLevel = null;
		List<Element> userLevels = root.elements("level");
		List<UserLevel> levels = new ArrayList<UserLevel>();
		if (null!=userLevels&&userLevels.size()>0) {
			for(Element e : userLevels){
				userLevel =new UserLevel();
				userLevel.setId(IDUtil.getID());
				userLevel.setName(e.attributeValue("name"));
				userLevel.setExpense(ConvertUtil.stringToInteger(e.attributeValue("expense")));
				userLevel.setScore_ratio(Integer.valueOf(e.attributeValue("score_ratio")));
				levels.add(userLevel);
			}
			userLevelServiceImpl.batchInsertLevel(levels);
		}
		in.close();
		doc.clearContent();
	}
	
}