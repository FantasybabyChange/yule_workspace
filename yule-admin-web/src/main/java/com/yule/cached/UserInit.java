package com.yule.cached;

import java.util.List;

import net.sf.json.JSONArray;

import com.yule.admin.service.IUserLevelService;
import com.yule.constant.CustomBeanConst;
import com.yule.constant.TimeConst;
import com.yule.pojo.UserLevel;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.CustomBeanFactory;

public class UserInit {
	
	public static void initUserLevel() throws Exception{
		IUserLevelService userLevelServiceImpl = (IUserLevelService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("userLevelServiceImpl");
		List<UserLevel> userLevels = userLevelServiceImpl.findUserLevelList();
		if(null!=userLevels&&userLevels.size()>0){
			JedisUtil.getInstance().del(RedisConst.USER_LEVEL);
			JedisUtil.getInstance().set(RedisConst.USER_LEVEL, JSONArray.fromObject(userLevels).toString(),TimeConst.TWO_DAY);
			userLevels.clear();
			userLevels = null;
		}
		userLevelServiceImpl = null;
	}
}