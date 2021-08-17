package com.yule.action.set.other;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.mongo.user.query.UserScoreQuery;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;

@Controller
@Scope("prototype")
public class CompanyCategoryAction extends BaseAction{
	
	@RequestMapping(value = "/category",method = RequestMethod.POST)
	public String findScore(UserScoreQuery userScoreQuery) throws Exception {
		outputResult(JedisUtil.getInstance().get(RedisConst.COMPANY_CATEGORY));
		return null;
	}
	
}