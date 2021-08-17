package com.yule.action.favorite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.DoMainConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.mongo.pojo.UserFavorite;
import com.yule.mongo.user.service.IUserFavoriteMongo;
import com.yule.util.DateUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
public class UserFavoriteAction extends BaseAction{
	
	@Autowired
	private IUserFavoriteMongo userFavoritesMongoImpl;

	@RequestMapping(value = "/favorite")
	public String findUserCollections(@RequestParam(value="pageNo",required=false)Integer pageNo) throws Exception {
		if(null==pageNo){
			pageNo = 1;
		}
		Page<UserFavorite> page = userFavoritesMongoImpl.findUserFavoritePageByUserId(getCookieValue(), PageConst.PAGE_SIZE_TEN, pageNo);
		StringBuffer htmls = new StringBuffer("");
		if(page.getRowCount()>0){
			List<UserFavorite> userFavorites = page.getDatas();
			for(UserFavorite userFavorite:userFavorites){
//				htmls.append("<span class=\"history-list-del\"></span>");
				htmls.append("<div class=\"sr_item_photo pull-left\">");
				htmls.append("<img class=\"hotel_image\" src=\"\" width=\"150\" height=\"150\" alt=\""+userFavorite.getCompany_name()+"\">");
				htmls.append("</div>");
				htmls.append("<div class=\"sr_item_content\">");
				htmls.append("<p><a href=\""+DoMainConst.DETAIL_URL+"/company/findCompanyDetails.do?id="+userFavorite.getCompany_id()+"\" class=\"history-list-title\" target=\"_blank\">"+userFavorite.getCompany_name()+"</a>");
				htmls.append("</p>");
				htmls.append("<span class=\"feataddress\">"+userFavorite.getAddress_datail()+", "+userFavorite.getArea_county_name()+", "+userFavorite.getArea_city_name()+"</span>");
				htmls.append("<span class=\"feataddress\">收藏日期:"+DateUtil.DateToString(userFavorite.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_EN)+"</span>");
				htmls.append("<a href=\""+DoMainConst.DETAIL_URL+"/company/findCompanyDetails.do?id="+userFavorite.getCompany_id()+"\" class=\"b-button index-order-btn mt10\" target=\"_blank\">现在就预订</a>");
				htmls.append("</div>");
			}
			userFavorites.clear();
		}
		request.setAttribute("pageHtmls", PaginationUtil.getPaginationHtml(page));
		request.setAttribute("htmls", htmls.toString());
		return "favorite";
	}
	
}