package com.yule.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.mongo.user.service.IOrdersMongo;
import com.yule.mongo.user.service.IUserBrowseMongo;
import com.yule.mongo.user.service.IUserFavoriteMongo;
import com.yule.mongo.user.service.IUserScoreMongo;
import com.yule.user.service.IUserService;
import com.yule.user.vo.UserVO;

@Controller
@Scope("prototype")
public class IndexAction extends BaseAction{
	
	@Autowired
	private IUserService userServiceImpl;
	
	@Autowired
	private IUserScoreMongo userScoreMongoImpl;
	
	@Autowired
	private IUserBrowseMongo userBrowseMongoImpl;
	
	@Autowired
	private IUserFavoriteMongo userFavoriteMongoImpl;
	
	@Autowired
	private IOrdersMongo ordersMongoImpl;
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String index() throws Exception {
		String userId = getCookieValue();
		UserVO userVO = userServiceImpl.findUserVOById(userId);
		request.setAttribute("user", userVO);
		request.setAttribute("userLoginVO", getUserLoginVO());
		request.setAttribute("ordersCount", ordersMongoImpl.findOrders(userId));
		request.setAttribute("scoreSum", userScoreMongoImpl.findUserScore(userId));
		request.setAttribute("browseCount", userBrowseMongoImpl.findUserBrowse(userId));
		request.setAttribute("favoriteCount", userFavoriteMongoImpl.findUserFavorite(userId));
		return "index";
	}
	
}