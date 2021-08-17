package com.yule.action.expense;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.JSONConst;
import com.yule.enumerate.DateStyle;
import com.yule.mongo.user.query.UserExpenseQuery;
import com.yule.mongo.user.service.IUserExpenseMongo;
import com.yule.mongo.user.vo.UserExpenseVO;
import com.yule.pojo.UserLevel;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.user.service.IUserLevelService;
import com.yule.util.DateUtil;
import com.yule.util.RatioUtil;

@Controller
@Scope("prototype")
public class UserExpenseAction extends BaseAction{
	
	@Autowired
	private IUserExpenseMongo userExpenseMongoImpl;
	
	@Autowired
	private IUserLevelService userLevelServiceImpl;
	
//	@Autowired
//	private IUserLoginService userLoginServiceImpl;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/expense",method = RequestMethod.GET)
	public String findExpense() throws Exception {
		String userId = getCookieValue();
		UserExpenseQuery userExpenseQuery = new UserExpenseQuery();
		userExpenseQuery.setUserId(getCookieValue());
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		userExpenseQuery.setEndTime(DateUtil.DateToString(calendar.getTime(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		calendar.add(Calendar.YEAR, -1);
		userExpenseQuery.setStartTime(DateUtil.DateToString(calendar.getTime(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		UserExpenseVO userExpenseVO = userExpenseMongoImpl.findUserExpenseVO(userExpenseQuery);
		userExpenseVO.setRatio(userExpenseVO.getRatio().replace("%", ""));
		UserLevel ul = userLevelServiceImpl.findUserLevelByUserId(userId);
		List<UserLevel> userLevels = new ArrayList<UserLevel>();
		if(JedisUtil.getInstance().exists(RedisConst.USER_LEVEL)){
			userLevels.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.USER_LEVEL)),new UserLevel(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		StringBuffer levelHtmls = new StringBuffer("");
		StringBuffer growHtmls = new StringBuffer("");
		levelHtmls.append("<ul class=\"level_img_list clearfix\">");
		growHtmls.append("<div class=\"grow_bar\">");
		int i=1;
		UserLevel currentUserLevel = null;
		UserLevel nextUserLevel = null;
		for(UserLevel userLevel:userLevels){
			if(ul.getId().equals(userLevel.getId())){
				levelHtmls.append("<li class=\"lv0"+i+"\"><em class=\"current\"></em><span>"+userLevel.getName()+"</span></li>");
				currentUserLevel = userLevel;
			}else{
				levelHtmls.append("<li class=\"lv0"+i+"\"><em></em><span>"+userLevel.getName()+"</span></li>");
			}
			if(null!=currentUserLevel&&null==nextUserLevel&&userLevels.size()>i){
				nextUserLevel = userLevels.get(i);
			}
			growHtmls.append("<div class=\"bar bar0"+i+"\">"+userLevel.getExpense()+"</div>");
			i++;
		}
		if(nextUserLevel!=null){
			nextUserLevel.setExpense(nextUserLevel.getExpense()-currentUserLevel.getExpense());
			request.setAttribute("nextUserLevel", nextUserLevel);
			request.setAttribute("nextText", "还差<strong>"+nextUserLevel.getExpense()+"即可升级到"+nextUserLevel.getName());
		}else{
			request.setAttribute("nextText", currentUserLevel.getName());
		}
		htmls.append(levelHtmls.append("</ul>"));
		htmls.append(growHtmls.append("</div>"));
		levelHtmls.setLength(0);
		growHtmls.setLength(0);
		String moneyRatio = RatioUtil.getUpperRatio(userExpenseVO.getMoney(), userLevels.get(userLevels.size()-1).getExpense());
		userLevels.clear();
		request.setAttribute("moneyRatio", moneyRatio);
		request.setAttribute("currentUserLevel", currentUserLevel);
		request.setAttribute("levelHtmls", htmls.toString());
		request.setAttribute("userExpenseVO", userExpenseVO);
		return "expense";
	}
	
}