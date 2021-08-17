package com.yule.action.favorite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.DoMainConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.mongo.pojo.UserFavorite;
import com.yule.mongo.user.service.IUserFavoriteMongo;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
public class FavoriteAction extends BaseAction{
	
	@Autowired
	private IUserFavoriteMongo userFavoritesMongoImpl;

	@RequestMapping(value = "/findFavorite",method = RequestMethod.POST)
	public String findUserCollections() throws Exception {
		Page<UserFavorite> page = userFavoritesMongoImpl.findUserFavoritePageByUserId(getCookieValue(), PageConst.PAGE_SIZE_TEN, PageConst.PAGE_NO_DEFAULT);
		StringBuffer htmls = new StringBuffer("");
		if(page.getRowCount()>0){
			List<UserFavorite> userFavorites = page.getDatas();
			for(UserFavorite userFavorite:userFavorites){
				htmls.append("<li class=\"history-list-item clearfix\">");
//				htmls.append("<span class=\"history-list-del\"></span>");
				htmls.append("<div class=\"sr_item_photo pull-left\">");
				htmls.append("<img class=\"hotel_image\" src=\"\" width=\"150\" height=\"150\" alt=\""+userFavorite.getCompany_name()+"\">");
				htmls.append("</div>");
				htmls.append("<div class=\"sr_item_content\">");
				htmls.append("<p><a href=\""+DoMainConst.DETAIL_URL+"/company/findCompanyDetails.do?id="+userFavorite.getCompany_id()+"\" class=\"history-list-title\" target=\"_blank\">"+userFavorite.getCompany_name()+"【"+userFavorite.getCompany_grade_name()+"】</a>");
				htmls.append("</p>");
				htmls.append("<span class=\"feataddress\">"+userFavorite.getAddress_datail()+", "+userFavorite.getArea_county_name()+", "+userFavorite.getArea_city_name()+"</span>");
				htmls.append("<span class=\"feataddress\">收藏日期:"+DateUtil.DateToString(userFavorite.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_EN)+"</span>");
				htmls.append("<a href=\""+DoMainConst.DETAIL_URL+"/company/findCompanyDetails.do?id="+userFavorite.getCompany_id()+"\" class=\"b-button index-order-btn mt10\" target=\"_blank\">现在就预订</a>");
				htmls.append("</div>");
				htmls.append("</li>");
			}
			userFavorites.clear();
		}
		request.setAttribute("result", htmls.toString());
		return "return/favorite";
	}
	
}