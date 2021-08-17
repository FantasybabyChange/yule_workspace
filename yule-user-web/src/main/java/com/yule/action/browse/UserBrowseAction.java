package com.yule.action.browse;

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
import com.yule.mongo.user.service.IUserBrowseMongo;
import com.yule.mongo.user.vo.UserBrowseVO;
import com.yule.util.DateUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
public class UserBrowseAction extends BaseAction{
	
	@Autowired
	private IUserBrowseMongo userBrowsesMongoImpl;

	@RequestMapping(value = "/browse")
	public String findUserBrowses(@RequestParam(value="pageNo",required=false)Integer pageNo) throws Exception {
		if(null==pageNo){
			pageNo = 1;
		}
		Page<UserBrowseVO> page = userBrowsesMongoImpl.findUserBrowsePageByUserId(getCookieValue(), PageConst.PAGE_SIZE_TEN, pageNo);
		StringBuffer htmls = new StringBuffer("");
		if(page.getRowCount()>0){
			List<UserBrowseVO> userBrowses = page.getDatas();
			for(UserBrowseVO userBrowse:userBrowses){
//				htmls.append("<span class=\"history-list-del\"></span>");
				htmls.append("<div class=\"sr_item_photo pull-left\">");
				htmls.append("<img class=\"hotel_image\" src=\"\" width=\"150\" height=\"150\" alt=\""+userBrowse.getCompany_name()+"\">");
				htmls.append("</div>");
				htmls.append("<div class=\"sr_item_content\">");
				htmls.append("<p><a href=\""+DoMainConst.DETAIL_URL+"/company/findCompanyDetails.do?id="+userBrowse.getCompany_id()+"\" class=\"history-list-title\" target=\"_blank\">"+userBrowse.getAddress_datail()+"</a></p>");
				htmls.append("<span class=\"feataddress\">"+userBrowse.getAddress_datail()+", "+userBrowse.getArea_county_name()+", "+userBrowse.getArea_city_name()+"</span>");
				htmls.append("<span class=\"feataddress\">浏览次数:"+userBrowse.getCount()+"</span>");
				htmls.append("<span class=\"feataddress\">最后浏览日期:"+DateUtil.DateToString(userBrowse.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_EN)+"</span>");
				htmls.append("<a href=\"#\" class=\"b-button index-order-btn mt10\">现在就预订</a>");
				htmls.append("</div>");
			}
			userBrowses.clear();
		}
		request.setAttribute("pageHtmls", PaginationUtil.getPaginationHtml(page));
		request.setAttribute("htmls", htmls.toString());
		return "browse";
	}
	
}